package com.d288.heroapp.entity;


import lombok.Data;

@Data
public class HeroVillain {

    private int id;
    private String name;
    private String description;
    private String superpower;
    private int organizationId;

}
