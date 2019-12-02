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
      this.level = (this.XP - 250) / 50 + 1;
      // Resetting HP
      initHP += (30 * this.level);
      this.HP = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(Hero enemyHero, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    float drainDamage =
        abilitiesFactory.createAbility("Drain", this.level, round, terrainType).execute(enemyHero);
    float deflectDamage =
        abilitiesFactory
            .createAbility("Deflect", this.level, round, terrainType)
            .execute(enemyHero);
    if (terrainType == 'D') {
      drainDamage *= 1.10f;
      deflectDamage *= 1.10f;
    }
    int totalDamage = Math.round(drainDamage) + Math.round(deflectDamage);
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(char terrainType, int round) {
    return 0;
  }
}
