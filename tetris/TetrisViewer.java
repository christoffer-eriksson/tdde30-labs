package se.liu.chrer268.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TetrisViewer
{
    private final JMenuBar menuBar = new JMenuBar();
    private JFrame frame = new JFrame("Tetris");
    private TetrisComponent tetro;
    private InputListener input;
    private Board tetrisBoard;
    private HighScoreList highScores = new HighScoreList();
    private StartIcon startIcon = new StartIcon();
    private final static int THREE_SECONDS = 3000;

    public TetrisViewer(Board board) {
	this.tetrisBoard = board;
	this.input = new InputListener(tetrisBoard);
	this.tetro = new TetrisComponent(tetrisBoard);
	board.addBoardListener(tetro);
    }

    public static void main(String[] args) {
	Board board = new Board(15, 10);
	TetrisViewer window = new TetrisViewer(board);
	window.readKeyInput();
	window.showStartIcon();
	window.showFrame();
	window.timer();
    }

    private void createNewBoard() {
	frame.dispose();
	tetrisBoard.createBoard();
	TetrisViewer window = new TetrisViewer(tetrisBoard);
    }

    private void showStartIcon() throws HeadlessException {
	final JFrame frame = new JFrame("Tetris");
	frame.add(new StartIcon());
	frame.setSize(startIcon.getIconWidth(), startIcon.getIconHeight());
	placeWindowInCenter(frame);
	frame.setVisible(true);
	try {
	    Thread.sleep(THREE_SECONDS);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	frame.dispose();
    }

    public void showFrame() {
	createFile();
	createInfo();
	setUpFrame();
    }

    public void createFile() {
	final JMenu file = new JMenu("File");
	final JMenuItem quitButton = new JMenuItem("Quit");

	quitButton.addActionListener(new MenuButtonAction(MenuOption.QUIT));

	file.add(quitButton);
	menuBar.add(file);
    }

    private void createInfo() {
	final JMenu about = new JMenu("About");
	final JMenuItem infoButton = new JMenuItem("Information");

	infoButton.addActionListener(new MenuButtonAction(MenuOption.INFORMATION));

	about.add(infoButton);
	menuBar.add(about);
    }

    public void setUpFrame() {
	frame.setLayout(new GridLayout(1, 1));
	frame.add(tetro, BorderLayout.CENTER);
	frame.pack();
	frame.setJMenuBar(menuBar);
	frame.setVisible(true);
	placeWindowInCenter(frame);
    }

    public void handleGameOver(int playerScore) {
	String player = "";
	while (player.isEmpty()) {
	    player = JOptionPane.showInputDialog(null, "Game Over! Please type your name", "Game Over",  JOptionPane.PLAIN_MESSAGE);
	    if (player == null) {
		player = "No Name";
	    }
	}
	try {
	    highScores.readFile();
	    handleHighscore(player, playerScore);
	    startNewGame();
	} catch (FileNotFoundException ignore) {
	    if (confirmOption("Could not seem to find an existing score.json. Create a new file?", "Error") == JOptionPane.YES_OPTION) {
		try {
		    handleHighscore(player, playerScore);
		    startNewGame();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}

	    } else {
		startNewGame();
	    }
	} catch (IOException ignore) {
	    if (confirmOption("Something went wrong while trying to read score.json. Try again?", "Error") == JOptionPane.YES_OPTION) {
		handleGameOver(playerScore);
	    } else {
		startNewGame();
	    }
	}
    }

    private int confirmOption(final String message, final String title) {
	return JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    private void startNewGame() {
	if (confirmOption("Want to start a new game?", "New Game") == JOptionPane.YES_OPTION) {
	    createNewBoard();
	} else {
	    System.exit(0);
	}

    }

    private void handleHighscore(final String player, final int playerScore) throws FileNotFoundException {
	HighScore score = new HighScore(player, playerScore);
	highScores.addScore(score);
	showHighScore();
	highScores.saveFile();
    }

    private void showHighScore() {
	/*
	 * Creates a string for all the scores in the score file and shows it.
	 */
	StringBuilder highScore = highScores.getScores();
	JOptionPane.showMessageDialog(null, highScore);
    }

    public void placeWindowInCenter(Window frame) {
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	int centerX = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	int centerY = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	frame.setLocation(centerX, centerY);
    }

    public void readKeyInput() {
	input.bindKey(frame);
    }

    public void timer() {
	final Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(final ActionEvent e) {
		tetrisBoard.tick();
	    }
	};
	final Timer clockTimer = new Timer(500, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    private class MenuButtonAction extends AbstractAction{

	private MenuOption menu;

	private MenuButtonAction(MenuOption menu){
	    this.menu = menu;
	}

	@Override public void actionPerformed(final ActionEvent e) {
	    MenuButton.menuClicked(menu);
	}
    }
}
