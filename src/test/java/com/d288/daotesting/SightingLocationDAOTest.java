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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeroappApplication.class)
public class SightingLocationDAOTest {

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
    public void testAddAndGetSightingLocation() {
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

        SightingLocation fromDao =  sightingLocationDao.getLocationById(sl.getLocationId());
        assertEquals(sl, fromDao);
    }

    @Test
    public void testGetAllSightingLocations() {
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

        SightingLocation sl2 = new SightingLocation();
        sl2.setName("Sighting Location2");
        sl2.setDescription("SL description2");
        sl2.setStreet("Street2");
        sl2.setStreetNumber(100);
        sl2.setCity("City2");
        sl2.setState("State2");
        sl2.setZipCode("88888");
        sl2.setLatitude(110.00f);
        sl2.setLongitude(110.00f);
        sl2 = sightingLocationDao.addLocation(sl2);

        List<SightingLocation> allSightingLocations =  sightingLocationDao.getAllLocations();
        assertEquals(2, allSightingLocations.size());
        assertTrue(allSightingLocations.contains(sl));
        assertTrue(allSightingLocations.contains(sl2));
    }

    @Test
    public void testUpdateSightingLocation() {
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

        SightingLocation fromDao = sightingLocationDao.getLocationById(sl.getLocationId());
        assertEquals(sl, fromDao);

        sl.setName("New Sighting Location");
        sl.setDescription("New Sighting Location");
        sightingLocationDao.updateLocation(sl);

        assertNotEquals(fromDao, sl);

        fromDao = sightingLocationDao.getLocationById(sl.getLocationId());
        assertEquals(sl, fromDao);
    }

    @Test
    public void testDeleteSightingLocation() {
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

        SightingLocation fromDao = sightingLocationDao.getLocationById(sl.getLocationId());
        assertEquals(sl, fromDao);

        sightingLocationDao.deleteLocationById(sl.getLocationId());
        fromDao = sightingLocationDao.getLocationById(sl.getLocationId());
        assertNull(fromDao);
    }
}
