package com.reversi.model.ai.expertsystem.minimizerexpert;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.Rule;
import com.reversi.model.BoardImp;

public class MinimizerExpertRule1 extends Rule {

    @Override
    public int ifThenCause(Board board, byte playerID) {
        int h;
        int playerCaptured;
        int oppCaptured;
        int nCaptured;
        BoardImp boardImp = (BoardImp) board;

        if (playerID == Board.PLAYER1_ID) {
            playerCaptured = boardImp.getPlayer1Score();
            oppCaptured = boardImp.getPlayer2Score();
        } else {
            playerCaptured = boardImp.getPlayer2Score();
            oppCaptured = boardImp.getPlayer1Score();
        }

        nCaptured = playerCaptured + oppCaptured;

        h = 0;
        // R1
        if (nCaptured <= 15) {
            h = -playerCaptured * 2;
        } else if (nCaptured <= 25) {
            h = -playerCaptured * 1;
        }

        return h;
    }
}
