package com.d288.daotesting;

import com.d288.heroapp.HeroappApplication;
import com.d288.heroapp.dao.HerovillainDao;
import com.d288.heroapp.dao.OrganizationDao;
import com.d288.heroapp.dao.SightingDao;
import com.d288.heroapp.dao.SightingLocationDao;
import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.entity.Organization;
import com.d288.heroapp.entity.Sighting;
import com.d288.heroapp.entity.SightingLocation;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeroappApplication.class)
public class SightingDAOTest {

    @Autowired
    HerovillainDao herovillainDao;

    @Autowired
    SightingLocationDao sightingLocationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SightingDao sightingDao;

    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {
        List<HeroVillain> allHeroVillains = herovillainDao.getallherovillains();
        for (HeroVillain hv : allHeroVillains) {
            herovillainDao.deleteHerovillainById(hv.getId());
        }
        List<Organization> allOrganizations = organizationDao.getAllOrganizations();
        for (Organization o : allOrganizations) {
            organizationDao.deleteOrganization(o);
        }
        List<Sighting> allSightings = sightingDao.getAllSightings();
        for (Sighting sighting : allSightings) {
            sightingDao.deleteSighting(sighting);
        }
        List<SightingLocation> allSightingLocations = sightingLocationDao.getAllLocations();
        for (SightingLocation sightingLocation : allSightingLocations) {
            sightingLocationDao.deleteLocationById(sightingLocation.getLocationId());
        }
    }
    @After
    public void tearDown() {}

    @Test
    public void testAddAndGetSighting() {
        Organization o = new Organization();
        o.setName("Test Organization");
        o.setDescription("Test Description");
        o.setStreet("Test Street");
        o.setStreetNumber(100);
        o.setCity("Test City");
        o.setState("Test State");
        o.setZipCode("88888");
        o.setPhone("Test Phone");
        o = organizationDao.addOrganization(o);

        HeroVillain hv = new HeroVillain();
        hv.setName("Hero Villain");
        hv.setDescription("Hero Villain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        SightingLocation sl = new SightingLocation();
        sl.setName("Sighting Location");
        sl.setDescription("SL description");
        sl.setStreet("Street");
        sl.setStreetNumber(100);
        sl.setCity("City");
        sl.setState("State");
        sl.setZipCode("88888");
        sl.setLatitude(110.00f);
        sl.setLongitude(110.00f);
        sl = sightingLocationDao.addLocation(sl);

        Sighting s = new Sighting();
        s.setLocationId(sl.getLocationId());
        s.setHeroId(hv.getId());
        s.setDate(java.sql.Date.valueOf(LocalDate.now()));
        s = sightingDao.addSighting(s);

        Sighting fromDao = sightingDao.getSightingById(s.getId());

        assertEquals(fromDao, s);
    }

    @Test
    public void testGetAllSightings() {

        Organization o = new Organization();
        o.setName("Test Organization");
        o.setDescription("Test Description");
        o.setStreet("Test Street");
        o.setStreetNumber(100);
        o.setCity("Test City");
        o.setState("Test State");
        o.setZipCode("88888");
        o.setPhone("Test Phone");
        o = organizationDao.addOrganization(o);

        HeroVillain hv = new HeroVillain();
        hv.setName("Hero Villain");
        hv.setDescription("Hero Villain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        SightingLocation sl = new SightingLocation();
        sl.setName("Sighting Location");
        sl.setDescription("SL description");
        sl.setStreet("Street");
        sl.setStreetNumber(100);
        sl.setCity("City");
        sl.setState("State");
        sl.setZipCode("88888");
        sl.setLatitude(110.00f);
        sl.setLongitude(110.00f);
        sl = sightingLocationDao.addLocation(sl);

        Sighting s = new Sighting();
        s.setLocationId(sl.getLocationId());
        s.setHeroId(hv.getId());
        s.setDate(java.sql.Date.valueOf(LocalDate.now()));
        s = sightingDao.addSighting(s);

        Sighting s2 = new Sighting();
        s2.setLocationId(sl.getLocationId());
        s2.setHeroId(hv.getId());
        s2.setDate(java.sql.Date.valueOf(LocalDate.now()));
        s2 = sightingDao.addSighting(s2);

        List<Sighting> allSightings = sightingDao.getAllSightings();
        assertEquals(allSightings.size(), 2);
        assertTrue(allSightings.contains(s));
        assertTrue(allSightings.contains(s2));
    }

    @Test
    public void testUpdateSighting() {
        Organization o = new Organization();
        o.setName("Test Organization");
        o.setDescription("Test Description");
        o.setStreet("Test Street");
        o.setStreetNumber(100);
        o.setCity("Test City");
        o.setState("Test State");
        o.setZipCode("88888");
        o.setPhone("Test Phone");
        o = organizationDao.addOrganization(o);

        HeroVillain hv = new HeroVillain();
        hv.setName("Hero Villain");
        hv.setDescription("Hero Villain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        SightingLocation sl = new SightingLocation();
        sl.setName("Sighting Location");
        sl.setDescription("SL description");
        sl.setStreet("Street");
        sl.setStreetNumber(100);
        sl.setCity("City");
        sl.setState("State");
        sl.setZipCode("88888");
        sl.setLatitude(110.00f);
        sl.setLongitude(110.00f);
        sl = sightingLocationDao.addLocation(sl);

        Sighting s = new Sighting();
        s.setLocationId(sl.getLocationId());
        s.setHeroId(hv.getId());
        s.setDate(java.sql.Date.valueOf(LocalDate.now()));
        s = sightingDao.addSighting(s);

        Sighting fromDao = sightingDao.getSightingById(s.getId());
        assertEquals(fromDao, s);

        s.setDate(java.sql.Date.valueOf("2025-01-01"));
        sightingDao.updateSighting(s);

        assertNotEquals(fromDao, s);

        fromDao = sightingDao.getSightingById(s.getId());
        assertEquals(fromDao, s);
    }

    @Test
    public void testDeleteSighting() {
        Organization o = new Organization();
        o.setName("Test Organization");
        o.setDescription("Test Description");
        o.setStreet("Test Street");
        o.setStreetNumber(100);
        o.setCity("Test City");
        o.setState("Test State");
        o.setZipCode("88888");
        o.setPhone("Test Phone");
        o = organizationDao.addOrganization(o);

        HeroVillain hv = new HeroVillain();
        hv.setName("Hero Villain");
        hv.setDescription("Hero Villain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        SightingLocation sl = new SightingLocation();
        sl.setName("Sighting Location");
        sl.setDescription("SL description");
        sl.setStreet("Street");
        sl.setStreetNumber(100);
        sl.setCity("City");
        sl.setState("State");
        sl.setZipCode("88888");
        sl.setLatitude(110.00f);
        sl.setLongitude(110.00f);
        sl = sightingLocationDao.addLocation(sl);

        Sighting s = new Sighting();
        s.setLocationId(sl.getLocationId());
        s.setHeroId(hv.getId());
        s.setDate(java.sql.Date.valueOf(LocalDate.now()));
        s = sightingDao.addSighting(s);

        Sighting fromDao = sightingDao.getSightingById(s.getId());
        assertEquals(fromDao, s);

        sightingDao.deleteSighting(s);

        fromDao = sightingDao.getSightingById(s.getId());
        assertNull(fromDao);
    }
}
