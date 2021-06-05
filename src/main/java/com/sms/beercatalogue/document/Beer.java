package com.sms.beercatalogue.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Beer {

    @Id
    private String id;
    @Indexed(unique = true)
    private String beerName;
    private String graduation;
    private String type;
    private String description;
    private Manufacturer manufacturer;

    public Beer(String beerName,
                String graduation,
                String type,
                String description,
                Manufacturer manufacturer) {
        this.beerName = beerName;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
        this.manufacturer = manufacturer;
    }
}
