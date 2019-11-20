package com.etapa1.main;

public class Map {
    private char[][] map;

    public Map(final int N, final int M, final String[] mapLands) {
        map = new char[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                map[i][j] = mapLands[i].charAt(j);
            }
        }
    }

    public char[][] getMap() {
        return map;
    }

    public char getLand(final int nPos, final int mPos) {
        return map[nPos][mPos];
    }
}
