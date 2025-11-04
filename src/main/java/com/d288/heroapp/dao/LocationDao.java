package com.d288.heroapp.dao;

import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Organization;

import javax.xml.stream.Location;
import java.util.List;

public interface LocationDao {

    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location l);
    void updateLocation(Location l);
    void deleteLocationById(Location l);

    List<Location> getLocationForHeroVillain(HeroVillain heroVillain);
    List<Location> getLocationForOrganization(Organization organization);

}
