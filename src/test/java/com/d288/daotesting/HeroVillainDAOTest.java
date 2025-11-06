package com.d288.daotesting;

import com.d288.heroapp.HeroappApplication;
import com.d288.heroapp.dao.HerovillainDao;
import com.d288.heroapp.dao.SightingLocationDao;
import com.d288.heroapp.dao.OrganizationDao;
import com.d288.heroapp.dao.SightingDao;
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
public class HeroVillainDAOTest {

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
    public void testAddAndGetHeroVillain() {
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

        HeroVillain fromDao = herovillainDao.getHerovillain(hv.getId());

        assertEquals(hv, fromDao);
    }

    @Test
    public void testGetAllHeroVillains() {
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
        hv.setDescription("HeroVillain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        HeroVillain hv2 = new HeroVillain();
        hv2.setName("HeroVillain 2");
        hv2.setDescription("HeroVillain description");
        hv2.setSuperpower("superpower2");
        hv2.setOrganizationId(o.getId());
        hv2 = herovillainDao.addherovillain(hv2);

        List<HeroVillain> allHeroVillains = herovillainDao.getallherovillains();
        assertEquals(allHeroVillains.size(), 2);
        assertTrue(allHeroVillains.contains(hv));
        assertTrue(allHeroVillains.contains(hv2));
    }

    @Test
    public void testUpdateHeroVillain() {
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
        hv.setDescription("HeroVillain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        HeroVillain fromDao = herovillainDao.getHerovillain(hv.getId());

        assertEquals(hv, fromDao);

        hv.setName("New Name");
        hv.setDescription("New Description");
        herovillainDao.updateherovillain(hv);

        assertNotEquals(hv, fromDao);

        fromDao = herovillainDao.getHerovillain(hv.getId());
        assertEquals(hv, fromDao);
    }

    @Test
    public void testDeleteHeroVillain() {
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
        hv.setDescription("HeroVillain description");
        hv.setSuperpower("superpower");
        hv.setOrganizationId(o.getId());
        hv = herovillainDao.addherovillain(hv);

        HeroVillain fromDao = herovillainDao.getHerovillain(hv.getId());
        assertEquals(hv, fromDao);

        herovillainDao.deleteHerovillainById(hv.getId());

        fromDao = herovillainDao.getHerovillain(hv.getId());
        assertNull(fromDao);
    }
}
