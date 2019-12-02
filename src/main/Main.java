package main;

import Abilities.Ignite;
import Abilities.Paralysis;
import Abilities.Slam;
import Heroes.*;
import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

public final class Main {
  private Main() {
    // just to trick checkstyle
  }
  /*
   * @param 2 heroes positions
   * @returns indicator for same position
   */
  private static boolean isSamePosition(int[] pos1, int[] pos2) {
    return pos1[0] == pos2[0] && pos1[1] == pos2[1];
  }
  /*
   * @param hero type
   * @returns the character who represents the hero type
   */
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

    // Heroes creations
    for (int i = 0; i < heroesTypes.size(); i++) {
      ArrayList<Integer> heroPosition = gameInput.getHeroPosition(i);
      if (heroesTypes.get(i).equals("P")) {
        Pyromancer pyromancer = new Pyromancer(heroPosition);
        heroes.add(pyromancer);
      } else if (heroesTypes.get(i).equals("K")) {
        Knight knight = new Knight(heroPosition);
        heroes.add(knight);
      } else if (heroesTypes.get(i).equals("R")) {
        Rogue rogue = new Rogue(heroPosition);
        heroes.add(rogue);
      } else if (heroesTypes.get(i).equals("W")) {
        Wizard wizard = new Wizard(heroPosition);
        heroes.add(wizard);
      }
    }

    // Game rounds
    for (int i = 0; i < gameInput.getRoundCount(); i++) {
      // Perform heroes movements
      for (int j = 0; j < heroes.size(); j++) {
        Hero currentHero = heroes.get(j);
        currentHero.updateOvertime(i);
        if (currentHero.getHP() == 0) {
          currentHero.setDead(true);
        }
        if (!currentHero.isSlammed()) {
          currentHero.updatePosition(gameInput.getHeroMovement(i, j));
        }
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
            int damageDone = currentHero.getTotalDamage(enemyHero, terrainType, i);
            enemyHero.takeDamage(damageDone);
            // Overtime abilities
            if (currentHero.getType() == HeroesType.Pyromancer) {
              enemyHero.setOvertime(
                  new Ignite(currentHero.getLevel(), i), i + 1, i + 3, terrainType);
            } else if (currentHero.getType() == HeroesType.Rogue) {
              int noOfRounds = 3;
              if (terrainType == 'W') {
                noOfRounds = 6;
              }
              enemyHero.setOvertime(
                  new Paralysis(currentHero.getLevel()), i + 1, i + 1 + noOfRounds, terrainType);
            } else if (currentHero.getType() == HeroesType.Knight) {
              enemyHero.setOvertime(new Slam(currentHero.getLevel()), i + 1, i + 2, terrainType);
            }
            if (enemyHero.getHP() == 0) {
              currentHero.win(enemyHero.getLevel());
            }
          }
        }
      }

      // Perform further checks on all heroes
      for (int j = 0; j < heroes.size(); j++) {
        Hero currentHero = heroes.get(j);
        // Check if dead
        if (currentHero.getHP() == 0) {
          currentHero.setDead(true);
        } else {
          // Level up hero if exceeded the threshold
          heroes.get(j).levelUp();
        }
      }
    }

    // Printing out to output file
    FileSystem fs = gameLoader.getFs();
    for (int i = 0; i < heroes.size(); i++) {
      Hero currentHero = heroes.get(i);
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
        fs.writeInt(currentHero.getHP());
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
