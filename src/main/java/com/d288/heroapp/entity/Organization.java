package com.d288.heroapp.entity;

import lombok.Data;

import java.util.List;

@Data
public class Organization {

    private String name;
    private int id;
    private String description;
    private String street;
    private int streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private List<HeroVillain> HeroVillains;

}
