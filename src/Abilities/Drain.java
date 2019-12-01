package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Drain extends Ability {
  float percentage;

  public Drain(int level) {
    this.percentage = 0.2f + (level * 0.05f);
    modifiers.put(HeroesType.Rogue, -0.2f);
    modifiers.put(HeroesType.Knight, 0.2f);
    modifiers.put(HeroesType.Pyromancer, -0.1f);
    modifiers.put(HeroesType.Wizard, 0.05f);
  }

  @Override
  public float execute() {
    return 0;
  }

  @Override
  public float execute(Hero enemyHero) {
    float basicHP = Math.min(0.3f * enemyHero.getInitHP(), enemyHero.getHP());
    float totalPercentage =
        this.percentage + (this.percentage * modifiers.get(enemyHero.getType()));
    return (basicHP * totalPercentage);
  }

  @Override
  public float executeOvertimeAbility(Hero enemyHero) {
    return 0;
  }
}
