package com.tictactoe.model.ai.heuristic;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class AnotherHeuristic extends Heuristic {

    private static final String NAME = "Another Heuristic";

    private static final int THREE_LINE = 100;
    private static final int TWO_LINE = 10;
    private static final int ONE_LINE = 1;

    @Override
    public int calculateH(Board board, byte playerID) {

        int m = getScore(board, playerID);
        int o = getScore(board, board.getPlayerOpponent(playerID));

        int h = m - o;

        return h;
    }

    private int getScore(Board board, byte playerID) {
        byte[][] data = board.getData();

        int score = 0;

        for (int j = 0; j < data.length; j++) {
            int count = 0;

            for (int i = 0; i < data[0].length; i++) {
                if (data[j][i] == playerID) {
                    count++;
                } else if (data[j][i] != Board.EMPTY_ID) {
                    count = 0;
                    break;
                }
            }

            score += evaluate(count);
        }

        for (int i = 0; i < data[0].length; i++) {
            int count = 0;

            for (int j = 0; j < data.length; j++) {
                if (data[j][i] == playerID) {
                    count++;
                } else if (data[j][i] != Board.EMPTY_ID) {
                    count = 0;
                    break;
                }
            }

            score += evaluate(count);
        }

        int count = 0;
        for (int i = 0, j = 0; i < data[0].length && j < data.length; i++, j++) {

            if (data[j][i] == playerID) {
                count++;
            } else if (data[j][i] != Board.EMPTY_ID) {
                count = 0;
                break;
            }
        }

        score += evaluate(count);

        count = 0;
        for (int i = data[0].length - 1, j = 0; i >= 0 && j < data.length; i--, j++) {

            if (data[j][i] == playerID) {
                count++;
            } else if (data[j][i] != Board.EMPTY_ID) {
                count = 0;
                break;
            }
        }

        score += evaluate(count);

        return score;
    }

    private int evaluate(int times) {
        switch (times) {
            case 0:
                return 0;
            case 1:
                return ONE_LINE;
            case 2:
                return TWO_LINE;
            case 3:
                return THREE_LINE;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return NAME;
    }
}
