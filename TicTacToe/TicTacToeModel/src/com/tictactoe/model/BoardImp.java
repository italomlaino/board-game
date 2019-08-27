package com.tictactoe.model;

import com.boardgame.model.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImp extends Board {

    private static final int HEIGHT = 3;
    private static final int WIDTH = 3;

    private boolean fullCache;
    private BoardType typeCache;

    public BoardImp() {
        this(constructInitialData());
    }

    private BoardImp(byte[][] data) {
        super(data);

        this.fullCache = checkIsFull();
        this.typeCache = checkType();
    }

    @Override
    public List<Point> getAllValidMovePoints(byte playerID) {

        if (playerID != PLAYER1_ID && playerID != PLAYER2_ID) {
            return null;
        }

        if (isTerminal()) {
            return null;
        }

        List<Point> moves = new ArrayList<Point>();

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (data[j][i] == EMPTY_ID) {
                    moves.add(new Point(i, j));
                }

            }
        }

        return moves;
    }

    @Override
    public List<Board> getAllValidMoves(byte playerID) {

        List<Board> moves = new ArrayList<Board>();

        if (playerID != PLAYER1_ID && playerID != PLAYER2_ID) {
            return moves;
        }

        if (isTerminal()) {
            return moves;
        }

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (data[j][i] == EMPTY_ID) {

                    Board board = set(i, j, playerID);
                    moves.add(board);

                }

            }
        }

        return moves;
    }

    @Override
    public boolean isAvailable(int x, int y) {

        if (isValidCoords(x, y)) {
            if (data[y][x] == BoardImp.EMPTY_ID) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public Board set(int x, int y, byte playerID) {
        BoardImp copy = new BoardImp(data);

        if (playerID != PLAYER1_ID && playerID != PLAYER2_ID
                && playerID != EMPTY_ID) {
            return copy;
        }

        if (!isValidCoords(x, y)) {
            return copy;
        }

        copy.data[y][x] = playerID;

        copy.fullCache = copy.checkIsFull();
        copy.typeCache = copy.checkType();

        return copy;
    }

    public boolean isFull() {
        return fullCache;
    }

    private boolean checkIsFull() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (data[j][i] == EMPTY_ID) {
                    return false;

                }
            }
        }

        return true;
    }

    public BoardType getType() {
        return typeCache;
    }

    private BoardType checkType() {
        boolean inLine = false;

        // Horizontal Check
        for (int j = 0; j < HEIGHT; j++) {

            inLine = true;
            for (int i = 0; i < WIDTH - 1; i++) {

                if (data[j][i] == EMPTY_ID || data[j][i] != data[j][i + 1]) {
                    inLine = false;
                    break;
                }

            }

            if (inLine) {
                return BoardType.getWonTypeByID(data[j][0]);
            }
        }

        // Vertical Check
        for (int i = 0; i < WIDTH; i++) {

            inLine = true;
            for (int j = 0; j < HEIGHT - 1; j++) {

                if (data[j][i] == EMPTY_ID || data[j][i] != data[j + 1][i]) {
                    inLine = false;
                    break;
                }
            }

            if (inLine) {
                return BoardType.getWonTypeByID(data[0][i]);
            }
        }

        // Main Diagonal Check
        for (int i = 0, j = 0; i < WIDTH - 1 && j < HEIGHT - 1; i++, j++) {
            inLine = true;

            if (data[j][i] == EMPTY_ID || data[j][i] != data[j + 1][i + 1]) {
                inLine = false;
                break;
            }
        }

        if (inLine) {
            return BoardType.getWonTypeByID(data[0][0]);
        }

        // Antidiagonal Check
        for (int i = WIDTH - 1, j = 0; i >= 1 && j < HEIGHT - 1; i--, j++) {
            inLine = true;

            if (data[j][i] == EMPTY_ID || data[j][i] != data[j + 1][i - 1]) {
                inLine = false;
                break;
            }
        }

        if (inLine) {
            return BoardType.getWonTypeByID(data[0][WIDTH - 1]);
        }

        if (isFull()) {
            return BoardType.DRAW;
        }

        return BoardType.INCONCLUSIVE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BoardImp other = (BoardImp) obj;
        if (!Arrays.deepEquals(data, other.data)) {
            return false;
        }
        return true;
    }

    private static byte[][] constructInitialData() {
        byte[][] data = new byte[HEIGHT][WIDTH];

        return data;
    }
}
