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
public class OrganizationDAOTest {

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
    public static void tearDownClass() {
    }

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
    public void tearDown() {
    }

    @Test
    public void testAddAndGetOrganization() {
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

        Organization fromDao = organizationDao.getOrganizationById(o.getId());

        assertEquals(o, fromDao);
    }

    @Test
    public void testGetAllOrganizations() {
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

        Organization o2 = new Organization();
        o2.setName("Test Organization2");
        o2.setDescription("Test Description2");
        o2.setStreet("Test Street2");
        o2.setStreetNumber(100);
        o2.setCity("Test City2");
        o2.setState("Test State2");
        o2.setZipCode("88882");
        o2.setPhone("Test Phone2");
        o2 = organizationDao.addOrganization(o2);

        List<Organization> allOrganizations = organizationDao.getAllOrganizations();
        assertEquals(allOrganizations.size(), 2);
        assertTrue(allOrganizations.contains(o));
        assertTrue(allOrganizations.contains(o2));
    }

    @Test
    public void testUpdateOrganization() {
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

        Organization fromDao = organizationDao.getOrganizationById(o.getId());
        assertEquals(o, fromDao);

        o.setDescription("Updated Description");
        o.setName("Updated Name");
        organizationDao.updateOrganization(o);

        assertNotEquals(o, fromDao);

        fromDao = organizationDao.getOrganizationById(o.getId());
        assertEquals(o, fromDao);
    }

    @Test
    public void testDeleteOrganization() {
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

        Organization fromDao = organizationDao.getOrganizationById(o.getId());
        assertEquals(o, fromDao);

        organizationDao.deleteOrganization(o);

        fromDao = organizationDao.getOrganizationById(o.getId());
        assertNull(fromDao);
    }
}