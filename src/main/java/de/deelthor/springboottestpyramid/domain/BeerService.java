package de.deelthor.springboottestpyramid.domain;


import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<Beer> findAll();

    Beer addBeer(AddBeerCommand beer);

    Optional<Beer> findById(Long id);
}
