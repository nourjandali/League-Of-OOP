package main;

import fileio.FileSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class GameLoader {
  private final String mInputPath;
  private final String mOutputPath;
  private FileSystem fs = null;

  GameLoader(final String inputPath, final String outputPath) {
    mInputPath = inputPath;
    mOutputPath = outputPath;
  }

  public GameInput load() {
    int n = 0;
    int m = 0;
    String[] mapLands = new String[0];
    HashMap<Integer, ArrayList> heroesPosition = new HashMap<>();
    ArrayList<String> heroesTypes = new ArrayList<String>();
    ArrayList<String> heroesMovement = new ArrayList<>();
    try {
      this.fs = new FileSystem(mInputPath, mOutputPath);
      n = fs.nextInt();
      m = fs.nextInt();
      mapLands = new String[n];
      for (int i = 0; i < n; i++) {
        mapLands[i] = fs.nextWord();
      }
      int heroCount = fs.nextInt();
      for (int i = 0; i < heroCount; i++) {
        ArrayList<Integer> positions = new ArrayList<>();
        heroesTypes.add(fs.nextWord());
        positions.add(fs.nextInt());
        positions.add(fs.nextInt());
        heroesPosition.put(i, positions);
      }
      int roundCount = fs.nextInt();
      for (int i = 0; i < roundCount; i++) {
        heroesMovement.add(fs.nextWord());
      }

    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return new GameInput(n, m, mapLands, heroesTypes, heroesPosition, heroesMovement);
  }

  public FileSystem getFs() {
    return fs;
  }

  public void closeFile() {
    try {
      this.fs.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
