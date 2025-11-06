package com.d288.heroapp.dao;

import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Organization;
import com.d288.heroapp.entity.SightingLocation;

import java.util.List;

public interface SightingLocationDao {

    SightingLocation getLocationById(int id);
    List<SightingLocation> getAllLocations();
    SightingLocation addLocation(SightingLocation l);
    void updateLocation(SightingLocation l);
    void deleteLocationById(int id);

    List<SightingLocation> getLocationForHeroVillain(HeroVillain heroVillain);
    List<SightingLocation> getLocationForOrganization(Organization organization);

}
