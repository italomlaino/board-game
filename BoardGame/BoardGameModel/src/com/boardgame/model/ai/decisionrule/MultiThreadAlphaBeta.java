package com.boardgame.model.ai.decisionrule;

import com.boardgame.model.Board;
import com.boardgame.model.ai.expertsystem.ExpertSystem;
import com.boardgame.model.ai.heuristic.Heuristic;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadAlphaBeta extends AlphaBeta {

    private static final int MAX_THREADS = 2;

    @Override
    public Board search(Board board, byte playerID, Heuristic heuristic, int depthLimit, ExpertSystem expertSystem) {
        this.aiPlayerID = playerID;

        if (board.getAllValidMovePoints(playerID).isEmpty()) {
            return null;
        }

        List<ThreadM> threads = new ArrayList<ThreadM>();
        List<Board> validMoves = board.getAllValidMoves(playerID);
        double[] values = new double[validMoves.size()];
        List<Board> remainingValidMoves = new ArrayList<Board>(validMoves);

        for (int i = 0; i < MAX_THREADS; i++) {
            if (remainingValidMoves.size() > 0) {
                ThreadM threadM = new ThreadM(remainingValidMoves.get(0), playerID, heuristic, depthLimit, expertSystem);
                threadM.start();

                threads.add(threadM);
                remainingValidMoves.remove(remainingValidMoves.get(0));
            } else {
                break;
            }
        }

        for (Board move : remainingValidMoves) {
            double value = minValue(move, board.getPlayerOpponent(playerID),
                    heuristic, Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY, depthLimit - 1, expertSystem);

            values[validMoves.indexOf(move)] = value;
        }

        for (ThreadM threadM : threads) {
            try {
                threadM.join();

                values[validMoves.indexOf(threadM.getBoard())] = threadM.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = -1;
        double resultValue = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > resultValue) {
                result = i;
                resultValue = values[i];
            }
        }

        return validMoves.get(result);
    }

    public class ThreadM extends Thread {

        private Board board;
        private byte playerID;
        private Heuristic heuristic;
        private int depthLimit;
        private ExpertSystem expertSystem;
        private volatile double value;

        public ThreadM(Board board, byte playerID, Heuristic heuristic, int depthLimit, ExpertSystem expertSystem) {
            this.board = board;
            this.playerID = playerID;
            this.heuristic = heuristic;
            this.depthLimit = depthLimit;
            this.expertSystem = expertSystem;
        }

        @Override
        public void run() {
            AlphaBeta alphaBeta = new AlphaBeta();
            alphaBeta.aiPlayerID = playerID;

            value = alphaBeta.minValue(board, board.getPlayerOpponent(playerID),
                    heuristic, Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY, depthLimit - 1, expertSystem);
        }

        public Board getBoard() {
            return board;
        }

        public double getValue() {
            return value;
        }
    }
}
