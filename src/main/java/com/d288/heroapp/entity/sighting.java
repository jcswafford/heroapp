package com.d288.heroapp.entity;

import lombok.Data;

import java.util.Date;

@Data
public class sighting {

    private int id;
    private int locationId;
    private int heroId;
    private Date date;

}
