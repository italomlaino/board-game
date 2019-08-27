package com.boardgame.model;

import com.boardgame.model.player.Player;

import java.awt.*;
import java.util.List;
import java.util.Observable;

public class Match extends Observable {

    private Player player1;
    private Player player2;
    private Player playerRound;
    private Board current;

    public Match() {
        this.player1 = null;
        this.player2 = null;

        setPlayerRound(null);
        setCurrent(BoardFactory.getDefaultFactory().makeBoard());
    }

    public void start() {

        if (player1 == null || player2 == null) {
            return;
        }

        if (playerRound == null) {
            setPlayerRound(player1);
            player1.doMove(this);
        }
    }

    public void nextMove(Board next) {
        if (playerRound == null) {
            return;
        }

        if (!isValidMove(next)) {
            return;
        }

        if (next != null) {
            setCurrent(next);
        }

        if (!getCurrent().isTerminal()) {

            // if (getAllValidMovePoints(current,
            // getPlayerOpponent(playerRound)) != null) {
            // setPlayerRound(getPlayerOpponent(playerRound));
            // }

            setPlayerRound(getPlayerOpponent(playerRound));
            playerRound.doMove(this);
        }
    }

    private boolean isValidMove(Board move) {
        List<Board> validMoves = getAllValidMoves(current, playerRound);
        if (!validMoves.isEmpty()) {
            if (move == null) {
                return false;
            } else if (!validMoves.contains(move)) {
                return false;
            }
        } else {
            if (move != null) {
                return false;
            }
        }

        return true;
    }

    public List<Point> getAllValidMovePoints(Board board, Player player) {
        return board.getAllValidMovePoints(getPlayerID(player));
    }

    public List<Board> getAllValidMoves(Board board, Player player) {
        return board.getAllValidMoves(getPlayerID(player));
    }

    public byte getPlayerID(Player player) {

        if (player1.equals(player)) {
            return Board.PLAYER1_ID;
        } else if (player2.equals(player2)) {
            return Board.PLAYER2_ID;
        } else {
            return Board.NOT_FOUND_ID;
        }
    }

    public Player getPlayerOpponent(Player player) {

        if (player.equals(player1)) {
            return player2;
        } else if (player.equals(player2)) {
            return player1;
        } else {
            return null;
        }

    }

    public void setPlayer1(Player player1) {
        if (this.player1 == null) {
            this.player1 = player1;

            start();

            return;
        } else if (this.player1.equals(player1)) {
            return;
        } else if (playerRound == null) {
            this.player1 = player1;
        } else if (playerRound.equals(this.player1)) {
            setPlayerRound(player1);

            this.player1 = player1;
            this.player1.doMove(this);
        } else {
            this.player1 = player1;
        }

    }

    public void setPlayer2(Player player2) {

        if (this.player2 == null) {
            this.player2 = player2;

            start();

            return;
        } else if (this.player2.equals(player2)) {
            return;
        } else if (playerRound == null) {
            this.player2 = player2;
        } else if (playerRound.equals(this.player2)) {
            setPlayerRound(player2);

            this.player2 = player2;
            this.player2.doMove(this);
        } else {
            this.player2 = player2;
        }

    }

    public Board getCurrent() {
        return current;
    }

    private void setCurrent(Board current) {
        this.current = current;

        setChanged();
        notifyObservers();
    }

    public Player getPlayerRound() {
        return playerRound;
    }

    private void setPlayerRound(Player playerRound) {
        this.playerRound = playerRound;

        setChanged();
        notifyObservers();
    }

    public void reset() {
        setCurrent(BoardFactory.getDefaultFactory().makeBoard());
        setPlayerRound(null);

        start();
    }
}
