package com.tictactoe.db;

import com.boardgame.db.Database;
import com.boardgame.model.ai.decisionrule.AlphaBeta;
import com.boardgame.model.ai.decisionrule.Minimax;
import com.tictactoe.model.ai.heuristic.AnotherHeuristic;
import com.tictactoe.model.ai.heuristic.SimpleHeuristic;
import com.tictactoe.model.ai.heuristic.SuggestedHeuristic;

public class DatabaseImp extends Database {

    @Override
    public void loadHeuristics() {
        heuristics.add(new SimpleHeuristic());
        heuristics.add(new SuggestedHeuristic());
        heuristics.add(new AnotherHeuristic());
    }

    @Override
    public void loadExpertSystems() {
    }

    @Override
    public void loadDecisionRules() {
        decisionRules.add(AlphaBeta.class);
        decisionRules.add(Minimax.class);
    }
}
