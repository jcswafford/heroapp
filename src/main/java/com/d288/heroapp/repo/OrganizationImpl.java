package com.d288.heroapp.repo;

import com.d288.heroapp.dao.OrganizationDao;
import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Organization;
import com.d288.heroapp.entity.SightingLocation;
import com.d288.heroapp.mapper.HeroVillainMapper;
import com.d288.heroapp.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class OrganizationImpl implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String getOrganizationById = "SELECT * FROM organization WHERE id = ?";
            return jdbcTemplate.queryForObject(getOrganizationById, new OrganizationMapper(), id);
        }
        catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String getAllOrganizations = "SELECT * FROM organization";
        return jdbcTemplate.query(getAllOrganizations, new OrganizationMapper());
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String addOrganization = "INSERT INTO organization (name, description, street, " +
                "streetNumber, city, state, zipCode, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(addOrganization,
                organization.getName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getStreetNumber(),
                organization.getCity(),
                organization.getState(),
                organization.getZipCode(),
                organization.getPhone());

        int orgId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(orgId);
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String updateOrganization = "UPDATE organization SET name = ?, description = ?, " +
                "street = ?, streetNumber = ?, city = ?, state = ?, zipCode = ?, phone = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(updateOrganization,
                organization.getName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getStreetNumber(),
                organization.getCity(),
                organization.getState(),
                organization.getZipCode(),
                organization.getPhone(),
                organization.getId());
    }

    @Override
    public void deleteOrganization(Organization organization) {
        final String deleteOrganization = "DELETE FROM organization WHERE id = ?";
        jdbcTemplate.update(deleteOrganization, organization.getId());
    }

    @Override
    public List<Organization> getOrganizationForHeroVillain(HeroVillain heroVillain) {
        final String getOrganizationForHeroVillain = "SELECT o.name, o.id, o.description," +
                "o.street, o.streetNumber, o.city, o.state, o.zipCode, o.phone FROM organization o " +
                "JOIN herovillain v ON o.id = v.organizationId WHERE v.name = ?";
        return jdbcTemplate.query(getOrganizationForHeroVillain, new OrganizationMapper(), heroVillain.getName());
    }

    @Override
    public List<Organization> getOrganizationForLocation(SightingLocation sightingLocation) {
        final String getOrganizationForLocation = "SELECT o.name, o.id, o.description," +
                "o.street, o.streetNumber, o.city, o.state, o.zipCode, o.phone FROM organization o " +
                "JOIN herovillain v on o.id = v.organizationId " +
                "JOIN sighting s on s.heroId = v.id " +
                "JOIN location l on s.locationId = l.locationId " +
                "WHERE l.locationId = ?";
        return jdbcTemplate.query(getOrganizationForLocation, new OrganizationMapper(), sightingLocation.getLocationId());
    }

    @Override
    public List<HeroVillain> getHeroVillainsForOrganization(Organization organization) {
        final String getHeroVillainsForOrganization = "SELECT * FROM herovillain WHERE organizationId = ?";
        return jdbcTemplate.query(getHeroVillainsForOrganization, new HeroVillainMapper(), organization.getId());
    }
}
