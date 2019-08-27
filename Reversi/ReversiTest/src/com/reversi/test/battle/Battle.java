package com.reversi.test.battle;

import com.boardgame.model.BoardFactory;
import com.boardgame.model.Match;
import com.boardgame.model.ai.decisionrule.AlphaBeta;
import com.boardgame.model.ai.decisionrule.DecisionRule;
import com.boardgame.model.ai.decisionrule.Minimax;
import com.boardgame.model.ai.heuristic.Heuristic;
import com.boardgame.model.player.Computer;
import com.reversi.model.BoardFactoryImp;
import com.reversi.model.ai.heuristic.componentwise.Componentwise;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    private static final Heuristic DEFAULT_HEURISTIC = new Componentwise();
    private static final DecisionRule DEFAULT_DECISION_RULE = new Minimax();

    private List<Computer> createComputers() {
        Computer minimax1 = new com.boardgame.model.player.Computer(
                new Minimax(), DEFAULT_HEURISTIC, null, 4);
        Computer alphabeta2 = new com.boardgame.model.player.Computer(
                new AlphaBeta(), DEFAULT_HEURISTIC, null, 4);

        List<Computer> players = new ArrayList<Computer>();
        players.add(minimax1);
        players.add(alphabeta2);

        return players;
    }

    private void fight(Computer player1, Computer player2) {
        Match match = new Match(player1, player2);
        match.start();

        System.out.println();
        System.out.println(player1.toString() + " vs " + player2.toString());
        System.out.println(match.getCurrent().toString());
    }

    private void fight(DecisionRule decisionRule1, Heuristic heuristic1,
                       DecisionRule decisionRule2, Heuristic heuristic2) {
        BoardFactory.setDefaultFactory(new BoardFactoryImp());

        Computer player1 = new com.boardgame.model.player.Computer(
                decisionRule1, heuristic1, null, 4);
        Computer player2 = new com.boardgame.model.player.Computer(
                decisionRule2, heuristic2, null, 4);

        fight(player1, player2);
    }

    @Test
    public void test1() {
        BoardFactory.setDefaultFactory(new BoardFactoryImp());

        List<Computer> computers = createComputers();

        for (Computer player1 : computers) {
            for (Computer player2 : computers) {
                fight(player1, player2);
                fight(player2, player1);

                System.out.println("--------------");
            }

            System.out
                    .println("----------------------------------------------");
        }
    }
}
