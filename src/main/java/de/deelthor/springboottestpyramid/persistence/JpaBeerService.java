package de.deelthor.springboottestpyramid.persistence;

import de.deelthor.springboottestpyramid.domain.AddBeerCommand;
import de.deelthor.springboottestpyramid.domain.Beer;
import de.deelthor.springboottestpyramid.domain.BeerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaBeerService implements BeerService{

    private BeerRepository beerRepository;

    public JpaBeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> findAll() {
        return beerRepository.findAll()
                .stream()
                .map(BeerEntity::toBeer)
                .collect(Collectors.toList());
    }

    @Override
    public Beer addBeer(AddBeerCommand addBeerCommand) {
        Objects.requireNonNull(addBeerCommand, "KEIN BIER!");

        BeerEntity entity = createEntity(addBeerCommand);
        entity  = beerRepository.save(entity);
        return entity.toBeer();
    }

    @Override
    @Transactional
    public Optional<Beer> findById(Long id) {
        return beerRepository.findById(id)
                .map(BeerEntity::toBeer);
    }

    private BeerEntity createEntity(AddBeerCommand addBeerCommand) {
        return BeerEntity.fromAddBeerCommand(addBeerCommand);
    }
}
