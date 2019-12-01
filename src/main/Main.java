package main;

import Heroes.Hero;
import Heroes.HeroesType;
import Heroes.Knight;
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

  private static char getTypeChar(HeroesType type) {
    switch (type) {
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
      ArrayList<Integer> heroPosition = gameInput.getHeroPosition(i);
      if (heroesTypes.get(i).equals("P")) {
        Pyromancer pyromancer = new Pyromancer(heroPosition);
        heroes.add(pyromancer);
      } else if (heroesTypes.get(i).equals("K")) {
        Knight knight = new Knight(heroPosition);
        heroes.add(knight);
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
        if (currentHero.isDead()) {
          continue;
        }
        int[] heroPosition = currentHero.getPosition();
        char terrainType = map.getLand(heroPosition[0], heroPosition[1]);
        for (int k = 0; k < heroes.size(); k++) {
          Hero enemyHero = heroes.get(k);
          // Skip same hero or is dead
          if (j == k || enemyHero.isDead()) {
            continue;
          }
          int[] enemyPosition = enemyHero.getPosition();
          // Else fight other heroes if same place
          if (isSamePosition(heroPosition, enemyPosition)) {
            double damageDone = currentHero.getTotalDamage(enemyHero, terrainType, i);
            enemyHero.takeDamage(damageDone);
            if (enemyHero.getHP() == 0) {
              currentHero.win(enemyHero.getLevel());
            }
          }
        }
      }
      // Perform further checks on all heroes
      for (int j = 0; j < heroes.size(); j++) {
        Hero currentHero = heroes.get(i);
        // Check if dead
        if (currentHero.getHP() == 0) {
          currentHero.setDead(true);
        }
        // Level up hero if exceeded the threshold
        heroes.get(j).levelUp();
      }
    }
    FileSystem fs = gameLoader.getFs();
    for (int i = 0; i < heroes.size(); i++) {
      Hero currentHero = heroes.get(i);
      System.out.println(currentHero.getHP());
      System.out.println(currentHero.getLevel());
      System.out.println("-------------------------");
      char heroChar = getTypeChar(currentHero.getType());
      fs.writeCharacter(heroChar);
      fs.writeCharacter(' ');
      if (currentHero.getHP() == 0) {
        fs.writeWord("dead");
      } else {
        fs.writeInt(currentHero.getLevel());
        fs.writeCharacter(' ');
        fs.writeInt(currentHero.getXP());
        fs.writeCharacter(' ');
        fs.writeDouble(currentHero.getHP());
        fs.writeCharacter(' ');
        fs.writeInt(currentHero.getPosition()[0]);
        fs.writeCharacter(' ');
        fs.writeInt(currentHero.getPosition()[1]);
      }
      fs.writeNewLine();
    }
    gameLoader.closeFile();
  }
}
