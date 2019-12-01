package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Slam extends Ability {
    float damage;

    public Slam(int level) {
        this.damage = 100 + (level * 40);
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
    public void executeOvertimeAbility(Hero enemyHero, Hero overtimeHero) {

    }
}