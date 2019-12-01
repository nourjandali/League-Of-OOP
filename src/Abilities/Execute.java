package Abilities;

import Heroes.Hero;
import Heroes.HeroesType;

public class Execute extends Ability {
    double damage;
    double hpLimit;

    public Execute(int level){
        this.damage = 200 + (level * 30);
        modifiers.put(HeroesType.Rogue, 0.15);
        modifiers.put(HeroesType.Knight, 0.0);
        modifiers.put(HeroesType.Pyromancer, 0.1);
        modifiers.put(HeroesType.Wizard, -0.2);
        this.hpLimit = 0.2 + (0.01*level);
        if(this.hpLimit > 0.4) {
            this.hpLimit = 0.4;
        }
    }

    @Override
    public double execute() {
        return this.damage;
    }

    @Override
    public double execute(Hero enemyHero) {
        double hpPercentage = enemyHero.getHP()/enemyHero.getInitHP();
        if(hpPercentage <= hpLimit){
            // Enemy take damage with all their health to die
            return enemyHero.getHP();
        }
        return this.damage + (this.damage * modifiers.get(enemyHero.getType()));
    }
}
