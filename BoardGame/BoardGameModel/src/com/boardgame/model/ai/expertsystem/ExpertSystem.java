package com.boardgame.model.ai.expertsystem;

import com.boardgame.model.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ExpertSystem {

    protected final List<Rule> rules;

    protected ExpertSystem() {
        rules = new ArrayList<Rule>();

        loadRules();
    }

    protected abstract void loadRules();

    public abstract int process(Board board, byte playerID);

}
