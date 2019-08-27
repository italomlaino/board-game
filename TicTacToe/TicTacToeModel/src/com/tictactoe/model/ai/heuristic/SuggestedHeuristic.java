package com.tictactoe.model.ai.heuristic;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class SuggestedHeuristic extends Heuristic {

    private static final String NAME = "Suggested Heuristic";

    @Override
    public int calculateH(Board board, byte playerID) {

        int m = getScore(board, playerID);
        int o = getScore(board, board.getPlayerOpponent(playerID));

        int h = m - o;

        return h;
    }

    private int getScore(Board board, byte playerID) {
        byte data[][] = board.getData();

        int score = 0;
        boolean inLine = false;

        // Horizontal Check
        for (int j = 0; j < data.length; j++) {

            inLine = true;
            for (int i = 0; i < data[0].length; i++) {
                if (data[j][i] != Board.EMPTY_ID && data[j][i] != playerID) {
                    inLine = false;
                    break;
                }
            }

            if (inLine) {
                score++;
            }
        }

        // Vertical Check
        for (int i = 0; i < data[0].length; i++) {

            inLine = true;
            for (int j = 0; j < data.length; j++) {

                if (data[j][i] != Board.EMPTY_ID && data[j][i] != playerID) {
                    inLine = false;
                    break;
                }
            }

            if (inLine) {
                score++;
            }
        }

        // Main Diagonal Check
        for (int i = 0, j = 0; i < data[0].length && j < data.length; i++, j++) {
            inLine = true;

            if (data[j][i] != Board.EMPTY_ID && data[j][i] != playerID) {
                inLine = false;
                break;
            }
        }

        if (inLine) {
            score++;
        }

        // Antidiagonal Check
        for (int i = data[0].length - 1, j = 0; i >= 1 && j < data.length; i--, j++) {
            inLine = true;

            if (data[j][i] != Board.EMPTY_ID && data[j][i] != playerID) {
                inLine = false;
                break;
            }
        }

        if (inLine) {
            score++;
        }

        return score;
    }

    @Override
    public String toString() {
        return NAME;
    }
}
