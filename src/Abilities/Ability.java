package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

import java.util.HashMap;

public abstract class Ability {
    protected HashMap<HeroesType, Float> modifiers = new HashMap<HeroesType, Float>();
    public abstract float execute();
    public abstract float execute(Hero enemyHero);
    public abstract float executeOvertimeAbility(Hero enemyHero, char terrainType);
}
