package com.d288.heroapp.entity;

import lombok.Data;

@Data
public class Location {

    private String name;
    private int locationId;
    private String description;
    private String street;
    private int streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private float latitude;
    private float longitude;

}
