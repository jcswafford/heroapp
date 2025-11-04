package com.d288.heroapp.dao;

import java.util.List;

public interface HerovillainDao {

    HerovillainDao getherovillain(int id);
    List<HerovillainDao> getallherovillains();
    HerovillainDao addherovillain(HerovillainDao h);
    void updateherovillain(HerovillainDao h);
    void deleteHerovillainById(int id);

}
