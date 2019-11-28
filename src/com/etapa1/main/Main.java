package com.etapa1.main;

import com.etapa1.main.Heroes.Hero;
import com.etapa1.main.Heroes.Pyromancer;

import java.util.ArrayList;

public final class Main {
  private Main() {
    // just to trick checkstyle
  }

  public static void main(final String[] args)  {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();
    Map map = new Map(gameInput.getN(), gameInput.getM(), gameInput.getMapLands());
    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<String> heroesTypes = gameInput.getHeroesTypes();
    for(int i = 0; i < heroesTypes.size(); i++){
      if(heroesTypes.get(i).equals("P")) {
        Pyromancer pyromancer = new Pyromancer(gameInput.getHeroPosition(i));
        heroes.add(pyromancer);
      }
    }
    for (int i = 0; i < gameInput.getRoundCount(); i++) {
      double[] heroDamage = new double[heroes.size()];
      // perform heroes movements and calculate the damage
      for(int j = 0; j < heroes.size(); j++) {
        heroes.get(j).updatePosition(gameInput.getHeroMovement(i,j));

      }
      // heroes action
      for(int j = 0; j < heroes.size(); j++) {
        heroes.get(j).updatePosition(gameInput.getHeroMovement(i,j));
      }
    }
  }
}
