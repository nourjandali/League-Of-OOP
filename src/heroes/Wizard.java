package heroes;

import abilities.AbilitiesFactory;
import main.Constants;

import java.util.ArrayList;

public final class Wizard extends Hero {
  public Wizard(final ArrayList<Integer> position) {
    super(position);
    this.initHP = Constants.WIZARD_INIT_HP;
    this.hp = this.initHP;
    type = HeroesType.Wizard;
  }

  @Override
  public void levelUp() {
    if (this.xp > getThreshold()) {
      this.level = (this.xp - Constants.XP_LEVEL_UP_CONST_1) / Constants.XP_LEVEL_UP_CONST_2 + 1;
      // Resetting hp
      initHP += (Constants.WIZARD_HP_PER_LEVEL * this.level);
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
      drainDamage *= Constants.WIZARD_DRAIN_DMG;
      deflectDamage *= Constants.WIZARD_DEFLECT_DMG;
    }
    int totalDamage = Math.round(drainDamage) + Math.round(deflectDamage);
    return totalDamage;
  }

  @Override
  public int getTotalDamageWithoutModifier(final char terrainType, final int round) {
    return 0;
  }
}
