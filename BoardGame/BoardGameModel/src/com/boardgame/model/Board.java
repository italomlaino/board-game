package com.boardgame.model;

import com.boardgame.util.Util;

import java.awt.*;
import java.util.List;

public abstract class Board {

    public enum BoardType {

        INCONCLUSIVE, DRAW, PLAYER1_WON, PLAYER2_WON;

        public static BoardType getWonTypeByID(byte id) {
            if (id == PLAYER1_ID) {
                return PLAYER1_WON;
            } else if (id == PLAYER2_ID) {
                return PLAYER2_WON;
            } else {
                return INCONCLUSIVE;
            }
        }
    }

    public static final byte NOT_FOUND_ID = -1;
    public static final byte EMPTY_ID = 0; // Keep 0 because it's a lot easier
    // to create the initial data
    public static final byte PLAYER1_ID = 1;
    public static final byte PLAYER2_ID = 2;

    protected final byte[][] data;

    protected Board(byte[][] data) {
        this.data = Util.copyMatrix(data);
    }

    public abstract List<Point> getAllValidMovePoints(byte playerID);

    public abstract List<Board> getAllValidMoves(byte playerID);

    public abstract boolean isAvailable(int x, int y);

    public abstract Board set(int x, int y, byte playerID);

    public abstract BoardType getType();

    public byte[][] getData() {
        return Util.copyMatrix(data);
    }

    public boolean isValidCoords(int x, int y) {

        if (x < 0 || x >= data[0].length) {
            return false;
        }

        if (y < 0 || y >= data.length) {
            return false;
        }

        return true;
    }

    public byte getPlayerOpponent(byte playerID) {

        if (playerID == PLAYER1_ID) {
            return PLAYER2_ID;
        } else if (playerID == PLAYER2_ID) {
            return PLAYER1_ID;
        } else {
            return NOT_FOUND_ID;
        }

    }

    public boolean isTerminal() {
        BoardType type = getType();

        if (type == BoardType.INCONCLUSIVE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < data.length; j++) {

            sb.append("\n");

            for (int i = 0; i < data[0].length; i++) {
                sb.append(data[j][i]);
                sb.append(" ");
            }
        }

        sb.append("\n");
        sb.append(getType().toString());

        return sb.toString();
    }
}
