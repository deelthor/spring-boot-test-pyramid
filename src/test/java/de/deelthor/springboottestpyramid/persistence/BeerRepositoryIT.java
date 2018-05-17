package de.deelthor.springboottestpyramid.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryIT {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BeerRepository repository;

    @Test
    public void should_find_all() {
        em.persistAndFlush(finneBeerEntity());
        em.persistAndFlush(pinkusBeerEntity());

        List<BeerEntity> found = repository.findAll();

        assertThat(found, hasSize(2));
    }

    private BeerEntity pinkusBeerEntity() {
        BeerEntity beerEntity = new BeerEntity("Pinkus", 4.8, "Spezial", 30);
        return beerEntity;
    }

    private BeerEntity finneBeerEntity() {
        BeerEntity beerEntity = new BeerEntity("Finne", 5.2, "Helles", 15);
        return beerEntity;
    }
}
