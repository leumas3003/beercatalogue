package com.sms.beercatalogue.controller;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/beer")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public List<Beer> fetchAllBeers(){
        return beerService.getAllBeers();
    }
}
