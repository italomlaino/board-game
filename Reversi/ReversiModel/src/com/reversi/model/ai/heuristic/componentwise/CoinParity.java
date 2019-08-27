package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;
import com.reversi.model.BoardImp;

public class CoinParity extends Heuristic {

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerSquares;
        int oppSquares;
        BoardImp boardImp = (BoardImp) board;

        if (playerID == Board.PLAYER1_ID) {
            playerSquares = boardImp.getPlayer1Score();
            oppSquares = boardImp.getPlayer2Score();
        } else {
            playerSquares = boardImp.getPlayer2Score();
            oppSquares = boardImp.getPlayer1Score();
        }

        if (playerSquares > oppSquares) {
            score = (int) ((100.0 * playerSquares) / (playerSquares + oppSquares));
        } else if (playerSquares < oppSquares) {
            score = (int) (-(100.0 * oppSquares) / (playerSquares + oppSquares));
        } else {
            score = 0;
        }

        return score;
    }
}
