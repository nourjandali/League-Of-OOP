package com.etapa1.main;

import fileio.FileSystem;

public final class GameInputLoader {
  private final String mInputPath;
  private final String mOutputPath;

  GameInputLoader(final String inputPath, final String outputPath) {
    mInputPath = inputPath;
    mOutputPath = outputPath;
  }

  public GameInput load() {
    //        List<Integer> assetsIds = new ArrayList<>();
    //        List<String> playerOrder = new ArrayList<>();
    //        int rounds = 0;
    //        int noPlayers = 0;
    //        int noGoods = 0;
    int N = 0;
    int M = 0;
    String[] mapLands = new String[0];
    try {
      FileSystem fs = new FileSystem(mInputPath, mOutputPath);
      N = fs.nextInt();
      M = fs.nextInt();
      mapLands = new String[N];
      for (int i = 0; i < N; i++) {
        mapLands[i] = fs.nextWord();
      }

      fs.close();

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    return new GameInput(N, M, mapLands);
  }
}
