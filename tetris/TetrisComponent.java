package se.liu.chrer268.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private final static int SQUARE_SIZE = 50;
    private final EnumMap<SquareType, Color> mapColor = createColorMap();

    public TetrisComponent(Board board) {
	this.board = board;
    }

    public Dimension getPreferredSize() {
	final int menuExtension = 18;
	int preferredWidth = board.getWidth() * SQUARE_SIZE;
	int preferredHeight = board.getHeight() * SQUARE_SIZE + menuExtension;
	return new Dimension(preferredWidth, preferredHeight);
    }

    private static EnumMap<SquareType, Color> createColorMap() {
	EnumMap<SquareType, Color> typeColor = new EnumMap<>(SquareType.class);
	typeColor.put(SquareType.I, Color.CYAN);
	typeColor.put(SquareType.O, Color.YELLOW);
	typeColor.put(SquareType.T, Color.MAGENTA);
	typeColor.put(SquareType.S, Color.GREEN);
	typeColor.put(SquareType.Z, Color.RED);
	typeColor.put(SquareType.J, Color.BLUE);
	typeColor.put(SquareType.L, Color.ORANGE);
	typeColor.put(SquareType.EMPTY, Color.BLACK);
	return typeColor;
	}

    @Override protected void paintComponent(Graphics g) {
	final int xOffset = 10;
	final int yOffset = 30;
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	for (int y = 0; y < board.getHeight(); y++) {
	    for (int x = 0; x < board.getWidth(); x++) {
		SquareType square = board.getVisibleSquareAt(y, x);
		Color squareColor = mapColor.get(square);
		g2d.setColor(squareColor);
		g2d.fill3DRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, true);
		g2d.setColor(Color.black);
		g2d.drawRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	    }
	}
	g2d.setColor(Color.white);
	g2d.setFont(new Font("Comic Sans", Font.BOLD, 25));
	String score = "Score: " + board.getPlayerScore();
	g2d.drawString(score, xOffset, yOffset);
    }

    @Override public void boardChanged() {
	repaint();
    }
}
