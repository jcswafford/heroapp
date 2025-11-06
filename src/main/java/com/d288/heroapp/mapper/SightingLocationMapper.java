package com.d288.heroapp.mapper;

import com.d288.heroapp.entity.SightingLocation;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class SightingLocationMapper implements RowMapper<SightingLocation> {
    @Override
    public SightingLocation mapRow(ResultSet rs, int rowNum) throws SQLException {

        SightingLocation l = new SightingLocation();
        l.setLocationId(rs.getInt("locationId"));
        l.setName(rs.getString("name"));
        l.setDescription(rs.getString("description"));
        l.setStreet(rs.getString("street"));
        l.setStreetNumber(Integer.parseInt(rs.getString("streetNumber")));
        l.setCity(rs.getString("city"));
        l.setState(rs.getString("state"));
        l.setZipCode(rs.getString("zipCode"));
        l.setLatitude(rs.getFloat("latitude"));
        l.setLongitude(rs.getFloat("longitude"));

        return l;
    }
}
