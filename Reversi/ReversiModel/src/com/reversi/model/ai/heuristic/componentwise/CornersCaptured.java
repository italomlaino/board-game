package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class CornersCaptured extends Heuristic {

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerCornersCaptured = 0;
        int oppCornersCaptured = 0;
        byte oppID = board.getPlayerOpponent(playerID);
        byte[][] data = board.getData();

        if (data[0][0] == playerID) {
            playerCornersCaptured++;
        } else if (data[0][0] == oppID) {
            oppCornersCaptured++;
        }

        if (data[0][7] == playerID) {
            playerCornersCaptured++;
        } else if (data[0][7] == oppID) {
            oppCornersCaptured++;
        }

        if (data[7][0] == playerID) {
            playerCornersCaptured++;
        } else if (data[7][0] == oppID) {
            oppCornersCaptured++;
        }

        if (data[7][7] == playerID) {
            playerCornersCaptured++;
        } else if (data[7][7] == oppID) {
            oppCornersCaptured++;
        }

        score = 25 * (playerCornersCaptured - oppCornersCaptured);

        return score;
    }

}