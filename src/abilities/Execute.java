package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Execute extends Ability {
  private float damage;

  public Execute(final int level) {
    this.damage = Constants.EXECUTE_DAMAGE + (level * Constants.EXECUTE_DAMAGE_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.EXECUTE_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.EXECUTE_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.EXECUTE_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.EXECUTE_WIZARD);
  }

  @Override
  public float execute() {
    return this.damage;
  }

  @Override
  public float execute(final Hero enemyHero) {

    float hpLimit =
        Math.min(
            Constants.EXECUTE_HP_LIMIT_CONSTANT
                + (Constants.EXECUTE_HP_LIMIT_CONSTANT_PER_LEVEL * enemyHero.getLevel()),
            Constants.EXECUTE_HP_LIMIT_CONSTANT_MAX);
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
