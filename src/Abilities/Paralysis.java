package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Paralysis extends Ability {
  double damage;

  public Paralysis(int level) {
    this.damage = 40 + (level * 10);
    modifiers.put(HeroesType.Rogue, -0.2);
    modifiers.put(HeroesType.Knight, 0.2);
    modifiers.put(HeroesType.Pyromancer, -0.1);
    modifiers.put(HeroesType.Wizard, 0.05);
  }

  @Override
  public double execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }
}
