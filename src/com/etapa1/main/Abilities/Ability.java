package com.etapa1.main.Abilities;

import com.etapa1.main.Heroes.HeroesType;

import java.util.HashMap;

public abstract class Ability {
    protected HashMap<HeroesType, Double> modifiers = new HashMap<HeroesType, Double>();
    public abstract double execute(HeroesType enemyType);
}
