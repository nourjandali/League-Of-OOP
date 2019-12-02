package heroes;

import abilities.AbilitiesFactory;

import java.util.ArrayList;

public final class Pyromancer extends Hero {

  public Pyromancer(final ArrayList<Integer> position) {
    super(position);
    this.initHP = 500;
    this.HP = this.initHP;
    type = HeroesType.Pyromancer;
  }

  @Override
  public void levelUp() {
    if (this.XP > getThreshold()) {
      this.level = (this.XP - 250) / 50 + 1;
      // Resetting HP
      initHP += (50 * this.level);
      this.HP = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(final Hero enemyHero, final char terrainType, final int round) {
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
  public int getTotalDamageWithoutModifier(final char terrainType, final int round) {
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
}
