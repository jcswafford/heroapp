package com.d288.heroapp.repo;

import com.d288.heroapp.dao.HerovillainDao;
import com.d288.heroapp.entity.HeroVillain;
import com.d288.heroapp.mapper.HeroVillainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HerovillainImpl implements HerovillainDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public HeroVillain getHerovillain(int id) {
        try {
            final String getHerovillain = "select * from herovillain where id = ?";
            return jdbcTemplate.queryForObject(getHerovillain, new HeroVillainMapper(), id);
        }
        catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<HeroVillain> getallherovillains() {
        final String getAllHeroVillains = "select * from herovillain";
        return jdbcTemplate.query(getAllHeroVillains, new HeroVillainMapper());
    }

    @Override
    @Transactional
    public HeroVillain addherovillain(HeroVillain h) {
        final String addHerovillain = "INSERT INTO herovillain(name, description, superpower, organizationId) " +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(addHerovillain,
                h.getName(),
                h.getDescription(),
                h.getSuperpower(),
                h.getOrganizationId());

        int hvId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        h.setId(hvId);
        return h;
    }

    @Override
    public void updateherovillain(HeroVillain h) {
        final String update_herovillain = "UPDATE herovillain SET name = ?, description = ?, superpower = ?, "
                + "organizationId = ? WHERE id = ?";
        jdbcTemplate.update(update_herovillain,
                h.getName(),
                h.getDescription(),
                h.getSuperpower(),
                h.getOrganizationId(),
                h.getId());
    }

    @Override
    @Transactional
    public void deleteHerovillainById(int id) {
        final String delete_herovillain_from_sightings = "DELETE FROM sighting WHERE " +
                "heroId = ?";
        jdbcTemplate.update(delete_herovillain_from_sightings, id);

        final String query = "DELETE FROM herovillain WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
