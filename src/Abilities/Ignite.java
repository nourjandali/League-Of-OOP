package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Ignite extends Ability {
  float damage;
  int overtimeLevel;
  public Ignite(int level, int round) {
    this.damage = 150 + (level * 20);
    this.overtimeLevel = level;
    modifiers.put(HeroesType.Rogue, -0.2f);
    modifiers.put(HeroesType.Knight, 0.2f);
    modifiers.put(HeroesType.Pyromancer, -0.1f);
    modifiers.put(HeroesType.Wizard, 0.05f);
  }

  @Override
  public float execute() {
    return this.damage;
  }

  @Override
  public float execute(Hero enemyHero) {
    return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
  }

  @Override
  public float executeOvertimeAbility(Hero enemyHero, char terrainType) {
    float terrainAmp = 1;
    if(terrainType == 'V'){
      terrainAmp = 1.25f;
    }
    return ((50 + (this.overtimeLevel * 30)) * (1 + modifiers.get(enemyHero.getType()))) * terrainAmp;
  }
}
