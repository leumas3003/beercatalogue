package com.sms.beercatalogue.repository;

import com.sms.beercatalogue.table.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {

    Optional<Manufacturer> findManufacturerByName(String name);
}
