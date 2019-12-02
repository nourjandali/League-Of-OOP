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
      this.level = ( this.XP - 250 ) / 50 + 1;
      // Resetting HP
      initHP += (80* this.level);
      this.HP = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float executeDamage =
            abilitiesFactory
                    .createAbility("Execute", this.level, round, terrainType)
                    .execute(enemyHero);
    float slamDamage =
            abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute(enemyHero);
    if (terrainType == 'L') {
      executeDamage *= 1.15f;
      slamDamage *= 1.15f;
    }
    int totalDamage = Math.round(executeDamage) + Math.round(slamDamage);

    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float executeDamage =
            abilitiesFactory.createAbility("Execute", this.level, round, terrainType).execute();
    float slamDamage =
            abilitiesFactory.createAbility("Slam", this.level, round, terrainType).execute();
    if (terrainType == 'L') {
      executeDamage *= 1.15f;
      slamDamage *= 1.15f;
    }
    int totalDamage = Math.round(executeDamage) + Math.round(slamDamage);

    return totalDamage;
  }
}