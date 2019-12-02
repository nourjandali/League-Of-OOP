package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Ignite extends Ability {
  private float damage;
  private int overtimeLevel;

  public Ignite(final int level, final int round) {
    this.damage = Constants.IGNITE_DAMAGE + (level * Constants.IGNITE_DAMAGE_PER_LEVEL);
    this.overtimeLevel = level;
    modifiers.put(HeroesType.Rogue, Constants.IGNITE_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.IGNITE_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.IGNITE_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.IGNITE_WIZARD);
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
    float terrainAmp = Constants.IGNITE_OVERTIME_TERRAIN_AMP;
    if (terrainType == 'V') {
      terrainAmp = Constants.IGNITE_OVERTIME_TERRAIN_AMP_VOLCANIC;
    }
    return ((Constants.IGNITE_OVERTIME_DAMAGE
                + (this.overtimeLevel * Constants.IGNITE_OVERTIME_DAMAGE_PER_LEVEL))
            * (1 + modifiers.get(enemyHero.getType())))
        * terrainAmp;
  }
}
