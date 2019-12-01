package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

import java.util.HashMap;

public abstract class Ability {
    protected HashMap<HeroesType, Double> modifiers = new HashMap<HeroesType, Double>();
    public abstract double execute(Hero enemyHero);
}
