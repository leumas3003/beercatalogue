package com.sms.beercatalogue.service;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BeerService {

    private final BeerRepository repository;
    public List<Beer> getAllBeers(){
        return repository.findAll();
    }

    public void addingBeer(Beer beer){
        Optional<Beer> beerExist = repository.findBeerByBeerName(beer.getBeerName());
        if(beerExist.isPresent()){
            throw new IllegalStateException("Beer " + beer.getBeerName() + " already exists.");
        }
        repository.insert(beer);
    }

    public void deleteBeer(String beerName) {
        Optional<Beer> beer = repository.findBeerByBeerName(beerName);
        if(beer.isPresent()){
            repository.deleteById(beer.get().getId());
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
        if(beerNew.getManufacturer() != null){
            if(beerNew.getManufacturer().getName() != null && beerNew.getManufacturer().getName().length()>0 &&
                    !Objects.equals(beer.getManufacturer().getName(),beerNew.getManufacturer().getName())){
                beer.getManufacturer().setName(beerNew.getManufacturer().getName());
            }
            if(beerNew.getManufacturer().getNationality() != null && beerNew.getManufacturer().getNationality().length()>0 &&
                    !Objects.equals(beer.getManufacturer().getNationality(),beerNew.getManufacturer().getNationality())){
                beer.getManufacturer().setNationality(beerNew.getManufacturer().getNationality());
            }
        }
        repository.save(beer);
    }
}
