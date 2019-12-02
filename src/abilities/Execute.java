package abilities;

import heroes.Hero;
import heroes.HeroesType;

public final class Execute extends Ability {
  private float damage;

  public Execute(final int level) {
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
  public float execute(final Hero enemyHero) {

    float hpLimit = Math.min(0.2f + (0.01f * enemyHero.getLevel()), 0.4f);
    float hpPercentage = (float) enemyHero.getHP() / enemyHero.getInitHP();
    if (hpPercentage < hpLimit) {
      // Enemy take damage with all its health to die
      return enemyHero.getHP();
    }
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }

  @Override
  public float executeOvertimeAbility(final Hero enemyHero, final char terrainType) {
    // Execute does not have overtime
    return 0;
  }
}
