package com.sms.beercatalogue.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @SequenceGenerator(
            name = "manufacturer_sequence",
            sequenceName = "manufacturer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "manufacturer_sequence"
    )
    private Long id;
    private String name;
    private String nationality;

    public Manufacturer( String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}
