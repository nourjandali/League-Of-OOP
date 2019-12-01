package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Execute extends Ability {
    double damage;

    public Execute(int level){
        this.damage = 200 + (level * 30);
        modifiers.put(HeroesType.Rogue, 0.15);
        modifiers.put(HeroesType.Knight, 0.0);
        modifiers.put(HeroesType.Pyromancer, 0.1);
        modifiers.put(HeroesType.Wizard, -0.2);
    }
    @Override
    public double execute(Hero enemyHero) {
        return 0;
    }
}
