package com.boardgame.model.ai.expertsystem;

import com.boardgame.model.Board;

public abstract class Rule {

    public abstract int ifThenCause(Board board, byte playerID);
}
