package com.etapa1.main.Abilities;

import com.etapa1.main.Heroes.HeroesType;

public class Ignite extends Ability {
  double damage;

  public Ignite(int level, int round) {
    this.damage = 150 + (level * 20) + (round * 50) + (level * 30);
    modifiers.put(HeroesType.Rogue, -0.2);
    modifiers.put(HeroesType.Knight, 0.2);
    modifiers.put(HeroesType.Pyromancer, -0.1);
    modifiers.put(HeroesType.Wizard, 0.05);
  }

  @Override
  public double execute(HeroesType enemyType) {
    return this.damage + (this.damage * modifiers.get(enemyType));
  }

  @Override
  public double executeWithoutModifier() {
    return this.damage;
  }
}