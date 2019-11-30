package com.etapa1.main;

import com.etapa1.main.Heroes.Hero;
import com.etapa1.main.Heroes.HeroesType;
import com.etapa1.main.Heroes.Pyromancer;

import java.util.ArrayList;

public final class Main {
  private Main() {
    // just to trick checkstyle
  }
  private static boolean isSamePosition(int[]pos1, int[]pos2){
    return pos1[0] == pos2[0] && pos1[1] == pos2[1];
  }
  public static void main(final String[] args) {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();
    Map map = new Map(gameInput.getN(), gameInput.getM(), gameInput.getMapLands());
    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<String> heroesTypes = gameInput.getHeroesTypes();
    for (int i = 0; i < heroesTypes.size(); i++) {
      if (heroesTypes.get(i).equals("P")) {
        Pyromancer pyromancer = new Pyromancer(gameInput.getHeroPosition(i));
        heroes.add(pyromancer);
      }
    }
    // Game rounds
    for (int i = 0; i < gameInput.getRoundCount(); i++) {
      double[] heroDamage = new double[heroes.size()];
      // Perform heroes movements
      for (int j = 0; j < heroes.size(); j++) {
        heroes.get(j).updatePosition(gameInput.getHeroMovement(i, j));
      }
      // Calculate damage and perform heroes action
      for (int j = 0; j < heroes.size(); j++) {
        Hero currentHero = heroes.get(j);
        int[] heroPosition = currentHero.getPosition();
        char terrainType = map.getLand(heroPosition[0], heroPosition[1]);
        for(int k = 0; k < heroes.size(); k++){
          // Skip same hero
          if (j == k) {
            continue;
            }
          Hero enemyHero = heroes.get(k);
          int[] enemyPosition = enemyHero.getPosition();
          // Else fight other heroes if same place
          if(isSamePosition(heroPosition, enemyPosition)) {
            HeroesType enemyType = enemyHero.getType();
            double damageDone = currentHero.getTotalDamage(enemyType, terrainType, i);
            enemyHero.takeDamage(damageDone);
          }
        }
      }
    }
    for (int i = 0; i < heroes.size(); i++) {

    }
  }
}
