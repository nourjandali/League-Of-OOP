package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Deflect extends Ability {
  char terrainType;
  float percentage;
  int round;

  public Deflect(int level, int round, char terrainType) {
    this.percentage = Math.min(0.35f + (level * 0.02f), 0.7f);
    modifiers.put(HeroesType.Rogue, 0.2f);
    modifiers.put(HeroesType.Knight, 0.4f);
    modifiers.put(HeroesType.Pyromancer, 0.3f);
    modifiers.put(HeroesType.Wizard, 0.0f);
    this.terrainType = terrainType;
    this.round = round;
  }

  @Override
  public float execute() {
    return 0;
  }

  @Override
  public float execute(Hero enemyHero) {
    float deflectedDamage = enemyHero.getTotalDamageWithoutModifier(this.terrainType, this.round);
    return (this.percentage * deflectedDamage * (1.0f + modifiers.get(enemyHero.getType())));
  }

  @Override
  public float executeOvertimeAbility(Hero enemyHero) {
    return 0;
  }
}
