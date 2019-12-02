package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Backstab extends Ability {
  private float damage;

  public Backstab(final int level) {
    this.damage = Constants.BACKSTAB_DAMAGE
            + (level * Constants.BACKSTAB_DAMAGE_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.BACKSTAB_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.BACKSTAB_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.BACKSTAB_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.BACKSTAB_WIZARD);
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
    return 0;
  }
}
