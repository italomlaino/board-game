package com.boardgame.gui.action;

import com.boardgame.gui.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {

    private static final String NAME = "Exit";
    private static final String ACCELERATOR = "alt F4";

    public ExitAction() {
        putValue(Action.NAME, NAME);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(ACCELERATOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainGUI.getInstance().dispose();
    }
}
