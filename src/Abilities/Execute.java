package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Execute extends Ability {
  float damage;

  public Execute(int level) {
    this.damage = 200 + (level * 30);
    modifiers.put(HeroesType.Rogue, 0.15f);
    modifiers.put(HeroesType.Knight, 0.0f);
    modifiers.put(HeroesType.Pyromancer, 0.1f);
    modifiers.put(HeroesType.Wizard, -0.2f);
  }

  @Override
  public float execute() {
    return this.damage;
  }

  @Override
  public float execute(Hero enemyHero) {

    float hpLimit = Math.min(0.2f + (0.01f * enemyHero.getLevel()), 0.4f);
    float hpPercentage = (float) enemyHero.getHP() / enemyHero.getInitHP();
    if (hpPercentage < hpLimit) {
      // Enemy take damage with all their health to die
      return enemyHero.getHP();
    }
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }

  @Override
  public float executeOvertimeAbility(Hero enemyHero, char terrainType) {
    return 0;
  }
}