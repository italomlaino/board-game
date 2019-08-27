package com.reversi.gui.component;

import com.boardgame.gui.component.BoardViewer;
import com.boardgame.model.Board;
import com.boardgame.model.Board.BoardType;

import java.awt.*;
import java.util.List;

public class BoardViewerImp extends BoardViewer {

    private static final String borderVerticalLabel = "abcdefghijklmnopqrstuvwxyz";
    private static final String borderHorizontalLabel = "123456789ABCDEFGHIJKLMNOPQ";
    private boolean showValidMoves;
    private final int borderWidth;
    private final int borderHeight;
    private Font font;

    public BoardViewerImp() {
        super();

        showValidMoves = false;
        borderWidth = 20;
        borderHeight = 20;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = getGraphics2D(g);

        updateCalculations();

        g2d.setColor(new Color(0, 158, 11));
        g2d.fillRect(0, 0, getSize().width, getSize().height);

        if (board == null) {
            return;
        }

        if (board.isTerminal()) {
            g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.30f));
        }

        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < data[0].length; i++) {

                int x = i * squareWidth + borderWidth;
                int y = j * squareHeight + borderHeight;

                g2d.setColor(Color.black);
                g2d.drawRect(x, y, squareWidth, squareHeight);

                if (data[j][i] != Board.EMPTY_ID) {

                    if (data[j][i] == Board.PLAYER1_ID) {
                        g2d.setColor(Color.BLACK);
                    } else {
                        g2d.setColor(Color.WHITE);
                    }
                    g2d.fillOval(x + 3, y + 3, squareWidth - 6,
                            squareHeight - 6);

                    g2d.setColor(Color.black);
                    g2d.drawOval(x + 3, y + 3, squareWidth - 6,
                            squareHeight - 6);
                }
            }
        }

        if (showValidMoves && !board.isTerminal()) {
            if (match != null) {
                g2d.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, 0.30f));

                List<Point> points = match.getAllValidMovePoints(
                        match.getCurrent(), match.getPlayerRound());

                for (Point p : points) {
                    int x = p.x * squareWidth + borderWidth;
                    int y = p.y * squareHeight + borderHeight;

                    if (match.getPlayerID(match.getPlayerRound()) == Board.PLAYER1_ID) {
                        g2d.setColor(Color.BLACK);
                    } else {
                        g2d.setColor(Color.WHITE);
                    }
                    g2d.fillOval(x + 3, y + 3, squareWidth - 6,
                            squareHeight - 6);

                    g2d.setColor(Color.black);
                    g2d.drawOval(x + 3, y + 3, squareWidth - 6,
                            squareHeight - 6);
                }

                g2d.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, 1.0f));
            }
        }

        for (int i = 0; i < data[0].length; i++) {
            String text = borderVerticalLabel.substring(i, i + 1);

            g2d.drawString(text, i * squareWidth + borderWidth + squareWidth
                    / 2, 0 + borderHeight / 2);
        }

        for (int j = 0; j < data.length; j++) {
            String text = borderHorizontalLabel.substring(j, j + 1);

            g2d.drawString(text, 0, j * squareHeight + borderHeight
                    + squareHeight / 2);
        }

        if (board.isTerminal()) {
            String s;
            Color color;

            if (board.getType() == BoardType.PLAYER1_WON) {
                s = "Black Won";
                color = Color.BLACK;
            } else if (board.getType() == BoardType.PLAYER2_WON) {
                s = "White Won";
                color = Color.WHITE;
            } else {
                s = "Draw";
                color = Color.GRAY;
            }

            g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1.0f));

            g2d.setFont(font);

            FontMetrics metrics = g2d.getFontMetrics();
            int x = (int) ((getWidth() - metrics.getStringBounds(s, g2d)
                    .getWidth()) / 2);
            int y = (int) ((getHeight() + metrics.getStringBounds(s, g2d)
                    .getHeight() / 2) / 2);

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawString(s, x + 3, y + 3);

            g2d.setColor(color);
            g2d.drawString(s, x, y);
        }
    }

    @Override
    protected void updateCalculations() {
        squareHeight = (getHeight() - borderHeight * 2) / data.length;
        squareWidth = (getWidth() - borderWidth * 2) / data[0].length;

        if (squareHeight > squareWidth) {
            squareHeight = squareWidth;
        } else if (squareHeight < squareWidth) {
            squareWidth = squareHeight;
        }

        setSize(squareWidth * data.length + borderWidth * 2, squareHeight
                * data[0].length + borderHeight * 2);

        font = new Font("Arial", Font.PLAIN,
                (int) (getWidth() * 0.07 + getHeight() * 0.07));
    }

    @Override
    public Point getPointRelativeToMatrix(Point src) {
        if (src.x <= borderWidth) {
            return null;
        } else if (src.x >= borderWidth + squareWidth * data[0].length) {
            return null;
        }

        if (src.y <= borderHeight) {
            return null;
        } else if (src.y >= borderHeight + squareHeight * data.length) {
            return null;
        }

        int x = (int) ((src.getX() - borderWidth) / squareWidth);
        int y = (int) ((src.getY() - borderHeight) / squareHeight);

        return new Point(x, y);
    }

    public boolean isShowingValidMoves() {
        return showValidMoves;
    }

    public void setShowValidMoves(boolean showValidMoves) {
        this.showValidMoves = showValidMoves;

        reset();
    }
}