package com.sms.beercatalogue.service;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class BeerService {

    private final BeerRepository repository;
    public List<Beer> getAllBeers(){
        return repository.findAll();
    }
}
