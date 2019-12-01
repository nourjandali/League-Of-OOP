package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Rogue extends Hero {
  int backstabCount;

  public Rogue(ArrayList<Integer> position) {
    super(position);
    this.initHP = 600;
    this.HP = this.initHP;
    type = HeroesType.Rogue;
    this.backstabCount = 0;
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
  public double getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double backstabDamage =
        abilitiesFactory
            .createAbility("Backstab", this.level, round, terrainType)
            .execute(enemyHero);
    double paralysisDamage =
        abilitiesFactory
            .createAbility("Paralysis", this.level, round, terrainType)
            .execute(enemyHero);
    if (backstabCount % 3 == 0 && terrainType == 'W') {
      backstabDamage += (1.5 * backstabDamage);
    }
    double totalDamage = backstabDamage + paralysisDamage;
    if (terrainType == 'W') {
      totalDamage *= 1.15;
    }
    backstabCount++;
    return totalDamage;
  }

  @Override
  public double getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double backstabDamage =
        abilitiesFactory.createAbility("Backstab", this.level, round, terrainType).execute();
    double paralysisDamage =
        abilitiesFactory.createAbility("Paralysis", this.level, round, terrainType).execute();
    if (backstabCount % 3 == 0 && terrainType == 'W') {
      backstabDamage += (1.5 * backstabDamage);
    }
    double totalDamage = backstabDamage + paralysisDamage;
    if (terrainType == 'W') {
      totalDamage *= 1.15;
    }
    backstabCount++;
    return totalDamage;
  }
}
