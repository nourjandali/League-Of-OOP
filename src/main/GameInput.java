package main;

import java.util.ArrayList;
import java.util.HashMap;

public final class GameInput {
  private final int n;
  private final int m;
  private final String[] mapLands;
  private ArrayList<String> heroesTypes = new ArrayList<String>();
  private HashMap<Integer, ArrayList> heroesPosition = new HashMap<>();
  private ArrayList<String> heroesMovement = new ArrayList<>();

  public GameInput() {
    this.n = 0;
    this.m = 0;
    this.mapLands = null;
  }

  public GameInput(
      final int n,
      final int m,
      final String[] mapLands,
      final ArrayList<String> heroesTypes,
      final HashMap<Integer, ArrayList> heroesPosition,
      final ArrayList<String> heroesMovement) {
    this.n = n;
    this.m = m;
    this.mapLands = mapLands;
    this.heroesTypes = heroesTypes;
    this.heroesPosition = heroesPosition;
    this.heroesMovement = heroesMovement;
  }

  public int getN() {
    return n;
  }

  public int getM() {
    return m;
  }

  public String[] getMapLands() {
    return mapLands;
  }

  public ArrayList<String> getHeroesTypes() {
    return heroesTypes;
  }

  public ArrayList<Integer> getHeroPosition(final int index) {
    return heroesPosition.get(index);
  }

  public char getHeroMovement(final int round, final int heroIndex) {
    return heroesMovement.get(round).charAt(heroIndex);
  }

  public int getRoundCount() {
    return heroesMovement.size();
  }
}
