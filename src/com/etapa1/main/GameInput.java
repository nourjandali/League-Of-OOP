package com.etapa1.main;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInput {
  private final int N;
  private final int M;
  private final String[] mapLands;
  private ArrayList<String> heroesTypes = new ArrayList<String>();
  private HashMap<Integer, ArrayList> heroesPosition = new HashMap<>();


  public GameInput() {
    this.N = 0;
    this.M = 0;
    this.mapLands = null;
  }


  public GameInput(
      final int N,
      final int M,
      final String[] mapLands,
      final ArrayList<String> heroesTypes,
      HashMap<Integer,ArrayList> heroesPosition) {
    this.N = N;
    this.M = M;
    this.mapLands = mapLands;
    this.heroesTypes = heroesTypes;
    this.heroesPosition = heroesPosition;

  }

  public final int getN() {
    return N;
  }

  public final int getM() {
    return M;
  }

  public final String[] getMapLands() {
    return mapLands;
  }

  public ArrayList<String> getHeroesTypes() {
    return heroesTypes;
  }

  public ArrayList<Integer> getHeroPosition(int index) {
    return heroesPosition.get(index);
  }
}
