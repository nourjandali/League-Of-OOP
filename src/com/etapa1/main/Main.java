package com.etapa1.main;

public final class Main {
  private Main() {
    // just to trick checkstyle
  }

  public static void main(final String[] args)  {
    GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
    GameInput gameInput = gameInputLoader.load();
    Map map = new Map(gameInput.getN(), gameInput.getM(), gameInput.getMapLands());
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++) {
        System.out.println(map.getLand(i,j));
      }
    }
  }
}
