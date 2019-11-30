package com.etapa1.main.Abilities;

import com.etapa1.main.Heroes.HeroesType;

public class Fireblast extends Ability {
  double damage;

  public Fireblast(int level) {
    this.damage = 350 + (level * 50);
    modifiers.put(HeroesType.Rogue, -0.2);
    modifiers.put(HeroesType.Knight, 0.2);
    modifiers.put(HeroesType.Pyromancer, -0.1);
    modifiers.put(HeroesType.Wizard, 0.05);
  }

  @Override
  public double execute(HeroesType enemyType) {
    return this.damage + (this.damage * modifiers.get(enemyType));
  }

}
