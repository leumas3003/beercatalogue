package com.sms.beercatalogue.service;

import com.sms.beercatalogue.model.BeerBean;
import com.sms.beercatalogue.model.ManufacturerBean;
import com.sms.beercatalogue.table.Beer;
import com.sms.beercatalogue.repository.BeerRepository;
import com.sms.beercatalogue.table.Manufacturer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class BeerService {

    private final BeerRepository repository;
    private final ManufacturerService manufacturerService;

    public List<BeerBean> getAllBeers(){
        List<Beer> beers = repository.findAll();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        List<BeerBean> beersOutput = new ArrayList<>();
        for (Beer beerSQL: beers){
            BeerBean beer = new BeerBean();
            beer.setId(beerSQL.getId());
            beer.setBeerName(beerSQL.getBeerName());
            beer.setGraduation(beerSQL.getGraduation());
            beer.setType(beerSQL.getType());
            beer.setDescription(beerSQL.getDescription());
            ManufacturerBean manufacturer = new ManufacturerBean();
            Optional<Manufacturer> manu = manufacturers.stream().filter(m -> m.getId() == beerSQL.getManufacturer()).findFirst();
            if(manu.isPresent()){
                manufacturer.setName(manu.get().getName());
                manufacturer.setNationality(manu.get().getNationality());
                beer.setManufacturer(manufacturer);
            }
            beersOutput.add(beer);
        }

        return beersOutput;
    }

    public void addingBeer(Beer beer){
        Optional<Beer> beerExist = repository.findBeerByBeerName(beer.getBeerName());
        if(beerExist.isPresent()){
            throw new IllegalStateException("Beer " + beer.getBeerName() + " already exists.");
        }
        repository.save(beer);
    }

    public void deleteBeer(String beerName) {
        Optional<Beer> beer = repository.findBeerByBeerName(beerName);
        if(beer.isPresent()){
            repository.deleteById(beer.get().getId().toString());
        }else{
            throw new IllegalStateException("Beer " + beerName + " does not exists.");
        }
    }

    @Transactional
    public void updateBeer(String beerName, Beer beerNew){
        Optional<Beer> beerExists = repository.findBeerByBeerName(beerName);
        if(!beerExists.isPresent()){
            throw new IllegalStateException("Beer " + beerName + " does not exists.");
        }
        Beer beer = beerExists.get();
        if(beerNew.getGraduation() != null && beerNew.getGraduation().length()>0 &&
                !Objects.equals(beer.getGraduation(),beerNew.getGraduation())){
            beer.setGraduation(beerNew.getGraduation());
        }
        if(beerNew.getType() != null && beerNew.getType().length()>0 &&
                !Objects.equals(beer.getType(),beerNew.getType())){
            beer.setType(beerNew.getType());
        }
        if(beerNew.getDescription() != null && beerNew.getDescription().length()>0 &&
                !Objects.equals(beer.getDescription(),beerNew.getDescription())){
            beer.setDescription(beerNew.getDescription());
        }

        repository.save(beer);
    }
}
