package abilities;

import heroes.Hero;
import heroes.HeroesType;

public final class Slam extends Ability {
  private float damage;

  public Slam(final int level) {
    this.damage = 100 + (level * 40);
    modifiers.put(HeroesType.Rogue, -0.2f);
    modifiers.put(HeroesType.Knight, 0.2f);
    modifiers.put(HeroesType.Pyromancer, -0.1f);
    modifiers.put(HeroesType.Wizard, 0.05f);
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
    enemyHero.setStunned(true);
    return 0;
  }
}
