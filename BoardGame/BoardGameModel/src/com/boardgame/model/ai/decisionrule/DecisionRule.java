package com.boardgame.model.ai.decisionrule;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;

public abstract class DecisionRule {

    public abstract Board search(Board board, byte playerID,
                                 Heuristic heuristic, int depthLimit, ExpertSystem expertSystem);
}
