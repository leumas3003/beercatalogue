package com.sms.beercatalogue.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name= "beer")
public class Beer {

    @Id
    @SequenceGenerator(
            name = "beer_sequence",
            sequenceName = "beer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "beer_sequence"
    )
    private Long id;
    private String beerName;
    private String graduation;
    private String type;
    private String description;
    private Long manufacturer;

    public Beer(String beerName,
                String graduation,
                String type,
                String description,
                Long manufacturer) {
        this.beerName = beerName;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
        this.manufacturer = manufacturer;
    }
}
