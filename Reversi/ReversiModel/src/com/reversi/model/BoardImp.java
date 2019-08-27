package com.reversi.model;

import com.boardgame.model.Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardImp extends Board {

    private static final int HEIGHT = 8;
    private static final int WIDTH = 8;

    private int player1Score;
    private int player2Score;

    private BoardType typeCache;

    public BoardImp() {
        this(constructInitialData());
    }

    private BoardImp(byte[][] data) {
        super(data);

        updateScore();

        typeCache = checkType();
    }

    public List<Point> getAllValidMovePoints(byte playerID) {

        if (playerID != PLAYER1_ID && playerID != PLAYER2_ID) {
            return null;
        }

        List<Point> moves = new ArrayList<Point>();

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (isValidMove(i, j, playerID)) {
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

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (isValidMove(i, j, playerID)) {

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

    private boolean isValidMove(int x, int y, byte playerID) {

        if (data[y][x] != BoardImp.EMPTY_ID) {
            return false;
        }

        // Top (-,0)
        int flipped = 0;

        for (int j = 1; j <= y; j++) {
            int deltaY = y - j;

            if (data[deltaY][x] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][x] != playerID) {
                flipped++;
            } else if (data[deltaY][x] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Bottom (+,0)
        flipped = 0;

        for (int j = 1; j <= HEIGHT - 1 - y; j++) {
            int deltaY = y + j;

            if (data[deltaY][x] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][x] != playerID) {
                flipped++;
            } else if (data[deltaY][x] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Right (0,+)
        flipped = 0;

        for (int i = 1; i <= WIDTH - 1 - x; i++) {
            int deltaX = x + i;

            if (data[y][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[y][deltaX] != playerID) {
                flipped++;
            } else if (data[y][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Left (0,-)
        flipped = 0;

        for (int i = 1; i <= x; i++) {
            int deltaX = x - i;

            if (data[y][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[y][deltaX] != playerID) {
                flipped++;
            } else if (data[y][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Main Diagonal Positive (-, -)
        flipped = 0;

        for (int i = 1, j = 1; i <= x && j <= y; i++, j++) {
            int deltaX = x - i;
            int deltaY = y - j;

            if (data[deltaY][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][deltaX] != playerID) {
                flipped++;
            } else if (data[deltaY][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Main Diagonal Negative (+, +)
        flipped = 0;

        for (int i = 1, j = 1; i <= WIDTH - 1 - x && j <= HEIGHT - 1 - y; i++, j++) {
            int deltaX = x + j;
            int deltaY = y + i;

            if (data[deltaY][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][deltaX] != playerID) {
                flipped++;
            } else if (data[deltaY][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Anti-Diagonal Positive (-, +)
        flipped = 0;

        for (int i = 1, j = 1; i <= WIDTH - 1 - x && j <= y; i++, j++) {
            int deltaX = x + i;
            int deltaY = y - j;

            if (data[deltaY][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][deltaX] != playerID) {
                flipped++;
            } else if (data[deltaY][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        // Anti-Diagonal Negative (+, -)
        flipped = 0;

        for (int i = 1, j = 1; i <= x && j <= HEIGHT - 1 - y; i++, j++) {
            int deltaX = x - i;
            int deltaY = y + j;

            if (data[deltaY][deltaX] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY][deltaX] != playerID) {
                flipped++;
            } else if (data[deltaY][deltaX] == playerID) {
                if (flipped == 0) {
                    break;
                } else {
                    return true;
                }
            }
        }

        return false;
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

        setExtended(copy.data, x, y, playerID);

        copy.updateScore();
        copy.typeCache = copy.checkType();

        return copy;
    }

    private void setExtended(byte[][] data, int x, int y, byte playerID) {

        boolean changed = false;

        // Top (-,0)
        int flipped = 0;

        for (int j = 1; j <= y; j++) {
            int deltaY1 = y - j;

            if (data[deltaY1][x] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][x] != playerID) {
                flipped++;
            } else if (data[deltaY1][x] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaY2 = y - k;

                        data[deltaY2][x] = playerID;
                    }

                    break;
                }
            }
        }

        // Bottom (+,0)
        flipped = 0;

        for (int j = 1; j <= HEIGHT - 1 - y; j++) {
            int deltaY1 = y + j;

            if (data[deltaY1][x] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][x] != playerID) {
                flipped++;
            } else if (data[deltaY1][x] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaY2 = y + k;

                        data[deltaY2][x] = playerID;
                    }

                    break;
                }
            }
        }

        // Right (0,+)
        flipped = 0;

        for (int i = 1; i <= WIDTH - 1 - x; i++) {
            int deltaX1 = x + i;

            if (data[y][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[y][deltaX1] != playerID) {
                flipped++;
            } else if (data[y][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x + k;

                        data[y][deltaX2] = playerID;
                    }

                    break;
                }
            }
        }

        // Left (0,-)
        flipped = 0;

        for (int i = 1; i <= x; i++) {
            int deltaX1 = x - i;

            if (data[y][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[y][deltaX1] != playerID) {
                flipped++;
            } else if (data[y][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x - k;

                        data[y][deltaX2] = playerID;
                    }

                    break;
                }
            }
        }

        // Main Diagonal Positive (-, -)
        flipped = 0;

        for (int i = 1, j = 1; i <= x && j <= y; i++, j++) {
            int deltaX1 = x - i;
            int deltaY1 = y - j;

            if (data[deltaY1][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][deltaX1] != playerID) {
                flipped++;
            } else if (data[deltaY1][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x - k;
                        int deltaY2 = y - k;

                        data[deltaY2][deltaX2] = playerID;
                    }

                    break;
                }
            }
        }

        // Main Diagonal Negative (+, +)
        flipped = 0;

        for (int i = 1, j = 1; i <= WIDTH - 1 - x && j <= HEIGHT - 1 - y; i++, j++) {
            int deltaX1 = x + j;
            int deltaY1 = y + i;

            if (data[deltaY1][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][deltaX1] != playerID) {
                flipped++;
            } else if (data[deltaY1][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x + k;
                        int deltaY2 = y + k;

                        data[deltaY2][deltaX2] = playerID;
                    }

                    break;
                }

            }
        }

        // Anti-Diagonal Positive (-, +)
        flipped = 0;

        for (int i = 1, j = 1; i <= WIDTH - 1 - x && j <= y; i++, j++) {
            int deltaX1 = x + i;
            int deltaY1 = y - j;

            if (data[deltaY1][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][deltaX1] != playerID) {
                flipped++;
            } else if (data[deltaY1][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x + k;
                        int deltaY2 = y - k;

                        data[deltaY2][deltaX2] = playerID;
                    }

                    break;
                }
            }
        }

        // Anti-Diagonal Negative (+, -)
        flipped = 0;

        for (int i = 1, j = 1; i <= x && j <= HEIGHT - 1 - y; i++, j++) {
            int deltaX1 = x - i;
            int deltaY1 = y + j;

            if (data[deltaY1][deltaX1] == BoardImp.EMPTY_ID) {
                break;
            } else if (data[deltaY1][deltaX1] != playerID) {
                flipped++;
            } else if (data[deltaY1][deltaX1] == playerID) {

                if (flipped == 0) {
                    break;
                } else {
                    changed = true;

                    for (int k = 1; k <= flipped; k++) {
                        int deltaX2 = x - k;
                        int deltaY2 = y + k;

                        data[deltaY2][deltaX2] = playerID;
                    }

                    break;
                }
            }
        }

        if (changed) {
            data[y][x] = playerID;
        }
    }

    @Override
    public BoardType getType() {
        return typeCache;
    }

    private BoardType checkType() {
        if (getAllValidMovePoints(PLAYER1_ID).isEmpty()
                && getAllValidMovePoints(PLAYER2_ID).isEmpty()) {

            if (player1Score > player2Score) {
                return BoardType.PLAYER1_WON;
            } else if (player2Score > player1Score) {
                return BoardType.PLAYER2_WON;
            } else {
                return BoardType.DRAW;
            }

        } else {
            return BoardType.INCONCLUSIVE;
        }
    }

    private void updateScore() {
        player1Score = 0;
        player2Score = 0;

        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < data[0].length; i++) {

                if (data[j][i] == PLAYER1_ID) {
                    player1Score++;
                } else if (data[j][i] == PLAYER2_ID) {
                    player2Score++;
                }

            }
        }
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
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

        data[3][4] = PLAYER1_ID;
        data[4][3] = PLAYER1_ID;

        data[3][3] = PLAYER2_ID;
        data[4][4] = PLAYER2_ID;

        return data;
    }

    @Override
    public String toString() {
        String s = "Black: " + player1Score + " White: " + player2Score;

        return s.concat(super.toString());
    }
}
