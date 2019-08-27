package com.tictactoe.gui.component;

import com.boardgame.gui.component.BoardViewer;
import com.boardgame.model.Board;
import com.boardgame.model.Board.BoardType;

import java.awt.*;

public class BoardViewerImp extends BoardViewer {

    private Font font1;
    private Font font2;
    private final BasicStroke stroke;

    private final Color colorO;
    private final Color colorX;
    private final Color colorBackgroundPlayer1Won;
    private final Color colorBackgroundPlayer2Won;
    private final Color colorBackgroundDraw;

    public BoardViewerImp() {
        super();

        stroke = new BasicStroke(1);
        colorO = Color.decode("#181ef6");
        colorX = Color.decode("#ff0f0f");
        colorBackgroundPlayer1Won = Color.decode("#F70C0C");
        colorBackgroundPlayer2Won = Color.decode("#6060FC");
        colorBackgroundDraw = Color.decode("#fff447");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = getGraphics2D(g);

        if (board == null) {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getSize().width, getSize().height);
            return;
        }

        if (board.getType() == BoardType.PLAYER1_WON) {
            g2d.setColor(colorBackgroundPlayer1Won);
        } else if (board.getType() == BoardType.PLAYER2_WON) {
            g2d.setColor(colorBackgroundPlayer2Won);
        } else if (board.getType() == BoardType.DRAW) {
            g2d.setColor(colorBackgroundDraw);
        } else {
            g2d.setColor(Color.WHITE);
        }

        g2d.fillRect(0, 0, getSize().width, getSize().height);

        updateCalculations();

        g2d.setFont(font1);
        g2d.setColor(Color.BLACK);

        g2d.setStroke(stroke);

        for (int j = 0; j < data.length; j++) {

            for (int i = 0; i < data[0].length; i++) {

                int x = i * squareWidth;
                int y = j * squareHeight;

                g2d.setColor(Color.black);
                g2d.drawRect(x, y, squareWidth, squareHeight);

                if (data[j][i] != Board.EMPTY_ID) {
                    String s;
                    Color sColor;

                    if (data[j][i] == Board.PLAYER1_ID) {
                        s = "X";
                        sColor = colorX;
                    } else {
                        s = "O";
                        sColor = colorO;
                    }

                    FontMetrics metrics = g2d.getFontMetrics();

                    x += (squareWidth - metrics.getStringBounds(s, g2d)
                            .getWidth()) / 2;
                    y += (squareHeight + metrics.getStringBounds(s, g2d)
                            .getHeight()) / 2;

                    g2d.setColor(Color.BLACK);
                    g2d.drawString(s, x + 3, y + 3);

                    g2d.setColor(sColor);
                    g2d.drawString(s, x, y);
                }
            }
        }

        if (board.isTerminal()) {
            String s;

            if (board.getType() == BoardType.PLAYER1_WON) {
                s = "X Won";
            } else if (board.getType() == BoardType.PLAYER2_WON) {
                s = "O Won";
            } else {
                s = "Draw";
            }

            g2d.setFont(font2);

            FontMetrics metrics = g2d.getFontMetrics();
            int x = (int) ((getWidth() - metrics.getStringBounds(s, g2d)
                    .getWidth()) / 2);
            int y = (int) ((getHeight() + metrics.getStringBounds(s, g2d)
                    .getHeight()) / 2);

            g2d.setColor(Color.BLACK);
            g2d.drawString(s, x + 3, y + 3);

            g2d.setColor(Color.ORANGE);
            g2d.drawString(s, x, y);
        }
    }

    @Override
    protected void updateCalculations() {
        super.updateCalculations();

        font1 = new Font("Arial", Font.PLAIN,
                (int) (squareWidth * 0.25 + squareHeight * 0.25));
        font2 = new Font("Arial", Font.PLAIN,
                (int) (getWidth() * 0.07 + getHeight() * 0.07));
    }
}