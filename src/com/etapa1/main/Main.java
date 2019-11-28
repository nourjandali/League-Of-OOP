package com.etapa1.main;

import com.etapa1.main.Heroes.Hero;
import com.etapa1.main.Heroes.Pyromancer;

import java.util.ArrayList;
import java.util.Arrays;

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
    System.out.println(Arrays.toString(heroes.get(0).getPosition()));
  }
}
