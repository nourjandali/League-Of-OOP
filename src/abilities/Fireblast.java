package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Fireblast extends Ability {
  private float damage;

  public Fireblast(final int level) {
    this.damage = Constants.FIREBLAST_DAMAGE + (level * Constants.FIREBLAST_DAMAGE_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.FIREBLAST_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.FIREBLAST_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.FIREBLAST_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.FIREBLAST_WIZARD);
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
