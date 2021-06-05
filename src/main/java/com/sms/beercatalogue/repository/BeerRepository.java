package com.sms.beercatalogue.repository;

import com.sms.beercatalogue.document.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BeerRepository extends MongoRepository<Beer, String> {

    Optional<Beer> findBeerByBeerName(String beerName);


}
