package com.boardgame.model;

public abstract class BoardFactory {

    private static BoardFactory defaultFactory;

    public abstract Board makeBoard();

    public static BoardFactory getDefaultFactory() {
        return defaultFactory;
    }

    public static void setDefaultFactory(BoardFactory defaultFactory) {
        BoardFactory.defaultFactory = defaultFactory;
    }
}
