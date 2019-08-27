package com.boardgame.gui.action;

import com.boardgame.gui.MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutAction extends AbstractAction {

    private static final String NAME = "About";
    private static final String MESSAGE_ABOUT = "\nÍtalo Macedo\nVitor Takahashi";

    public AboutAction() {
        putValue(Action.NAME, NAME);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(MainGUI.getInstance(), MESSAGE_ABOUT,
                "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
