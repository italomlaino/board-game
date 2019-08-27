package com.tictactoe.cli;

import com.boardgame.gui.MainGUI;
import com.tictactoe.gui.MainGUIImp;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUIImp.load();

                MainGUI.getInstance().setVisible(true);
            }
        });

    }
}
