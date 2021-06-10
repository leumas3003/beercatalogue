package com.sms.beercatalogue.config;

import com.sms.beercatalogue.table.Beer;
import com.sms.beercatalogue.table.Manufacturer;
import com.sms.beercatalogue.repository.BeerRepository;
import com.sms.beercatalogue.repository.ManufacturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeerCatalogueConfig {

    @Bean
    CommandLineRunner runner(
            BeerRepository repository,
            ManufacturerRepository manufacturerRepository){
        return args -> {
            Manufacturer ccc = new Manufacturer( "CCC", "Spain");
            Manufacturer germany = new Manufacturer("Paulaner Factory", "Germany");
            Manufacturer england = new Manufacturer("Heineken Factory", "England");
            manufacturerRepository.saveAll(List.of(ccc, germany, england));

            Beer tropical = new Beer(
                    "Tropical",
                    "7.8",
                    "Rubia",
                    "Cerveza Rubia de canarias",
                    manufacturerRepository.findManufacturerByName("CCC").get().getId()
            );

            Beer dorada = new Beer(
                    "Dorada",
                    "7.2",
                    "Rubia",
                    "Cerveza Rubia de canarias",
                    manufacturerRepository.findManufacturerByName("CCC").get().getId()
            );

            Beer paulaner = new Beer(
                    "Paulaner",
                    "6.8",
                    "Rubia",
                    "Cerveza Rubia de Alemania",
                    manufacturerRepository.findManufacturerByName("Paulaner Factory").get().getId()
            );

            Beer heineken = new Beer(
                    "Heineken",
                    "4.8",
                    "Rubia",
                    "Cerveza Rubia de Inglaterra",
                    manufacturerRepository.findManufacturerByName("Heineken Factory").get().getId()

            );
            repository.saveAll(List.of(tropical, dorada, paulaner, heineken));

        };
    }
}
