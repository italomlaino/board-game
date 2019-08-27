package com.boardgame.gui.action;

import com.boardgame.model.Board;
import com.boardgame.model.Match;
import com.boardgame.model.player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SelectPlayerAction extends AbstractAction {

    private static int player1N = 0;
    private static int player2N = 0;

    private final Match match;
    private final byte playerID;
    protected Player player;

    public SelectPlayerAction(Match match, byte playerID, Player player) {
        this.match = match;
        this.playerID = playerID;
        this.player = player;

        if (player != null) {
            putValue(Action.NAME, player.toString());
        }

        String accelerator;
        if (playerID == 1) {
            accelerator = "ctrl " + player1N;
            player1N++;
        } else {
            accelerator = "alt " + player2N;
            player2N++;
        }

        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(accelerator));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playerID == Board.PLAYER1_ID) {
            match.setPlayer1(player);
        } else if (playerID == Board.PLAYER2_ID) {
            match.setPlayer2(player);
        }
    }

}
