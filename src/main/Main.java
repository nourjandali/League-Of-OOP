package main;

import Heroes.Hero;
import Heroes.HeroesType;
import Heroes.Pyromancer;
import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

public final class Main {
  private Main() {
    // just to trick checkstyle
  }

  private static boolean isSamePosition(int[] pos1, int[] pos2) {
    return pos1[0] == pos2[0] && pos1[1] == pos2[1];
  }
  private static char getTypeChar(HeroesType type){
    switch (type){
      case Pyromancer:
        return 'P';
      case Rogue:
        return 'R';
      case Knight:
        return 'K';
      case Wizard:
        return 'W';
    }
    return 0;
  }

  public static void main(final String[] args) throws IOException {
    GameLoader gameLoader = new GameLoader(args[0], args[1]);
    GameInput gameInput = gameLoader.load();
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
        for (int k = 0; k < heroes.size(); k++) {
          // Skip same hero
          if (j == k) {
            continue;
          }
          Hero enemyHero = heroes.get(k);
          int[] enemyPosition = enemyHero.getPosition();
          // Else fight other heroes if same place
          if (isSamePosition(heroPosition, enemyPosition)) {
            HeroesType enemyType = enemyHero.getType();
            double damageDone = currentHero.getTotalDamage(enemyType, terrainType, i);
            enemyHero.takeDamage(damageDone);
          }
        }
      }
    }
    FileSystem fs = gameLoader.getFs();
    for (int i = 0; i < heroes.size(); i++) {
      Hero currentHero = heroes.get(i);
      char heroChar = getTypeChar(currentHero.getType());
      if(currentHero.getHP() == 0){
        fs.writeCharacter(heroChar);
        fs.writeCharacter(' ');
        fs.writeWord("dead");
        fs.writeNewLine();
      }
    }
    gameLoader.closeFile();
  }
}
