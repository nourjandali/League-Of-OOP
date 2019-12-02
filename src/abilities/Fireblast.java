package abilities;

import heroes.Hero;
import heroes.HeroesType;

public final class Fireblast extends Ability {
  private float damage;

  public Fireblast(final int level) {
    this.damage = 350 + (level * 50);
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
    // Fireblast does not have overtime
    return 0;
  }
}
