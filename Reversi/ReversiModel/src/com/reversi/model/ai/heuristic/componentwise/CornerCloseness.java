package com.reversi.model.ai.heuristic.componentwise;

import com.boardgame.model.Board;
import com.boardgame.model.ai.heuristic.Heuristic;

public class CornerCloseness extends Heuristic {

    @Override
    public int calculateH(Board board, byte playerID) {

        int score;
        int playerScore = 0;
        int oppScore = 0;
        byte oppID = board.getPlayerOpponent(playerID);
        byte[][] data = board.getData();

        if (data[0][0] == Board.EMPTY_ID) {
            if (data[0][1] == playerID) {
                playerScore++;
            } else if (data[0][1] == oppID) {
                oppScore++;
            }

            if (data[1][1] == playerID) {
                playerScore++;
            } else if (data[1][1] == oppID) {
                oppScore++;
            }

            if (data[1][0] == playerID) {
                playerScore++;
            } else if (data[1][0] == oppID) {
                oppScore++;
            }
        }

        if (data[0][7] == Board.EMPTY_ID) {
            if (data[0][6] == playerID) {
                playerScore++;
            } else if (data[0][6] == oppID) {
                oppScore++;
            }

            if (data[1][6] == playerID) {
                playerScore++;
            } else if (data[1][6] == oppID) {
                oppScore++;
            }

            if (data[1][7] == playerID) {
                playerScore++;
            } else if (data[1][7] == oppID) {
                oppScore++;
            }
        }

        if (data[7][0] == Board.EMPTY_ID) {
            if (data[7][1] == playerID) {
                playerScore++;
            } else if (data[7][1] == oppID) {
                oppScore++;
            }

            if (data[6][1] == playerID) {
                playerScore++;
            } else if (data[6][1] == oppID) {
                oppScore++;
            }

            if (data[6][0] == playerID) {
                playerScore++;
            } else if (data[6][0] == oppID) {
                oppScore++;
            }
        }

        if (data[7][7] == Board.EMPTY_ID) {
            if (data[6][7] == playerID) {
                playerScore++;
            } else if (data[6][7] == oppID) {
                oppScore++;
            }

            if (data[6][6] == playerID) {
                playerScore++;
            } else if (data[6][6] == oppID) {
                oppScore++;
            }

            if (data[7][6] == playerID) {
                playerScore++;
            } else if (data[7][6] == oppID) {
                oppScore++;
            }
        }

        score = (int) (-12.5 * (playerScore - oppScore));

        return score;
    }
}
