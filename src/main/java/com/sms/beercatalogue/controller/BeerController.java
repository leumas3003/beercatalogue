package com.sms.beercatalogue.controller;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void addBeer(@RequestBody Beer beer){
        beerService.addingBeer(beer);
    }

    @DeleteMapping(path="{beerName}")
    public void deleteBeer(@PathVariable("beerName") String beerName){
        beerService.deleteBeer(beerName);
    }

    @PutMapping(path="{beerName}")
    public void updateBeer(@PathVariable("beerName") String beerName,
                           @RequestBody Beer beer){
        beerService.updateBeer(beerName, beer);
    }
}
