package com.d288.heroapp.repo;

import com.d288.heroapp.dao.SightingDao;
import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Sighting;
import com.d288.heroapp.entity.SightingLocation;
import com.d288.heroapp.mapper.SightingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SightingImpl implements SightingDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String getSightingById = "SELECT * FROM sighting WHERE id = ?";
            return jdbcTemplate.queryForObject(getSightingById, new SightingMapper(), id);
        }
        catch (DataAccessException exception) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String getAllSightings = "SELECT * FROM sighting";
        return jdbcTemplate.query(getAllSightings, new SightingMapper());
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String addSighting = "INSERT INTO sighting(locationId, heroId, date) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(addSighting,
                sighting.getLocationId(),
                sighting.getHeroId(),
                sighting.getDate());

        int newSighting = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newSighting);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String updateSighting = "UPDATE sighting SET heroId = ?, locationId = ?, date = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(updateSighting,
                sighting.getHeroId(),
                sighting.getLocationId(),
                sighting.getDate(),
                sighting.getId());
    }

    @Override
    public void deleteSighting(Sighting sighting) {
        final String deleteSighting = "DELETE FROM sighting WHERE id = ?";
        jdbcTemplate.update(deleteSighting, sighting.getId());
    }

    @Override
    public List<Sighting> getSightingsByLocation(SightingLocation sightingLocation) {
        final String getSightingsByLocation = "SELECT * FROM sighting WHERE locationId = ?";
        return jdbcTemplate.query(getSightingsByLocation, new SightingMapper(),
                sightingLocation.getLocationId());
    }

    @Override
    public List<Sighting> getSightingsByHeroVillain(HeroVillain heroVillain) {
        final String getSightingsByHeroVillain = "SELECT * FROM sighting WHERE heroId = ?";
        return jdbcTemplate.query(getSightingsByHeroVillain, new SightingMapper(),
                heroVillain.getId());
    }
}
