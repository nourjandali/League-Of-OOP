package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Deflect extends Ability {
  private char terrainType;
  private float percentage;
  private int round;

  public Deflect(final int level, final int round, final char terrainType) {
    this.percentage =
        Math.min(
            Constants.DEFLECT_PERC + (level * Constants.DEFLECT_PERC_PER_LEVEL),
            Constants.DEFLECT_PERC_MAX);
    modifiers.put(HeroesType.Rogue, Constants.DEFLECT_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.DEFLECT_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.DEFLECT_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.DEFLECT_WIZARD);
    this.terrainType = terrainType;
    this.round = round;
  }

  @Override
  public float execute() {
    return 0;
  }

  @Override
  public float execute(final Hero enemyHero) {
    // Two wizard heroes do not give each other
    if (enemyHero.getType() == HeroesType.Wizard) {
      return 0;
    }

    // Apply deflect ability other heroes
    float deflectedDamage = enemyHero.getTotalDamageWithoutModifier(this.terrainType, this.round);
    return (this.percentage
        * deflectedDamage
        * (Constants.ONE + modifiers.get(enemyHero.getType())));
  }

  @Override
  public float executeOvertimeAbility(final Hero enemyHero, final char terrain) {
    // Deflect does not have overtime
    return 0;
  }
}
