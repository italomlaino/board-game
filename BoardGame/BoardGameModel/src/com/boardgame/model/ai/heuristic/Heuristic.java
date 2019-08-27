package com.boardgame.model.ai.heuristic;

import com.boardgame.model.Board;

public abstract class Heuristic {

    public abstract int calculateH(Board board, byte playerID);

}
