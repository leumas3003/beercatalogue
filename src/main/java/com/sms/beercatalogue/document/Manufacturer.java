package com.sms.beercatalogue.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

    private String id;
    private String name;
    private String nationality;

    public Manufacturer(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}
