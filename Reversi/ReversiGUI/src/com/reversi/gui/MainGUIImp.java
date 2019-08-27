package com.reversi.gui;

import com.boardgame.db.Database;
import com.boardgame.gui.MainGUI;
import com.boardgame.gui.component.BoardViewer;
import com.boardgame.model.BoardFactory;
import com.reversi.db.DatabaseImp;
import com.reversi.gui.action.ShowValidMovesAction;
import com.reversi.gui.component.BoardViewerImp;
import com.reversi.gui.component.ScorePanel;
import com.reversi.model.BoardFactoryImp;

import javax.swing.*;
import java.awt.*;

public class MainGUIImp extends MainGUI {

    private static final String TITLE = "Reversi";
    private static final Dimension DEFAULT_SIZE = new Dimension(417, 520);
    private ScorePanel scorePanel;

    private MainGUIImp() {
        super();

        initComponents();
    }

    public static void load() {
        BoardFactory.setDefaultFactory(new BoardFactoryImp());
        Database.setInstance(new DatabaseImp());
        MainGUI.setInstance(new MainGUIImp());
    }

    private void initComponents() {
        setTitle(TITLE);
        setSize(DEFAULT_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);

        mnOptionsPlayer1.setText("Black");
        mnOptionsPlayer2.setText("White");

        BoardViewerImp boardViewerImp = (BoardViewerImp) getBoardViewer();
        ShowValidMovesAction showValidMovesAction = new ShowValidMovesAction(
                boardViewerImp);
        JCheckBoxMenuItem checkBoxShowValidMoves = new JCheckBoxMenuItem(
                showValidMovesAction);

        mnOptions.add(checkBoxShowValidMoves);

        scorePanel = new ScorePanel(getMatch());

        add(scorePanel, BorderLayout.NORTH);
    }

    @Override
    protected BoardViewer createBoardViewer() {
        return new BoardViewerImp();
    }
}
