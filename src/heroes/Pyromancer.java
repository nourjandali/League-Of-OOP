package heroes;

import abilities.AbilitiesFactory;
import main.Constants;

import java.util.ArrayList;

public final class Pyromancer extends Hero {

  public Pyromancer(final ArrayList<Integer> position) {
    super(position);
    this.initHP = Constants.PYROMANCER_INIT_HP;
    this.hp = this.initHP;
    type = HeroesType.Pyromancer;
  }

  @Override
  public void levelUp() {
    if (this.xp > getThreshold()) {
      this.level = (this.xp - Constants.XP_LEVEL_UP_CONST_1) / Constants.XP_LEVEL_UP_CONST_2 + 1;
      // Resetting hp
      initHP += (Constants.PYROMANCER_HP_PER_LEVEL * this.level);
      this.hp = this.initHP;
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
      fireblastDamage *= Constants.PYROMANCER_FIREBLAST_DMG;
      igniteDamage *= Constants.PYROMANCER_IGNITE_DMG;
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
      fireblastDamage *= Constants.PYROMANCER_OVERTIME_FIREBLAST_DMG;
      igniteDamage *= Constants.PYROMANCER_OVERTIME_IGNITE_DMG;
    }
    int totalDamage = Math.round(fireblastDamage) + Math.round(igniteDamage);
    return totalDamage;
  }
}
