package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class CurrentMobility extends Heuristic {

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerValidMoves;
        int oppValidMoves;
        byte oppID = board.getPlayerOpponent(playerID);

        playerValidMoves = board.getAllValidMovePoints(playerID).size();
        oppValidMoves = board.getAllValidMovePoints(oppID).size();

        if (playerValidMoves > oppValidMoves) {
            score = (int) ((100.0 * playerValidMoves) / (playerValidMoves + oppValidMoves));
        } else if (playerValidMoves < oppValidMoves) {
            score = (int) (-(100.0 * oppValidMoves) / (playerValidMoves + oppValidMoves));
        } else {
            score = 0;
        }

        return score;
    }
}
