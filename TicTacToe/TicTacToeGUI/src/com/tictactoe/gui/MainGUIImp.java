package com.tictactoe.gui;

import com.boardgame.db.Database;
import com.boardgame.gui.MainGUI;
import com.boardgame.gui.component.BoardViewer;
import com.boardgame.model.BoardFactory;
import com.tictactoe.db.DatabaseImp;
import com.tictactoe.gui.component.BoardViewerImp;
import com.tictactoe.model.BoardFactoryImp;

public class MainGUIImp extends MainGUI {

    private static final String TITLE = "TicTacToe";

    private MainGUIImp() {
        super();

        setTitle(TITLE);

        mnOptionsPlayer1.setText("X Player");
        mnOptionsPlayer2.setText("O Player");
    }

    @Override
    protected BoardViewer createBoardViewer() {
        return new BoardViewerImp();
    }

    public static void load() {
        BoardFactory.setDefaultFactory(new BoardFactoryImp());
        Database.setInstance(new DatabaseImp());
        MainGUI.setInstance(new MainGUIImp());
    }
}
