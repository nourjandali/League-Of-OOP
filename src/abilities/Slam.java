package abilities;

import heroes.Hero;
import heroes.HeroesType;
import main.Constants;

public final class Slam extends Ability {
  private float damage;

  public Slam(final int level) {
    this.damage = Constants.SLAM_DAMAGE + (level * Constants.SLAM_DAMAGE_PER_LEVEL);
    modifiers.put(HeroesType.Rogue, Constants.SLAM_ROGUE);
    modifiers.put(HeroesType.Knight, Constants.SLAM_KNIGHT);
    modifiers.put(HeroesType.Pyromancer, Constants.SLAM_PYROMANCER);
    modifiers.put(HeroesType.Wizard, Constants.SLAM_WIZARD);
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
