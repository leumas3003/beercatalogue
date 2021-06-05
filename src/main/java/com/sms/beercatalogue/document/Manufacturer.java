package com.sms.beercatalogue.document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Manufacturer {
    private String name;
    private String nationality;
}
