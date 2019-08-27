package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

import java.awt.*;
import java.util.List;

public class Stability extends Heuristic {

    private static final int STABLE_WEIGHT = 1;
    private static final int SEMI_STABLE_WEIGHT = 0;
    private static final int UNSTABLE_WEIGHT = -1;

    private boolean canBeFlanked(Board board, byte[][] data, byte playerID,
                                 int x, int y) {
        return true;
    }

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerScore = 0;
        int oppScore = 0;
        byte oppID = board.getPlayerOpponent(playerID);
        byte[][] data = board.getData();
        List<Point> unstablePoints = board.getAllValidMovePoints(oppID);

        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < data[0].length; i++) {

                if (data[j][i] == playerID) {
                    if (canBeFlanked(board, data, playerID, i, j)) {
                        if (unstablePoints.contains(new Point(i, j))) {
                            playerScore += UNSTABLE_WEIGHT;
                        } else {
                            playerScore += SEMI_STABLE_WEIGHT;
                        }
                    } else {
                        playerScore += STABLE_WEIGHT;
                    }
                } else if (data[j][i] == oppID) {
                    if (canBeFlanked(board, data, oppID, i, j)) {
                        if (unstablePoints.contains(new Point(i, j))) {
                            oppScore += UNSTABLE_WEIGHT;
                        } else {
                            oppScore += SEMI_STABLE_WEIGHT;
                        }
                    } else {
                        oppScore += STABLE_WEIGHT;
                    }
                }
            }
        }

        if (playerScore > oppScore) {
            score = (int) ((100.0 * playerScore) / (playerScore + oppScore));
        } else if (playerScore < oppScore) {
            score = (int) (-(100.0 * oppScore) / (playerScore + oppScore));
        } else {
            score = 0;
        }

        return score;
    }
}
