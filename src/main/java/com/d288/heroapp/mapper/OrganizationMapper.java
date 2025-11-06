package com.d288.heroapp.mapper;

import com.d288.heroapp.entity.Organization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class OrganizationMapper implements RowMapper<Organization> {
    @Override
    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization organization = new Organization();
        organization.setId(rs.getInt("id"));
        organization.setName(rs.getString("name"));
        organization.setDescription(rs.getString("description"));
        organization.setStreet(rs.getString("street"));
        organization.setStreetNumber(rs.getInt("streetNumber"));
        organization.setCity(rs.getString("city"));
        organization.setState(rs.getString("state"));
        organization.setZipCode(rs.getString("zipCode"));
        organization.setPhone(rs.getString("phone"));

        return organization;
    }
}
