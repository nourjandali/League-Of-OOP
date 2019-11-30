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
  public void win(int loserLevel) {
    super.win(loserLevel);
    // In case of leveling up
    if (this.XP > getThreshold()) {
      this.level++;
      this.XP -= getThreshold();
      // Resetting HP
      initHP += 50;
      this.HP = this.initHP;
    }
  }

  @Override
  public double getTotalDamage(HeroesType enemyType, char terrainType, int round) {
    AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
    double fireblastDamage =
        abilitiesFactory.createAbility("Fireblast", this.level, round).execute(enemyType);
    double igniteDamage = abilitiesFactory.createAbility("Ignite", this.level, round).execute(enemyType);
    double totalDamage = fireblastDamage + igniteDamage;
    if(terrainType == 'V') {
      totalDamage *= 1.25;
    }
    return totalDamage;
  }

  public HeroesType getType() {
    return type;
  }
}
