package com.reversi.model.ai.expertsystem.centercontrolexpert;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.Rule;

public class CenterControlExpertRule1 extends Rule {

    @Override
    public int ifThenCause(Board board, byte playerID) {

        byte[][] data = board.getData();

        int h = 0;
        if (data[3][3] == playerID) {
            h++;
        } else if (data[3][4] == playerID) {
            h++;
        } else if (data[4][3] == playerID) {
            h++;
        } else if (data[4][4] == playerID) {
            h++;
        }

        return h;
    }
}
