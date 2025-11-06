package com.d288.heroapp.dao;

import com.d288.heroapp.entity.HeroVillain;

import java.util.List;

public interface HerovillainDao {

    HeroVillain getHerovillain(int id);
    List<HeroVillain> getallherovillains();
    HeroVillain addherovillain(HeroVillain h);
    void updateherovillain(HeroVillain h);
    void deleteHerovillainById(int id);

}
