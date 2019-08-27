package com.boardgame.db;

import com.boardgame.model.ai.decisionrule.DecisionRule;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Database {

    private static Database instance;

    protected final List<Heuristic> heuristics;
    protected final List<ExpertSystem> expertSystems;
    protected final List<Class<? extends DecisionRule>> decisionRules;

    protected Database() {
        heuristics = new ArrayList<Heuristic>();
        expertSystems = new ArrayList<ExpertSystem>();
        decisionRules = new ArrayList<Class<? extends DecisionRule>>();
    }

    public void load() {
        loadHeuristics();
        loadExpertSystems();
        loadDecisionRules();
    }

    protected abstract void loadHeuristics();

    protected abstract void loadExpertSystems();

    protected abstract void loadDecisionRules();

    public List<Heuristic> getHeuristics() {
        return Collections.unmodifiableList(heuristics);
    }

    public List<ExpertSystem> getExpertSystems() {
        return Collections.unmodifiableList(expertSystems);
    }

    public List<Class<? extends DecisionRule>> getDecisionRules() {
        return Collections.unmodifiableList(decisionRules);
    }

    public static Database getInstance() {
        return instance;
    }

    public static void setInstance(Database instance) {
        Database.instance = instance;
    }
}
