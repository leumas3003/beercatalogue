package com.sms.beercatalogue.repository;

import com.sms.beercatalogue.table.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, String> {

    Optional<Beer> findBeerByBeerName(String beerName);
}
