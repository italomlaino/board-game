package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class ComponentWise extends Heuristic {

    private final CoinParity coinParity;
    private final CornersCaptured cornersCaptured;
    private final CornerCloseness cornerCloseness;
    private final CurrentMobility currentMobility;
    private final PotentialMobility potentialMobility;
    private final Stability stability;

    public ComponentWise() {

        coinParity = new CoinParity();
        cornersCaptured = new CornersCaptured();
        cornerCloseness = new CornerCloseness();
        currentMobility = new CurrentMobility();
        potentialMobility = new PotentialMobility();
        stability = new Stability();

    }

    @Override
    public int calculateH(Board board, byte playerID) {

        int p = coinParity.calculateH(board, playerID);
        int m = currentMobility.calculateH(board, playerID);
        int c = cornersCaptured.calculateH(board, playerID);
        int l = cornerCloseness.calculateH(board, playerID);
        int f = potentialMobility.calculateH(board, playerID);
        int d = stability.calculateH(board, playerID);

        int score = (int) ((10 * p) + (801.724 * c) + (382.026 * l)
                + (78.922 * m) + (74.396 * f) + (10 * d));

        return score;
    }
}
