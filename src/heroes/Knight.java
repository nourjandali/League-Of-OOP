package heroes;

import abilities.AbilitiesFactory;
import main.Constants;

import java.util.ArrayList;

public final class Knight extends Hero {

  public Knight(final ArrayList<Integer> position) {
    super(position);
    this.initHP = Constants.KNIGHT_INIT_HP;
    this.hp = this.initHP;
    type = HeroesType.Knight;
  }

  @Override
  public void levelUp() {
    if (this.xp > getThreshold()) {
      this.level = (this.xp - Constants.XP_LEVEL_UP_CONST_1)
              / Constants.XP_LEVEL_UP_CONST_2 + 1;
      // Resetting hp
      initHP += (Constants.KNIGHT_HP_PER_LEVEL * this.level);
      this.hp = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(final Hero enemyHero, final char terrainType, final int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float executeDamage =
        abilitiesFactory
            .createAbility("Execute", this.level, round, terrainType)
            .execute(enemyHero);
    float slamDamage =
        abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute(enemyHero);
    if (terrainType == 'L') {
      executeDamage *= Constants.KNIGHT_EXECUTE_DMG;
      slamDamage *= Constants.KNIGHT_EXECUTE_DMG;
    }
    int totalDamage = Math.round(executeDamage) + Math.round(slamDamage);
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(final char terrainType, final int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float executeDamage =
        abilitiesFactory.createAbility("Execute", this.level, round, terrainType).execute();
    float slamDamage =
        abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute();
    if (terrainType == 'L') {
      executeDamage *= Constants.KNIGHT_OVERTIME_EXECUTE_DMG;
      slamDamage *= Constants.KNIGHT_OVERTIME_SLAM_DMG;
    }
    int totalDamage = Math.round(executeDamage) + Math.round(slamDamage);
    return totalDamage;
  }
}
