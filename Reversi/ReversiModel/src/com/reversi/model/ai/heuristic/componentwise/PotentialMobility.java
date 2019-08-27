package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

import java.awt.*;

public class PotentialMobility extends Heuristic {

    private static final Point[] NEIGHBORS = {new Point(0, -1), new Point(1, -1),
            new Point(1, 0), new Point(1, 1), new Point(0, 1),
            new Point(-1, 1), new Point(-1, 0), new Point(-1, -1)};

    private boolean isFrontier(Board board, byte[][] data, int x, int y) {

        for (Point p : NEIGHBORS) {
            int dx = x + p.x;
            int dy = y + p.y;

            if (board.isValidCoords(dx, dy)) {
                if (data[dy][dx] == Board.EMPTY_ID) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerFrontiers = 0;
        int oppFrontiers = 0;
        byte oppID = board.getPlayerOpponent(playerID);
        byte[][] data = board.getData();

        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < data[0].length; i++) {

                if (data[j][i] != Board.EMPTY_ID) {

                    if (isFrontier(board, data, i, j)) {

                        if (data[j][i] == playerID) {
                            playerFrontiers++;
                        } else if (data[j][i] == oppID) {
                            oppFrontiers++;
                        }

                    }
                }
            }
        }

        if (playerFrontiers > oppFrontiers) {
            score = (int) (-(100.0 * playerFrontiers) / (playerFrontiers + oppFrontiers));
        } else if (playerFrontiers < oppFrontiers) {
            score = (int) (100.0 * oppFrontiers)
                    / (playerFrontiers + oppFrontiers);
        } else {
            score = 0;
        }

        return score;
    }
}
