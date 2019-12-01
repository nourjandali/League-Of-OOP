package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Backstab extends Ability {
  double damage;

  public Backstab(int level) {
    this.damage = 200 + (level * 20);
    modifiers.put(HeroesType.Rogue, 0.2);
    modifiers.put(HeroesType.Knight, -0.1);
    modifiers.put(HeroesType.Pyromancer, 0.25);
    modifiers.put(HeroesType.Wizard, 0.25);
  }

  @Override
  public double execute() {
    return this.damage;
  }

  @Override
  public double execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }
}
