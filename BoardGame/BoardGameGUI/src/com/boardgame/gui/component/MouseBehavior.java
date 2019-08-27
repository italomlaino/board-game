package com.boardgame.gui.component;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class MouseBehavior implements MouseListener,
        MouseMotionListener {

    private final BoardViewer viewer;

    public MouseBehavior(BoardViewer viewer) {
        this.viewer = viewer;
    }

    public BoardViewer getViewer() {
        return viewer;
    }
}
