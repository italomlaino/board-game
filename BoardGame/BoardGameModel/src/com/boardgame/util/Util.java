package com.boardgame.util;

import java.util.Arrays;

public class Util {

    public static byte[][] copyMatrix(byte[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        byte[][] copy = new byte[m][];

        for (int j = 0; j < m; j++) {
            copy[j] = Arrays.copyOf(matrix[j], n);
        }

        return copy;
    }
}
