package com.etapa1.main;

public class GameInput {
  private final int N;
  private final int M;
  private final String[] mapLands;

  public GameInput() {
    this.N = 0;
    this.M = 0;
    this.mapLands = null;
  }

  public GameInput(final int N, final int M, final String[] mapLands) {
    this.N = N;
    this.M = M;
    this.mapLands = mapLands;
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
  //    public final List<Integer> getAssetIds() {
  //        return mAssetOrder;
  //    }
  //
  //    public final List<String> getPlayerNames() {
  //        return mPlayersOrder;
  //    }
  //
  //    public final int getRounds() {
  //        return mRounds;
  //    }
  //
  //    public final boolean isValidInput() {
  //        boolean membersInstantiated = mAssetOrder != null && mPlayersOrder != null;
  //        boolean membersNotEmpty = mAssetOrder.size() > 0 && mPlayersOrder.size() > 0 && mRounds
  // > 0;
  //
  //        return membersInstantiated && membersNotEmpty;
  //    }
}
