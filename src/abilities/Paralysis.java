package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Paralysis extends Ability {
  private float damage;

  public Paralysis(final int level) {
    this.damage = Constants.PARALYSIS_DAMAGE + (level * Constants.PARALYSIS_DAMAGE_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.PARALYSIS_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.PARALYSIS_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.PARALYSIS_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.PARALYSIS_WIZARD);
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
