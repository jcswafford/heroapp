package com.d288.heroapp.repo;

import com.d288.heroapp.dao.SightingLocationDao;
import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Organization;
import com.d288.heroapp.entity.SightingLocation;
import com.d288.heroapp.mapper.SightingLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SightingLocationImpl implements SightingLocationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SightingLocation getLocationById(int id) {
        try {
            final String getLocationById = "SELECT * FROM location WHERE locationId = ?";
            return jdbcTemplate.queryForObject(getLocationById, new SightingLocationMapper(), id);
        }
        catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SightingLocation> getAllLocations() {
        final String getAllLocations = "SELECT * FROM location";
        return jdbcTemplate.query(getAllLocations, new SightingLocationMapper());
    }

    @Override
    @Transactional
    public SightingLocation addLocation(SightingLocation l) {
        final String addLocation = "INSERT INTO location(name, description, street, " +
                "streetNumber, city, state, zipCode, latitude, longitude) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(addLocation,
                l.getName(),
                l.getDescription(),
                l.getStreet(),
                l.getStreetNumber(),
                l.getCity(),
                l.getState(),
                l.getZipCode(),
                l.getLatitude(),
                l.getLongitude());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        l.setLocationId(newId);
        return l;
    }

    @Override
    public void updateLocation(SightingLocation l) {
        final String update_location = "UPDATE location SET name = ?, description = ?, " +
                "street = ?, streetNumber = ?, city = ?, state = ?, zipCode = ?, " +
                "latitude = ?, longitude = ? WHERE locationId = ?";
        jdbcTemplate.update(update_location,
                l.getName(),
                l.getDescription(),
                l.getStreet(),
                l.getStreetNumber(),
                l.getCity(),
                l.getState(),
                l.getZipCode(),
                l.getLatitude(),
                l.getLongitude(),
                l.getLocationId());
    }

    @Override
    public void deleteLocationById(int id) {
        final String deleteLocation = "DELETE FROM location WHERE locationId = ?";
        jdbcTemplate.update(deleteLocation, id);
    }

    @Override
    public List<SightingLocation> getLocationForHeroVillain(HeroVillain heroVillain) {
        final String getLocationForHeroVillain = "SELECT l.name, l.locationId, " +
                "l.description, l.street, l.streetNumber, l.city, l.state, l.zipCode, " +
                "l.latitude, l.longitude FROM location l" +
                "JOIN sighting s on l.locationId = s.locationId " +
                "WHERE s.heroId = ?";
        return jdbcTemplate.query(getLocationForHeroVillain, new SightingLocationMapper(), heroVillain.getId());
    }

    @Override
    public List<SightingLocation> getLocationForOrganization(Organization organization) {
        final String getLocationForOrganization = "SELECT l.name, l.locationId, " +
                "l.description, l.street, l.streetNumber, l.city, l.state, l.zipCode, " +
                "l.latitude, l.longitude FROM location l " +
                "JOIN sighting s on l.locationId = s.locationId " +
                "JOIN herovillain v on v.id = s.heroId " +
                "WHERE v.organizationId = ?";
        return jdbcTemplate.query(getLocationForOrganization, new SightingLocationMapper(), organization.getId());
    }
}
