package de.deelthor.springboottestpyramid.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<BeerEntity, Long> {
}
