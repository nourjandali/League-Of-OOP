package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Knight extends Hero {

  public Knight(ArrayList<Integer> position) {
    super(position);
    this.initHP = 900;
    this.HP = this.initHP;
    type = HeroesType.Knight;
  }

  @Override
  public void levelUp() {
    if (this.XP > getThreshold()) {
      this.level++;
      this.XP -= getThreshold();
      // Resetting HP
      initHP += 80;
      this.HP = this.initHP;
    }
  }

  @Override
  public double getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double executeDamage =
        abilitiesFactory
            .createAbility("Execute", this.level, round, terrainType)
            .execute(enemyHero);
    double slamDamage =
        abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute(enemyHero);
    double totalDamage = executeDamage + slamDamage;
    if (terrainType == 'L') {
      totalDamage *= 1.15;
    }
    return totalDamage;
  }

  @Override
  public double getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double executeDamage =
        abilitiesFactory.createAbility("Execute", this.level, round, terrainType).execute();
    double slamDamage =
        abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute();
    double totalDamage = executeDamage + slamDamage;
    if (terrainType == 'L') {
      totalDamage *= 1.15;
    }
    return totalDamage;
  }
}
