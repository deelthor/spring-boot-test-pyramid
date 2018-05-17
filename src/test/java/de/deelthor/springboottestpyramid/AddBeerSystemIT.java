package de.deelthor.springboottestpyramid;

import de.deelthor.springboottestpyramid.persistence.BeerEntity;
import de.deelthor.springboottestpyramid.persistence.BeerRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddBeerSystemIT {

	private static final String EXPECTED_BREWERY_NAME = "big_endian_brewing";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BeerRepository repository;

	@Autowired
	@Value("classpath:add-beer-request.json")
	private Resource addBeerRequest;

	@Test
	public void persists_beer() throws Exception{
		byte[] requestBody = IOUtils.toByteArray(addBeerRequest.getURI());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		restTemplate.exchange("/beers", HttpMethod.POST, new HttpEntity<>(requestBody, headers), Void.class);

		List<BeerEntity> beers = repository.findAll();

		assertThat(beers, hasSize(1));
		assertThat(beers.get(0).getBrewery(), is(EXPECTED_BREWERY_NAME));
	}

}
