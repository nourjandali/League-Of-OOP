package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Backstab extends Ability {
  float damage;

  public Backstab(int level) {
    this.damage = 200 + (level * 20);
    modifiers.put(HeroesType.Rogue, 0.2f);
    modifiers.put(HeroesType.Knight, -0.1f);
    modifiers.put(HeroesType.Pyromancer, 0.25f);
    modifiers.put(HeroesType.Wizard, 0.25f);
  }

  @Override
  public float execute() {
    return this.damage;
  }

  @Override
  public float execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }

  @Override
  public float executeOvertimeAbility(Hero enemyHero) {
    return 0;
  }
}
