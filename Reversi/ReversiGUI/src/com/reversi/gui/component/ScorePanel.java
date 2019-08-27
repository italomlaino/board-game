package com.reversi.gui.component;

import com.boardgame.model.Match;
import com.reversi.model.BoardImp;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ScorePanel extends JComponent implements Observer {

    private final Match match;

    private JLabel lbScore1;
    private JLabel lbScore2;
    private JLabel lbPlayerRound;

    public ScorePanel(Match match) {
        this.match = match;

        match.addObserver(this);

        initComponents();
    }

    private void initComponents() {
        lbScore1 = new JLabel("2");
        lbScore1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbScore1.setHorizontalAlignment(SwingConstants.LEFT);
        lbScore2 = new JLabel("2");
        lbScore2.setHorizontalAlignment(SwingConstants.RIGHT);
        lbScore2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 70, 1, 53, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0};
        setLayout(gridBagLayout);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(4, 4, 5, 5);
        c1.anchor = GridBagConstraints.NORTHEAST;
        c1.gridx = 1;
        c1.gridy = 0;
        JLabel label_1 = new JLabel("Black");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label_1, c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(4, 4, 0, 5);
        c2.anchor = GridBagConstraints.NORTHEAST;
        c2.gridx = 1;
        c2.gridy = 1;
        add(lbScore1, c2);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.insets = new Insets(4, 2, 5, 5);
        c3.anchor = GridBagConstraints.NORTHWEST;
        c3.gridx = 3;
        c3.gridy = 0;
        JLabel label_2 = new JLabel("White");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label_2, c3);

        GridBagConstraints c4 = new GridBagConstraints();
        c4.insets = new Insets(4, 2, 4, 5);
        c4.anchor = GridBagConstraints.NORTHWEST;
        c4.gridx = 3;
        c4.gridy = 1;
        add(lbScore2, c4);

        GridBagConstraints c5 = new GridBagConstraints();
        c5.insets = new Insets(4, 4, 4, 0);
        c5.anchor = GridBagConstraints.NORTHEAST;
        c5.gridx = 4;
        c5.gridy = 0;
        lbPlayerRound = new JLabel("Black to play");
        lbPlayerRound.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbPlayerRound, c5);
    }

    @Override
    public void update(Observable o, Object arg) {

        BoardImp board = (BoardImp) match.getCurrent();

        int playerRoundID = match.getPlayerID(match.getPlayerRound());

        lbScore1.setText(Integer.toString(board.getPlayer1Score()));
        lbScore2.setText(Integer.toString(board.getPlayer2Score()));

        if (playerRoundID == 1) {
            lbPlayerRound.setText("Black to play");
        } else if (playerRoundID == 2) {
            lbPlayerRound.setText("White to play");
        }

    }

}
