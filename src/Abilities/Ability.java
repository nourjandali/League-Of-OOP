package Abilities;

import Heroes.HeroesType;

import java.util.HashMap;

public abstract class Ability {
    protected HashMap<HeroesType, Double> modifiers = new HashMap<HeroesType, Double>();
    public abstract double execute(HeroesType enemyType);
}
