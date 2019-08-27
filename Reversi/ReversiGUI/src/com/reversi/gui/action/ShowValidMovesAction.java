package com.reversi.gui.action;

import com.reversi.gui.component.BoardViewerImp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowValidMovesAction extends AbstractAction {

    private static final String NAME = "Show Valid Moves";
    private static final String ACCELERATOR = "F9";

    private final BoardViewerImp boardViewer;

    public ShowValidMovesAction(BoardViewerImp boardViewer) {
        this.boardViewer = boardViewer;

        putValue(Action.NAME, NAME);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(ACCELERATOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boardViewer == null) {
            return;
        }

        boardViewer.setShowValidMoves(!boardViewer.isShowingValidMoves());
    }

}
