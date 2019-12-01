package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Slam extends Ability {
    double damage;

    public Slam(int level) {
        this.damage = 100 + (level * 40);
        modifiers.put(HeroesType.Rogue, -0.2);
        modifiers.put(HeroesType.Knight, 0.2);
        modifiers.put(HeroesType.Pyromancer, -0.1);
        modifiers.put(HeroesType.Wizard, 0.05);
    }

    @Override
    public double execute(Hero enemyHero) {
        return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
    }
}