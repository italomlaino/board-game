package com.reversi.model.ai.expertsystem.bordercontrolexpert;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.Rule;
import com.reversi.model.BoardImp;

public class BorderControlExpertRule1 extends Rule {

    @Override
    public int ifThenCause(Board board, byte playerID) {

        int h;
        BoardImp boardImp = (BoardImp) board;
        int totalPieaces = boardImp.getPlayer1Score() + boardImp.getPlayer2Score();
        byte oppPlayerID = board.getPlayerOpponent(playerID);
        byte[][] data;

        h = 0;
        data = board.getData();

        if (data[0][0] == playerID) {
            h += 564 - totalPieaces;
        } else if (data[0][0] == oppPlayerID) {
            h -= Integer.MAX_VALUE;
        }

        if (data[7][0] == playerID) {
            h += 564 - totalPieaces;
        } else if (data[7][0] == oppPlayerID) {
            h -= Integer.MAX_VALUE;
        }

        if (data[0][7] == playerID) {
            h += 564 - totalPieaces;
        } else if (data[0][7] == oppPlayerID) {
            h -= Integer.MAX_VALUE;
        }

        if (data[7][7] == playerID) {
            h += 564 - totalPieaces;
        } else if (data[7][7] == oppPlayerID) {
            h -= Integer.MAX_VALUE;
        }

        return h;
    }
}
