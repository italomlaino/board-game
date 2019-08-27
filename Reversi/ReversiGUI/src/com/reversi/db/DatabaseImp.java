package com.reversi.db;

import com.boardgame.db.Database;
import com.boardgame.model.ai.decisionrule.AlphaBeta;
import com.boardgame.model.ai.decisionrule.Minimax;
import com.boardgame.model.ai.decisionrule.MultiThreadAlphaBeta;
import com.reversi.model.ai.expertsystem.bordercontrolexpert.BorderControlExpert;
import com.reversi.model.ai.expertsystem.centercontrolexpert.CenterControlExpert;
import com.reversi.model.ai.expertsystem.minimizerexpert.MinimizerExpert;
import com.reversi.model.ai.heuristic.StaticWeights;
import com.reversi.model.ai.heuristic.componentwise.ComponentWise;

public class DatabaseImp extends Database {

    @Override
    public void loadHeuristics() {
        heuristics.add(new ComponentWise());
        heuristics.add(new StaticWeights());
    }

    @Override
    public void loadExpertSystems() {
        expertSystems.add(null);
        expertSystems.add(new BorderControlExpert());
        expertSystems.add(new CenterControlExpert());
        expertSystems.add(new MinimizerExpert());
    }

    @Override
    public void loadDecisionRules() {
        decisionRules.add(AlphaBeta.class);
        decisionRules.add(Minimax.class);
        decisionRules.add(MultiThreadAlphaBeta.class);
    }
}
