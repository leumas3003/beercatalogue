package com.sms.beercatalogue;

import com.sms.beercatalogue.document.Beer;
import com.sms.beercatalogue.document.Manufacturer;
import com.sms.beercatalogue.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class BeerCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerCatalogueApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(
			BeerRepository repository,
			MongoTemplate mongoTemplate){
		return args -> {
			String beerName = "Tropical";
			Beer beer = new Beer(
					beerName,
					"7.8",
					"Rubia",
					"Cerveza Rubia de canarias",
					new Manufacturer(
							"CCC",
							"Spain"
					)
			);

			//usingMongoTemplate(repository, mongoTemplate, beerName, beer);
			repository.findBeerByBeerName(beerName)
					.ifPresentOrElse(b -> System.out.println("Beer " + b.getBeerName() + " already exists."), () ->{
						System.out.println("Inserting beer");
						repository.insert(beer);
					});
		};
	}

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
	}
}
