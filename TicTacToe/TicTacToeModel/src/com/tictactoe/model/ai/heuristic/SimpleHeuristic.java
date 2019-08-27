package com.tictactoe.model.ai.heuristic;

import com.boardgame.model.Board;
import com.boardgame.model.Board.BoardType;
import com.boardgame.model.ai.heuristic.Heuristic;

public class SimpleHeuristic extends Heuristic {

    private static final String NAME = "Simple Heuristic";
    private static final int PLAYER_WON = 1;
    private static final int OPPONENT_WON = -1;
    private static final int DRAW = 0;

    @Override
    public int calculateH(Board board, byte playerID) {

        BoardType type = board.getType();

        if (type == BoardType.PLAYER1_WON) {

            if (Board.PLAYER1_ID == playerID) {
                return PLAYER_WON;
            } else {
                return OPPONENT_WON;
            }

        } else if (type == BoardType.PLAYER2_WON) {

            if (Board.PLAYER2_ID == playerID) {
                return PLAYER_WON;
            } else {
                return OPPONENT_WON;
            }

        } else {

            return DRAW;

        }
    }

    @Override
    public String toString() {
        return NAME;
    }
}
