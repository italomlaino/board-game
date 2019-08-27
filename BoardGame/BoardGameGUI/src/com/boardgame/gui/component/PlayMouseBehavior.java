package com.boardgame.gui.component;

import com.boardgame.model.Board;
import com.boardgame.model.Match;
import com.boardgame.model.player.Human.MoveInput;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class PlayMouseBehavior extends MouseBehavior implements MoveInput,
        Observer {

    private Match match;
    private byte playerID;

    public PlayMouseBehavior(BoardViewer viewer) {
        super(viewer);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Board current = getViewer().getBoard();

        if (current == null) {
            return;
        }

        if (current.isTerminal()) {
            match.reset();

            return;
        }

        Point p = getViewer().getPointRelativeToMatrix(e.getPoint());
        if (p == null) {
            return;
        }

        if (!current.isAvailable(p.x, p.y)) {
            if (current.getAllValidMovePoints(playerID).isEmpty()) {
                match.nextMove(null);
            } else {
                return;
            }
        }

        Board next = current.set(p.x, p.y, playerID);

        if (next == null) {
            return;
        } else if (next.equals(current)) {
            next = null;
        }

        match.nextMove(next);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void doMoveInput(Match match, byte playerID) {
        this.match = match;
        this.playerID = playerID;

        match.addObserver(this);
        getViewer().changeMouseBehavior(this);
    }

    @Override
    public void update(Observable observable, Object arg) {

        if (observable.getClass() == Match.class) {

            Match match = (Match) observable;
            if (match.getCurrent().isTerminal()) {
                match.deleteObserver(this);
                getViewer().changeMouseBehavior(this);
            }
        }

    }
}
