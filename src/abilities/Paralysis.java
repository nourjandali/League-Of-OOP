package abilities;

import heroes.Hero;
import heroes.HeroesType;

public final class Paralysis extends Ability {
  private float damage;

  public Paralysis(final int level) {
    this.damage = 40 + (level * 10);
    modifiers.put(HeroesType.Rogue, -0.1f);
    modifiers.put(HeroesType.Knight, -0.2f);
    modifiers.put(HeroesType.Pyromancer, 0.2f);
    modifiers.put(HeroesType.Wizard, 0.25f);
  }

  @Override
  public float execute() {
    return this.damage;
  }

  @Override
  public float execute(final Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }

  @Override
  public float executeOvertimeAbility(final Hero enemyHero, final char terrainType) {
    float terrainAmp = 1;
    if (terrainType == 'W') {
      terrainAmp = 1.15f;
    }
    float totalDamage =
        (this.damage + (this.damage * modifiers.get(enemyHero.getType()))) * terrainAmp;
    enemyHero.setStunned(true);
    return Math.round(totalDamage);
  }
}
