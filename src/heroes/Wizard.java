package heroes;

import abilities.AbilitiesFactory;

import java.util.ArrayList;

public final class Wizard extends Hero {
  public Wizard(final ArrayList<Integer> position) {
    super(position);
    this.initHP = 400;
    this.hp = this.initHP;
    type = HeroesType.Wizard;
  }

  @Override
  public void levelUp() {
    if (this.xp > getThreshold()) {
      this.level = (this.xp - 250) / 50 + 1;
      // Resetting hp
      initHP += (30 * this.level);
      this.hp = this.initHP;
    }
  }

  @Override
  public int getTotalDamage(final Hero enemyHero, final char terrainType, final int round) {
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
  public int getTotalDamageWithoutModifier(final char terrainType, final int round) {
    return 0;
  }
}