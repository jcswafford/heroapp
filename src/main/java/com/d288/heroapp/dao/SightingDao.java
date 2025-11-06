package com.d288.heroapp.dao;

import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.SightingLocation;
import com.d288.heroapp.entity.Sighting;

import java.util.List;

public interface SightingDao {

    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSighting(Sighting sighting);

    List<Sighting> getSightingsByLocation(SightingLocation sightingLocation);
    List<Sighting> getSightingsByHeroVillain(HeroVillain heroVillain);

}
