package com.etapa1.main.Heroes;

import java.util.ArrayList;

public class Pyromancer extends Hero {

  public Pyromancer(ArrayList<Integer> position) {
    super(position);
    this.initHP = 500;
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
  public void getTotalDamage(HeroesType enemyType, char terrainType) {

  }

  public HeroesType getType() {
    return type;
  }
}
