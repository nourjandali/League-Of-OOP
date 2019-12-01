package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Pyromancer extends Hero {

  public Pyromancer(ArrayList<Integer> position) {
    super(position);
    this.initHP = 500;
    this.HP = this.initHP;
    type = HeroesType.Pyromancer;
  }

  @Override
  public void levelUp() {
    if (this.XP > getThreshold()) {
      this.level++;
      this.XP -= getThreshold();
      // Resetting HP
      initHP += 50;
      this.HP = this.initHP;
    }
  }

  @Override
  public double getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double fireblastDamage =
        abilitiesFactory
            .createAbility("Fireblast", this.level, round, terrainType)
            .execute(enemyHero);
    double igniteDamage =
        abilitiesFactory.createAbility("Ignite", this.level, round, terrainType).execute(enemyHero);
    double totalDamage = fireblastDamage + igniteDamage;
    if (terrainType == 'V') {
      totalDamage *= 1.25;
    }
    return totalDamage;
  }

  @Override
  public double getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double fireblastDamage =
        abilitiesFactory.createAbility("Fireblast", this.level, round, terrainType).execute();
    double igniteDamage =
        abilitiesFactory.createAbility("Ignite", this.level, round, terrainType).execute();
    double totalDamage = fireblastDamage + igniteDamage;
    if (terrainType == 'V') {
      totalDamage *= 1.25;
    }
    return totalDamage;
  }

  public HeroesType getType() {
    return type;
  }
}
