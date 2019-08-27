package com.boardgame.model.player;

import com.boardgame.model.Match;

public class Human extends Player {

    private static final String NAME = "Human";

    private final MoveInput input;

    public Human(MoveInput input) {
        this.input = input;
    }

    @Override
    public void doMove(Match match) {

        if (input != null && match != null) {
            input.doMoveInput(match, match.getPlayerID(this));
        }
    }

    @Override
    public String toString() {
        return NAME;
    }

    public interface MoveInput {

        public void doMoveInput(Match match, byte playerID);
    }
}
