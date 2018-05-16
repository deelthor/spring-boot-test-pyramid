package de.deelthor.springboottestpyramid.web;

import de.deelthor.springboottestpyramid.domain.Beer;
import de.deelthor.springboottestpyramid.domain.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public ResponseEntity<List<BeerDto>> getAll(){
        List<Beer> beers = beerService.findAll();
        List<BeerDto> beerResponse = beers.stream()
                .map(BeerDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(beerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> getOne(@PathVariable("id") final Long id) {
        Optional<Beer> order = beerService.findById(id);
        return order.map(BeerDto::new).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AddBeerRequest addBeerRequest){
        Beer beer = beerService.addBeer(addBeerRequest.toAddBeerCommand());

        final URI location = fromMethodCall(on(BeerController.class).getOne(beer.getId()))
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
