package com.boardgame.gui.component;

import com.boardgame.model.Board;
import com.boardgame.model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public abstract class BoardViewer extends JComponent implements Observer {

    protected byte[][] data;
    protected Board board;
    protected Match match;

    protected int squareHeight;
    protected int squareWidth;

    private final RenderingHints renderHints;

    protected BoardViewer() {
        setDoubleBuffered(true);

        this.renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
    }

    @Override
    protected abstract void paintComponent(Graphics g);

    protected void reset() {
        revalidate();
        repaint();
    }

    protected void updateCalculations() {
        squareHeight = getHeight() / data.length;
        squareWidth = getWidth() / data[0].length;

        if (squareHeight > squareWidth) {
            squareHeight = squareWidth;
        } else if (squareHeight < squareWidth) {
            squareWidth = squareHeight;
        }

    }

    protected Graphics2D getGraphics2D(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(renderHints);

        return g2d;
    }

    public Point getPointRelativeToMatrix(Point src) {
        int x = (int) (src.getX() / squareWidth);
        int y = (int) (src.getY() / squareHeight);

        return new Point(x, y);
    }

    public void changeMouseBehavior(MouseBehavior mouseBehavior) {
        clearAllMouseBehavior();

        addMouseBehavior(mouseBehavior);
    }

    public void addMouseBehavior(MouseBehavior mouseBehavior) {
        addMouseListener(mouseBehavior);
        addMouseMotionListener(mouseBehavior);
    }

    public void clearAllMouseBehavior() {
        for (MouseListener listener : getMouseListeners()) {
            removeMouseListener(listener);
        }

        for (MouseMotionListener listener : getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board value) {

        if (value == null) {
            return;
        }

        this.board = value;
        this.data = value.getData();

        updateCalculations();

        reset();
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable.getClass() == Match.class) {
            Match match = (Match) observable;
            this.match = match;
            setBoard(match.getCurrent());
        }
    }
}