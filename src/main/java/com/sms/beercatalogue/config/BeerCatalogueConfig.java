package com.sms.beercatalogue.config;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.document.Manufacturer;
import com.sms.beercatalogue.repository.BeerRepository;
import com.sms.beercatalogue.repository.ManufacturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Configuration
public class BeerCatalogueConfig {

    @Bean
    CommandLineRunner runner(
            BeerRepository repository,
            ManufacturerRepository manufacturerRepository,
            MongoTemplate mongoTemplate){
        return args -> {
            Manufacturer ccc = new Manufacturer("CCC", "Spain");
            insertManufacturer(manufacturerRepository, ccc);
            Manufacturer germany = new Manufacturer("Paulaner Factory", "Germany");
            insertManufacturer(manufacturerRepository, germany);
            Manufacturer england = new Manufacturer("Heineken Factory", "England");
            insertManufacturer(manufacturerRepository, england);

            Beer tropical = new Beer(
                    "Tropical",
                    "7.8",
                    "Rubia",
                    "Cerveza Rubia de canarias",
                    ccc
            );
            insertBeer(repository,tropical);
            Beer dorada = new Beer(
                    "Dorada",
                    "7.2",
                    "Rubia",
                    "Cerveza Rubia de canarias",
                    ccc
            );
            insertBeer(repository,dorada);
            Beer paulaner = new Beer(
                    "Paulaner",
                    "6.8",
                    "Rubia",
                    "Cerveza Rubia de Alemania",
                    germany
            );
            insertBeer(repository,paulaner);
            Beer heineken = new Beer(
                    "Heineken",
                    "4.8",
                    "Rubia",
                    "Cerveza Rubia de Inglaterra",
                    england
            );
            insertBeer(repository,heineken);
            //usingMongoTemplate(repository, mongoTemplate, beerName, beer);
        };
    }

    /* Check if Beer already exist using mongotemplate
    private void usingMongoTemplate(BeerRepository repository, MongoTemplate mongoTemplate, String beerName, Beer beer) {
        Query query = new Query();
        query.addCriteria(Criteria.where("beerName").is(beerName));
        List<Beer> beers = mongoTemplate.find(query, Beer.class);

        if(beers.size()>1){
            throw new IllegalStateException("Beer with name " + beerName + " already insert.");
        }

        if(beers.isEmpty()){
            System.out.println("Inserting beer");
            repository.insert(beer);
        }else{
            System.out.println("Beer " + beerName + " already exists.");
        }
    }*/

    private void insertManufacturer(ManufacturerRepository repository, Manufacturer manufacturer){
        repository.findManufacturerByName(manufacturer.getName())
                .ifPresentOrElse(m -> System.out.println("Manufacturer " + manufacturer.getName() + " already exists."), () -> {
                    System.out.println("Inserting Manufacuter " + manufacturer.getName());
                    repository.insert(manufacturer);
                });
    }

    private void insertBeer(BeerRepository repository, Beer beer){
        repository.findBeerByBeerName(beer.getBeerName())
                .ifPresentOrElse(b -> System.out.println("Beer " + b.getBeerName() + " already exists."), () ->{
                    System.out.println("Inserting beer");
                    repository.insert(beer);
                });
    }
}
