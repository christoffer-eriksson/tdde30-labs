package se.liu.chrer268.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisViewerOld
{
    private Board board;
    private JTextArea textArea;
    private BoardToTextConverter convert;
    private JFrame frame = new JFrame("Tetris");

    public TetrisViewerOld(Board board) {
	this.board = board;
	this.textArea = new JTextArea(board.getHeight(), board.getWidth());
	this.convert = new BoardToTextConverter();
	show();
    }

    public void show() {
	frame.setLayout(new BorderLayout());
	frame.add(textArea, BorderLayout.CENTER);
	textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	frame.pack();
	frame.setVisible(true);
    }

    public void timer() {
	final Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(final ActionEvent e) {
		board.createRandomBoard(board.getHeight(), board.getWidth());
		textArea.setText(convert.convertToText(board));
		frame.repaint();
	    }
	};
	final Timer clockTimer = new Timer(1000, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
}
