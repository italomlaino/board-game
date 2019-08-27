package com.reversi.model.ai.expertsystem.centercontrolexpert;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.expertsystem.Rule;

public class CenterControlExpert extends ExpertSystem {

    @Override
    public void loadRules() {
        rules.add(new CenterControlExpertRule1());
    }

    @Override
    public int process(Board board, byte playerID) {
        int h;

        h = 0;
        for (Rule rule : rules) {
            h += rule.ifThenCause(board, playerID);
        }

        return h;
    }
}
