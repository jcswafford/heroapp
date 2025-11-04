package com.d288.heroapp.entity;

import lombok.Data;

import java.util.List;

@Data
public class herovillain {

    private int id;
    private String name;
    private String description;
    private String superpower;
    private int organizationId;
    private List<organization> organizations;

}
