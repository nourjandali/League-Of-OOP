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
  public int getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float fireblastDamage =
        abilitiesFactory
            .createAbility("Fireblast", this.level, round, terrainType)
            .execute(enemyHero);
    float igniteDamage =
        abilitiesFactory.createAbility("Ignite", this.level, round, terrainType).execute(enemyHero);
    if (terrainType == 'V') {
      fireblastDamage *= 1.25;
      igniteDamage *= 1.25;
    }
    int totalDamage = Math.round(fireblastDamage) + Math.round(igniteDamage);
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float fireblastDamage =
        abilitiesFactory.createAbility("Fireblast", this.level, round, terrainType).execute();
    float igniteDamage =
        abilitiesFactory.createAbility("Ignite", this.level, round, terrainType).execute();
    if (terrainType == 'V') {
      fireblastDamage *= 1.25;
      igniteDamage *= 1.25;
    }
    int totalDamage = Math.round(fireblastDamage) + Math.round(igniteDamage);
    return totalDamage;
  }

  public HeroesType getType() {
    return type;
  }
}
