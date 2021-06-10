package com.sms.beercatalogue.repository;

import com.sms.beercatalogue.document.Manufacturer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManufacturerRepository extends MongoRepository<Manufacturer, String> {

    Optional<Manufacturer> findManufacturerByName(String name);
}
