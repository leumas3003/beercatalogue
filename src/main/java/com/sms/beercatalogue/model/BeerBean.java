package com.sms.beercatalogue.model;

import lombok.Data;

@Data
public class BeerBean {

    private Long id;
    private String beerName;
    private String graduation;
    private String type;
    private String description;
    private ManufacturerBean manufacturer;
}
