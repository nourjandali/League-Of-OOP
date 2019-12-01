package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Drain extends Ability {
  double percentage;

  public Drain(int level) {
    this.percentage = 0.2 + (level * 0.05);
    modifiers.put(HeroesType.Rogue, -0.2);
    modifiers.put(HeroesType.Knight, 0.2);
    modifiers.put(HeroesType.Pyromancer, -0.1);
    modifiers.put(HeroesType.Wizard, 0.05);
  }

  @Override
  public double execute() {
    return 0;
  }

  @Override
  public double execute(Hero enemyHero) {
    double basicHP = Math.min(0.3 * enemyHero.getInitHP(), enemyHero.getHP());
    double totalPercentage =
        this.percentage + (this.percentage * modifiers.get(enemyHero.getType()));
    return (basicHP * totalPercentage);
  }
}
