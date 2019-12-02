package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Drain extends Ability {
  private float percentage;

  public Drain(final int level) {
    this.percentage = Constants.DRAIN_PERC + (level * Constants.DRAIN_PERC_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.DRAIN_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.DRAIN_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.DRAIN_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.DRAIN_WIZARD);
  }

  @Override
  public float execute() {
    return 0;
  }

  @Override
  public float execute(final Hero enemyHero) {
    float basicHP = Math.min(Constants.DRAIN_BASIC_HP * enemyHero.getInitHP(), enemyHero.getHP());
    float totalPercentage =
        this.percentage + (this.percentage * modifiers.get(enemyHero.getType()));
    return (basicHP * totalPercentage);
  }

  @Override
  public float executeOvertimeAbility(final Hero enemyHero, final char terrainType) {
    // Drain does not have overtime
    return 0;
  }
}
