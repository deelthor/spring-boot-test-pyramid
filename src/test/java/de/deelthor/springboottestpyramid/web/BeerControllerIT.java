package de.deelthor.springboottestpyramid.web;

import de.deelthor.springboottestpyramid.domain.Beer;
import de.deelthor.springboottestpyramid.domain.BeerService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BeerService beerService;

    @Autowired
    @Value("classpath:add-beer-request.json")
    private Resource addBeerRequest;

    @Test
    public void get_all_beers() throws Exception {
        given(beerService.findAll()).willReturn(
                asList(finneBeer(), pinkusBeer())
        );

        mvc.perform(get("/beers"))
                .andExpect(status().isOk())
                .andExpect(finneBeerAtIndex(0))
                .andExpect(pinkusBeerAtIndex(1));
    }

    @Test
    public void should_return_beer_by_id() throws Exception {
        given(beerService.findById(anyLong())).willReturn(Optional.of(finneBeer()));

        mvc.perform(get("/beers/1"))
                .andExpect(status().isOk())
                .andExpect(finneBeerFields());
    }

    @Test
    public void should_return_not_found_when_beer_not_found() throws Exception {
        given(beerService.findById(anyLong())).willReturn(Optional.empty());

        mvc.perform(get("/beers/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_location_when_adding_beer() throws Exception {
        given(beerService.addBeer(any())).willReturn(pinkusBeer());

        mvc.perform(post("/beers")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(IOUtils.toByteArray(addBeerRequest.getURI())))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", endsWith("/beers/2")));
    }

    private ResultMatcher finneBeerFields() throws Exception {
        return beerFieldsAtPath(finneBeer(), "$");
    }

    private Beer finneBeer() {
        return new Beer(1, "Finne", 5.5, "Helles", 15);
    }

    private Beer pinkusBeer() {
        return new Beer(2, "Pinkus", 4.8, "Spezial", 30);
    }

    private ResultMatcher finneBeerAtIndex(int index) throws Exception {
        return beerFieldsAtIndex(finneBeer(), index);
    }

    private ResultMatcher pinkusBeerAtIndex(int index) throws Exception {
        return beerFieldsAtIndex(pinkusBeer(), index);
    }

    private ResultMatcher beerFieldsAtIndex(Beer beer, int index) throws Exception {
        String jsonPath = "$[" + index + "]";
        return beerFieldsAtPath(beer, jsonPath);
    }

    private ResultMatcher beerFieldsAtPath(final Beer beer, final String jsonPath) throws Exception {
        return allOf(
                jsonPath(jsonPath + ".id").value(beer.getId()),
                jsonPath(jsonPath + ".abv").value(beer.getAbv()),
                jsonPath(jsonPath + ".brewery").value(beer.getBrewery()),
                jsonPath(jsonPath + ".style").value(beer.getStyle()),
                jsonPath(jsonPath + ".ibu").value(beer.getIbu())
        );
    }

    private ResultMatcher allOf(final ResultMatcher... matchers) throws Exception {
        return (result) -> {
            for (ResultMatcher m : matchers) {
                m.match(result);
            }
        };
    }
}
