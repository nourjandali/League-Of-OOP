package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

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
  public double execute() {
    return this.damage;
  }

  @Override
  public double execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }
}
