package com.reversi.model.ai.heuristic;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class StaticWeights extends Heuristic {

    private static final String NAME = "Stability Heuristic";

    private static final int[][] WEIGHT_TABLE = {{4, -3, 2, 2, 2, 2, -3, 4},
            {-3, -4, -1, -1, -1, -1, -4, -3}, {2, -1, 1, 0, 0, 1, -1, 2},
            {2, -1, 0, 1, 1, 0, -1, 2}, {2, -1, 0, 1, 1, 0, -1, 2},
            {2, -1, 1, 0, 0, 1, -1, 2}, {-3, -4, -1, -1, -1, -1, -4, -3},
            {4, -3, 2, 2, 2, 2, -3, 4}};

    @Override
    public int calculateH(Board board, byte playerID) {
        int s = 0;
        byte oppID = board.getPlayerOpponent(playerID);
        byte[][] data = board.getData();

        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < data[0].length; i++) {

                if (data[j][i] == playerID) {
                    s += WEIGHT_TABLE[j][i];
                } else if (data[j][i] == oppID) {
                    s -= WEIGHT_TABLE[j][i];
                }
            }
        }

        return s;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
