package com.boardgame.model.ai.decisionrule;

public class DecisionRuleFactory {

    private static DecisionRuleFactory defaultFactory;

    public DecisionRule makeDecisionRule(Class<? extends DecisionRule> cl) {

        if (cl == Minimax.class) {
            return new Minimax();
        } else if (cl == AlphaBeta.class) {
            return new AlphaBeta();
        } else if (cl == MultiThreadAlphaBeta.class){
            return new MultiThreadAlphaBeta();
        }

        return null;
    }

    public static DecisionRuleFactory getDefaultFactory() {
        if (defaultFactory == null) {
            defaultFactory = new DecisionRuleFactory();
        }

        return defaultFactory;
    }
}
