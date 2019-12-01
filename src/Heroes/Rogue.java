package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Rogue extends Hero {
  int backstabCount;
  int backstabCountOvertime;

  public Rogue(ArrayList<Integer> position) {
    super(position);
    this.initHP = 600;
    this.HP = this.initHP;
    type = HeroesType.Rogue;
    this.backstabCount = 0;
    this.backstabCountOvertime = 0;

  }

  @Override
  public void levelUp() {
    if (this.XP > getThreshold()) {
      this.level++;
      this.XP -= getThreshold();
      // Resetting HP
      initHP += 40;
      this.HP = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float backstabDamage =
        abilitiesFactory
            .createAbility("Backstab", this.level, round, terrainType)
            .execute(enemyHero);
    float paralysisDamage =
        abilitiesFactory
            .createAbility("Paralysis", this.level, round, terrainType)
            .execute(enemyHero);
    if (backstabCount % 3 == 0 && terrainType == 'W' && backstabCount != 0) {
      backstabDamage += (1.5f * backstabDamage);
    }
    if (terrainType == 'W') {
      backstabDamage *= 1.15f;
      paralysisDamage *= 1.15f;
    }
    int totalDamage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    backstabCount++;
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float backstabDamage =
        abilitiesFactory.createAbility("Backstab", this.level, round, terrainType).execute();
    float paralysisDamage =
        abilitiesFactory.createAbility("Paralysis", this.level, round, terrainType).execute();
    if (backstabCountOvertime % 3 == 0 && terrainType == 'W' && backstabCountOvertime != 0) {
      backstabDamage += (1.5f * backstabDamage);
    }
    if (terrainType == 'W') {
      backstabDamage *= 1.15f;
      paralysisDamage *= 1.15f;
    }
    int totalDamage = Math.round(backstabDamage) + Math.round(paralysisDamage);
    backstabCountOvertime++;
    return totalDamage;
  }
}
