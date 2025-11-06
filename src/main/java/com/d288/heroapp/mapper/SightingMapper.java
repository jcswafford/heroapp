package com.d288.heroapp.mapper;

import com.d288.heroapp.entity.Sighting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightingMapper implements RowMapper<Sighting> {
    @Override
    public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sighting s =  new Sighting();
        s.setId(rs.getInt("id"));
        s.setLocationId(rs.getInt("locationId"));
        s.setHeroId(rs.getInt("heroId"));
        s.setDate(rs.getDate("date"));

        return s;
    }
}
