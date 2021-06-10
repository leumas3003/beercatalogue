package com.sms.beercatalogue.controller;

import com.sms.beercatalogue.table.Manufacturer;
import com.sms.beercatalogue.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@AllArgsConstructor
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @GetMapping
    public List<Manufacturer> fetchAllManufacturers(){
        return manufacturerService.getAllManufacturers();
    }

    @PostMapping
    public void addManufacturer(@RequestBody Manufacturer manufacturer){
        manufacturerService.addingManufacturer(manufacturer);
    }

    @DeleteMapping(path="{name}")
    public void deleteManufacturer(@PathVariable("name") String name){
        manufacturerService.deleteManufacturer(name);
    }

    @PutMapping(path="{name}")
    public void updateBeer(@PathVariable("name") String name,
                           @RequestBody Manufacturer manufacturer){
        manufacturerService.updateManufacturer(name, manufacturer);
    }
}
