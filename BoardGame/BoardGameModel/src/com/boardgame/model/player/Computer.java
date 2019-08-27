package com.boardgame.model.player;

import com.boardgame.model.Board;
import com.boardgame.model.Match;
import com.boardgame.model.ai.decisionrule.DecisionRule;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;

public class Computer extends Player {

    private final DecisionRule decisionRule;
    private final Heuristic heuristic;
    private final ExpertSystem expertSystem;
    private final int depthLimit;

    public Computer(DecisionRule decisionRule, Heuristic heuristic,
                    ExpertSystem expertSystem, int depthLimit) {
        this.decisionRule = decisionRule;
        this.heuristic = heuristic;
        this.expertSystem = expertSystem;
        this.depthLimit = depthLimit;
    }

    @Override
    public void doMove(Match match) {
        Board next = decisionRule.search(match.getCurrent(),
                match.getPlayerID(this), heuristic, depthLimit, expertSystem);

        match.nextMove(next);
    }

    public DecisionRule getDecisionRule() {
        return decisionRule;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public ExpertSystem getExpertSystem() {
        return expertSystem;
    }

    public int getDepthLimit() {
        return depthLimit;
    }

    @Override
    public String toString() {
        String s = decisionRule.getClass().getSimpleName();
        s += " (h: " + heuristic.getClass().getSimpleName();

        if (expertSystem != null) {
            s += ", e: " + expertSystem.getClass().getSimpleName();
        }

        s += ", d: " + depthLimit + ")";

        return s;
    }
}
