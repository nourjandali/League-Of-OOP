package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Deflect extends Ability {
  char terrainType;
  double percentage;
  int round;

  public Deflect(int level,  int round, char terrainType) {
    this.percentage = Math.min(0.35 + (level * 0.20), 0.7);
    modifiers.put(HeroesType.Rogue, 0.2);
    modifiers.put(HeroesType.Knight, 0.4);
    modifiers.put(HeroesType.Pyromancer, 0.3);
    modifiers.put(HeroesType.Wizard, 0.0);
    this.terrainType = terrainType;
    this.round = round;
  }

  @Override
  public double execute() {
    return 0;
  }

  @Override
  public double execute(Hero enemyHero) {
    double deflectedDamage = enemyHero.getTotalDamageWithoutModifier(this.terrainType, this.round);
    return this.percentage * deflectedDamage * (1 + modifiers.get(enemyHero.getType()));
    //        return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }
}
