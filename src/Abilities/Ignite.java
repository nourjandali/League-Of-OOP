package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

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
  public double execute() {
    return this.damage;
  }

  @Override
  public double execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }
}
