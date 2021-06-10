package com.sms.beercatalogue.service;

import com.sms.beercatalogue.table.Manufacturer;
import com.sms.beercatalogue.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ManufacturerService {

    private final ManufacturerRepository repository;
    public List<Manufacturer> getAllManufacturers(){
        return repository.findAll();
    }

    public void addingManufacturer(Manufacturer manufacturer){
        Optional<Manufacturer> manufacturerExists = repository.findManufacturerByName(manufacturer.getName());
        if(manufacturerExists.isPresent()){
            throw new IllegalStateException("Manufacturer " + manufacturer.getName() + " already exists.");
        }
        repository.save(manufacturer);
    }

    public void deleteManufacturer(String name) {
        Optional<Manufacturer> manufacturerExists = repository.findManufacturerByName(name);
        if(manufacturerExists.isPresent()){
            repository.deleteById(manufacturerExists.get().getId().toString());
        }else{
            throw new IllegalStateException("Manufacturer " + name + " does not exists.");
        }
    }

    @Transactional
    public void updateManufacturer(String name, Manufacturer newManufacturer){
        Optional<Manufacturer> manufacturerExists = repository.findManufacturerByName(name);
        if(!manufacturerExists.isPresent()){
            throw new IllegalStateException("Manufacturer " + name + " does not exists.");
        }
        Manufacturer manufacturer = manufacturerExists.get();
        if(newManufacturer.getName() != null && newManufacturer.getName().length()>0 &&
                !Objects.equals(manufacturer.getName() ,newManufacturer.getName())){
            manufacturer.setName(newManufacturer.getName() );
        }
        if(newManufacturer.getNationality() != null && newManufacturer.getNationality().length()>0 &&
                !Objects.equals(manufacturer.getNationality(),newManufacturer.getNationality())){
            manufacturer.setNationality(newManufacturer.getNationality());
        }

        repository.save(manufacturer);
    }
}
