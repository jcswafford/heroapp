package com.d288.heroapp.dao;

import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Location;
import com.d288.heroapp.entity.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganization(Organization organization);

    List<Organization> getOrganizationForHeroVillain(HeroVillain heroVillain);
    List<Organization> getOrganizationForLocation(Location location);
    List<HeroVillain> getHeroVillainsForOrganization(Organization organization);

}
