package abilities;

import heroes.Hero;
import heroes.HeroesType;

public final class Deflect extends Ability {
  private char terrainType;
  private float percentage;
  private int round;

  public Deflect(final int level, final int round, final char terrainType) {
    this.percentage = Math.min(0.35f + (level * 0.02f), 0.7f);
    modifiers.put(HeroesType.Rogue, 0.2f);
    modifiers.put(HeroesType.Knight, 0.4f);
    modifiers.put(HeroesType.Pyromancer, 0.3f);
    modifiers.put(HeroesType.Wizard, 0.0f);
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
    return (this.percentage * deflectedDamage * (1.0f + modifiers.get(enemyHero.getType())));
  }

  @Override
  public float executeOvertimeAbility(final Hero enemyHero, final char terrainType) {
    // Deflect does not have overtime
    return 0;
  }
}
