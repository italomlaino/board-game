package com.tictactoe.model;

import com.boardgame.model.Board;
import com.boardgame.model.BoardFactory;

public class BoardFactoryImp extends BoardFactory {

    @Override
    public Board makeBoard() {
        return new BoardImp();
    }

}
