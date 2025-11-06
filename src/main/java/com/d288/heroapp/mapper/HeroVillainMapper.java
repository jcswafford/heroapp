package com.d288.heroapp.mapper;

import com.d288.heroapp.entity.HeroVillain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class HeroVillainMapper implements RowMapper<HeroVillain> {

    @Override
    public HeroVillain mapRow(ResultSet rs, int index) throws SQLException {
        HeroVillain hv = new HeroVillain();
        hv.setId(rs.getInt("id"));
        hv.setName(rs.getString("name"));
        hv.setDescription(rs.getString("description"));
        hv.setSuperpower(rs.getString("superpower"));
        hv.setOrganizationId(rs.getInt("organizationId"));

        return hv;
    }
}