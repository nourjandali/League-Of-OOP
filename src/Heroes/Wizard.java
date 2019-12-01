package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Wizard extends Hero {
  public Wizard(ArrayList<Integer> position) {
    super(position);
    this.initHP = 400;
    this.HP = this.initHP;
    type = HeroesType.Wizard;
  }

  @Override
  public void levelUp() {
    if (this.XP > getThreshold()) {
      this.level++;
      this.XP -= getThreshold();
      // Resetting HP
      initHP += 30;
      this.HP = this.initHP;
    }
  }

  @Override
  public double getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double drainDamage =
        abilitiesFactory.createAbility("Drain", this.level, round, terrainType).execute(enemyHero);
    double deflectDamage =
        abilitiesFactory
            .createAbility("Deflect", this.level, round, terrainType)
            .execute(enemyHero);
    double totalDamage = drainDamage + deflectDamage;
    if (terrainType == 'D') {
      totalDamage *= 1.10;
    }
    return totalDamage;
  }

  @Override
  public double getTotalDamageWithoutModifier(char terrainType, int round) {
    return 0;
  }
}
