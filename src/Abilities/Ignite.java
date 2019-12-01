package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Ignite extends Ability {
  float damage;

  public Ignite(int level, int round) {
    this.damage = 150 + (level * 20);
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
  public float executeOvertimeAbility(Hero enemyHero) {
//    float damage = 50 + (overtimeHero.getLevel()*30);
//    float damageWithModifier = damage*modifiers.get(enemyHero.getType());
//    long totalOvertimeDamage = (long) (damage + damageWithModifier);
//    enemyHero.takeDamage(totalOvertimeDamage);
    return 0;
  }
}
