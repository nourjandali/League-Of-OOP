package com.etapa1.main;

import fileio.FileSystem;

import java.util.ArrayList;
import java.util.HashMap;

public final class GameInputLoader {
  private final String mInputPath;
  private final String mOutputPath;

  GameInputLoader(final String inputPath, final String outputPath) {
    mInputPath = inputPath;
    mOutputPath = outputPath;
  }

  public GameInput load() {
    int N = 0;
    int M = 0;
    String[] mapLands = new String[0];
    HashMap<Integer, ArrayList> heroesPosition = new HashMap<>();
    ArrayList<String> heroesTypes = new ArrayList<String>();
    ArrayList<String> heroesMovement = new ArrayList<>();
    try {
      FileSystem fs = new FileSystem(mInputPath, mOutputPath);
      N = fs.nextInt();
      M = fs.nextInt();
      mapLands = new String[N];
      for (int i = 0; i < N; i++) {
        mapLands[i] = fs.nextWord();
      }
      int heroCount = fs.nextInt();
      for(int i = 0; i<heroCount; i++) {
        ArrayList<Integer> positions = new ArrayList<>();
        heroesTypes.add(fs.nextWord());
        positions.add(fs.nextInt());
        positions.add(fs.nextInt());
        heroesPosition.put(i, positions);
      }
      int roundCount = fs.nextInt();
      for(int i = 0; i < roundCount; i++){
        heroesMovement.add(fs.nextWord());
      }

      fs.close();

    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return new GameInput(N, M, mapLands, heroesTypes, heroesPosition, heroesMovement);

  }
}
