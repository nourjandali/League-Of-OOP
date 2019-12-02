package main;

public final class Map {
  private char[][] map;

  public Map(final int n, final int m, final String[] mapLands) {
    map = new char[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        map[i][j] = mapLands[i].charAt(j);
      }
    }
  }

  public char[][] getMap() {
    return map;
  }
  /*
   * @param x & y coordinates
   * @returns specific land type
   */
  public char getLand(final int nPos, final int mPos) {
    return map[nPos][mPos];
  }
}
