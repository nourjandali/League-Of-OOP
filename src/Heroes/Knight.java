package Heroes;

import Abilities.AbilitiesFactory;

import java.util.ArrayList;

public class Knight extends Hero{

    public Knight(ArrayList<Integer> position) {
        super(position);
        this.initHP = 900;
        this.HP = this.initHP;
        type = HeroesType.Knight;
    }

    @Override
    public void levelUp() {
        if(this.XP > getThreshold()){
            this.level++;
            this.XP -= getThreshold();
            // Resetting HP
            initHP += 80;
            this.HP = this.initHP;
        }

    }

    @Override
    public double getTotalDamage(Hero enemyHero, char terrainType, int round) {
        AbilitiesFactory abilitiesFactory = AbilitiesFactory.getInstance();
        double fireblastDamage =
                abilitiesFactory.createAbility("Execute", this.level, round).execute(enemyHero);
        double igniteDamage =
                abilitiesFactory.createAbility("Slam", this.level, round).execute(enemyHero);
        double totalDamage = fireblastDamage + igniteDamage;
        if (terrainType == 'L') {
            totalDamage *= 1.25;
        }
        return totalDamage;
    }
}
