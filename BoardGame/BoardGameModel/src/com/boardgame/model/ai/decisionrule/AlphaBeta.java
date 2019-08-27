package com.boardgame.model.ai.decisionrule;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;

import java.util.List;

public class AlphaBeta extends DecisionRule {

    protected byte aiPlayerID;

    @Override
    public Board search(Board board, byte playerID, Heuristic heuristic,
                        int depthLimit, ExpertSystem expertSystem) {
        this.aiPlayerID = playerID;

        if (!board.getAllValidMovePoints(playerID).isEmpty()) {
            return alphaBetaSearch(board, playerID, heuristic, depthLimit,
                    expertSystem);
        } else {
            return null;
        }

    }

    protected Board alphaBetaSearch(Board board, byte playerID,
                                  Heuristic heuristic, int depthLimit, ExpertSystem expertSystem) {

        List<Board> moves = board.getAllValidMoves(playerID);

        Board result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        for (Board move : moves) {
            double value = minValue(move, board.getPlayerOpponent(playerID),
                    heuristic, Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY, depthLimit - 1, expertSystem);

            if (value > resultValue) {
                result = move;
                resultValue = value;
            }
        }

        return result;
    }

    protected double maxValue(Board board, byte playerID, Heuristic heuristic,
                            double alpha, double beta, int depthLeft, ExpertSystem expertSystem) {

        if (board.isTerminal() || depthLeft == 0) {
            int h = heuristic.calculateH(board, aiPlayerID);

            if (expertSystem != null) {
                h += expertSystem.process(board, aiPlayerID);
            }

            return h;
        }

        List<Board> moves = board.getAllValidMoves(playerID);

        if (moves.isEmpty()) {
            return minValue(board, board.getPlayerOpponent(playerID),
                    heuristic, alpha, beta, depthLeft - 1, expertSystem);
        }

        double value = Double.NEGATIVE_INFINITY;
        for (Board move : moves) {
            value = Math
                    .max(value,
                            minValue(move, board.getPlayerOpponent(playerID),
                                    heuristic, alpha, beta, depthLeft - 1,
                                    expertSystem));

            if (value >= beta) {
                return value;
            }

            alpha = Math.max(alpha, value);
        }

        return value;
    }

    protected double minValue(Board board, byte playerID, Heuristic heuristic,
                            double alpha, double beta, int depthLeft, ExpertSystem expertSystem) {

        if (board.isTerminal() || depthLeft == 0) {
            int h = heuristic.calculateH(board, aiPlayerID);

            if (expertSystem != null) {
                h += expertSystem.process(board, aiPlayerID);
            }

            return h;
        }

        List<Board> moves = board.getAllValidMoves(playerID);

        if (moves.isEmpty()) {
            return maxValue(board, board.getPlayerOpponent(playerID),
                    heuristic, alpha, beta, depthLeft - 1, expertSystem);
        }

        double value = Double.POSITIVE_INFINITY;
        for (Board move : moves) {
            value = Math
                    .min(value,
                            maxValue(move, board.getPlayerOpponent(playerID),
                                    heuristic, alpha, beta, depthLeft - 1,
                                    expertSystem));

            if (value <= alpha) {
                return value;
            }

            beta = Math.min(beta, value);
        }

        return value;
    }
}
