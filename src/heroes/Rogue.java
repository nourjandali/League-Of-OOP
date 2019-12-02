package heroes;

import abilities.AbilitiesFactory;
import main.Constants;

import java.util.ArrayList;

public final class Rogue extends Hero {
  private int backstabCount;
  private int backstabCountOvertime;

  public Rogue(final ArrayList<Integer> position) {
    super(position);
    this.initHP = Constants.ROGUE_INIT_HP;
    this.hp = this.initHP;
    type = HeroesType.Rogue;
    this.backstabCount = 0;
    this.backstabCountOvertime = 0;
  }

  @Override
  public void levelUp() {
    if (this.xp > getThreshold()) {
      this.level = (this.xp - Constants.XP_LEVEL_UP_CONST_1) / Constants.XP_LEVEL_UP_CONST_2 + 1;
      // Resetting hp
      initHP += (Constants.ROGUE_HP_PER_LEVEL * this.level);
      this.hp = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(final Hero enemyHero, final char terrainType, final int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float backstabDamage =
        abilitiesFactory
            .createAbility("Backstab", this.level, round, terrainType)
            .execute(enemyHero);
    float paralysisDamage =
        abilitiesFactory
            .createAbility("Paralysis", this.level, round, terrainType)
            .execute(enemyHero);
    if (backstabCount % Constants.BACKSTAB_CRITICAL_OCCUR == 0 && terrainType == 'W') {
      backstabDamage *= Constants.ROGUE_BACKSTAB_CRITICAL_HIT;
    }
    if (terrainType == 'W') {
      backstabDamage *= Constants.ROGUE_BACKSTAB_DMG;
      paralysisDamage *= Constants.ROGUE_PARALYSIS_DMG;
    }
    int totalDamage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    backstabCount++;
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(final char terrainType, final int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float backstabDamage =
        abilitiesFactory.createAbility("Backstab", this.level, round, terrainType).execute();
    float paralysisDamage =
        abilitiesFactory.createAbility("Paralysis", this.level, round, terrainType).execute();
    if (backstabCountOvertime % Constants.BACKSTAB_CRITICAL_OCCUR == 0 && terrainType == 'W') {
      backstabDamage *= Constants.ROGUE_OVERTIME_BACKSTAB_CRITICAL_HIT;
    }
    if (terrainType == 'W') {
      backstabDamage *= Constants.ROGUE_OVERTIME_BACKSTAB_DMG;
      paralysisDamage *= Constants.ROGUE_OVERTIME_PARALYSIS_DMG;
    }
    int totalDamage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    backstabCountOvertime++;
    return totalDamage;
  }
}
