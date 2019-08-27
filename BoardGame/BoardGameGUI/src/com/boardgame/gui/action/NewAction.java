package com.boardgame.gui.action;

import com.boardgame.model.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractAction {

    private static final String NAME = "New";
    private static final String ACCELERATOR = "ctrl N";

    private final Match match;

    public NewAction(Match match) {
        this.match = match;

        putValue(Action.NAME, NAME);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(ACCELERATOR));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        match.reset();
    }

}
