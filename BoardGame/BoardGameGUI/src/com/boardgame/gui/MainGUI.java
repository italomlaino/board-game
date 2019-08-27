package com.boardgame.gui;

import com.boardgame.db.Database;
import com.boardgame.gui.action.*;
import com.boardgame.gui.component.BoardViewer;
import com.boardgame.gui.component.PlayMouseBehavior;
import com.boardgame.model.Match;
import com.boardgame.model.player.Human;
import com.boardgame.model.player.Human.MoveInput;
import com.boardgame.model.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public abstract class MainGUI extends JFrame {

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    private static MainGUI instance;
    private NewAction newAction;
    private ExitAction exitAction;
    private AboutAction aboutAction;
    private ButtonGroup player1Group;
    private SelectComputerAction player1SelectComputerAction;
    private SelectPlayerAction player1SelectHumanAction;
    private ButtonGroup player2Group;
    private SelectComputerAction player2SelectComputerAction;
    private SelectPlayerAction player2SelectHumanAction;
    protected JMenu mnOptionsPlayer1;
    protected JMenu mnOptionsPlayer2;
    private JMenu mnFile;
    private JMenuItem mniFileNew;
    private JMenuItem mniFileExit;
    protected JMenu mnOptions;
    private JMenu mnHelp;
    private JMenuItem mniHelpAbout;
    private JMenuBar menuBar;
    private Match match;
    private BoardViewer boardViewer;
    private MoveInput input;
    private Player human1Input;
    private Player human2Input;
    private LinkedList<JRadioButtonMenuItem> rmniPlayer1;
    private LinkedList<JRadioButtonMenuItem> rmniPlayer2;

    public MainGUI() {
        initComponents();
    }

    public static MainGUI getInstance() {
        return instance;
    }

    public static void setInstance(MainGUI instance) {
        MainGUI.instance = instance;
    }

    private void initComponents() {
        setSize(DEFAULT_SIZE);
        setPreferredSize(DEFAULT_SIZE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        rmniPlayer1 = new LinkedList<JRadioButtonMenuItem>();
        rmniPlayer2 = new LinkedList<JRadioButtonMenuItem>();

        boardViewer = createBoardViewer();

        Database.getInstance().load();

        match = new Match();
        match.addObserver(boardViewer);

        boardViewer.setBoard(match.getCurrent());
        add(boardViewer, BorderLayout.CENTER);

        input = new PlayMouseBehavior(boardViewer);
        human1Input = new Human(input);
        human2Input = new Human(input);

        createActions();
        createMenu();
        createPlayersButtons();

        if (!rmniPlayer1.isEmpty()) {
            rmniPlayer1.peekLast().doClick();
        }

        if (!rmniPlayer2.isEmpty()) {
            rmniPlayer2.peekLast().doClick();
        }
    }

    private void createActions() {
        exitAction = new ExitAction();
        aboutAction = new AboutAction();
        newAction = new NewAction(match);

        player1SelectComputerAction = new SelectComputerAction(match, (byte) 1);
        player1SelectHumanAction = new SelectPlayerAction(match, (byte) 1,
                human1Input);

        player2SelectComputerAction = new SelectComputerAction(match, (byte) 2);
        player2SelectHumanAction = new SelectPlayerAction(match, (byte) 2,
                human2Input);
    }

    private void createMenu() {
        mniFileNew = new JMenuItem(newAction);
        mniFileExit = new JMenuItem(exitAction);

        mnFile = new JMenu("File");
        mnFile.add(mniFileNew);
        mnFile.add(mniFileExit);

        player1Group = new ButtonGroup();
        player2Group = new ButtonGroup();

        mnOptionsPlayer1 = new JMenu("Player 1");
        mnOptionsPlayer2 = new JMenu("Player 2");

        mnOptions = new JMenu("Options");
        mnOptions.add(mnOptionsPlayer1);
        mnOptions.add(mnOptionsPlayer2);

        mniHelpAbout = new JMenuItem(aboutAction);

        mnHelp = new JMenu("Help");
        mnHelp.add(mniHelpAbout);

        menuBar = new JMenuBar();
        menuBar.add(mnFile);
        menuBar.add(mnOptions);
        menuBar.add(mnHelp);

        setJMenuBar(menuBar);
    }

    private void createPlayersButtons() {

        // Player 1 Buttons
        JRadioButtonMenuItem player1SelectComputerButton = new JRadioButtonMenuItem(
                player1SelectComputerAction);
        player1Group.add(player1SelectComputerButton);
        mnOptionsPlayer1.add(player1SelectComputerButton);
        rmniPlayer1.add(player1SelectComputerButton);

        JRadioButtonMenuItem player1SelectHumanButton = new JRadioButtonMenuItem(
                player1SelectHumanAction);
        player1Group.add(player1SelectHumanButton);
        mnOptionsPlayer1.add(player1SelectHumanButton);
        rmniPlayer1.add(player1SelectHumanButton);

        // Player 2 Buttons
        JRadioButtonMenuItem player2SelectComputerButton = new JRadioButtonMenuItem(
                player2SelectComputerAction);
        player2Group.add(player2SelectComputerButton);
        mnOptionsPlayer2.add(player2SelectComputerButton);
        rmniPlayer2.add(player2SelectComputerButton);

        JRadioButtonMenuItem player2SelectHumanButton = new JRadioButtonMenuItem(
                player2SelectHumanAction);
        player2Group.add(player2SelectHumanButton);
        mnOptionsPlayer2.add(player2SelectHumanButton);
        rmniPlayer2.add(player2SelectHumanButton);
    }

    protected abstract BoardViewer createBoardViewer();

    public BoardViewer getBoardViewer() {
        return boardViewer;
    }

    public Match getMatch() {
        return match;
    }
}
