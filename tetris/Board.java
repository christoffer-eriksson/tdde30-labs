package se.liu.chrer268.tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board
{
    private SquareType[][] squares;
    private int width;
    private int height;
    private final static int MARGIN = 2;
    private final static int DOUBLE_MARGIN = 2 * MARGIN;
    private final static Random RND = new Random();
    private List<BoardListener> boardListeners = new ArrayList<>();
    private TetrominoMaker newPoly;
    private Poly falling;
    private Point fallingPos;
    public int fallingY;
    public int fallingX;
    private int playerScore;
    private TetrisViewer viewer;

    public Board(final int height, final int width) {
	this.viewer = new TetrisViewer(this);
	this.width = width;
	this.height = height;
	this.falling = null;
	this.fallingPos = null;
	this.newPoly = new TetrominoMaker();
	this.squares = new SquareType[height + DOUBLE_MARGIN][width + DOUBLE_MARGIN];
	Map<Integer, Integer> myScore = addRowScore();
	this.playerScore = 0;
	createBoard();
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int getFallingY() {
	return fallingY;
    }

    public Poly getFalling() {
	return falling;
    }

    public int getPlayerScore(){
	return playerScore;
    }

    public SquareType showSquareType(int col, int row) {
	return squares[col + MARGIN][row + MARGIN];
    }

    public SquareType getType(int height, int width) {
	return squares[height][width];
    }

    public void createRandomBoard(int height, int width) {
	// Creates a random board by randomize each square.
    	for (int col = 0; col < height; col++) {
	    for (int row = 0; row < width; row++) {
		squares[col][row] = SquareType.values()
			[RND.nextInt(SquareType.values().length)];
	    }
    	}
    }

    public void createBoard(){
	// Creates a board with only empty sqaures.
	for (int col = 0; col < height + DOUBLE_MARGIN; col++) {
	    for (int row = 0; row < width + DOUBLE_MARGIN; row++) {
		if (col < MARGIN || col >= height + MARGIN || row < MARGIN || row >= width + MARGIN) {
		    squares[col][row] = SquareType.OUTSIDE;
		} else {
		    squares[col][row] = SquareType.EMPTY;
		}
	    }
	}
	this.notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
	boardListeners.add(bl);
    }

    private void notifyListeners() {
	for (BoardListener boardListener : boardListeners) {
	    boardListener.boardChanged();
	}
    }

    public SquareType getVisibleSquareAt(int y, int x) {

	if (falling == null) {
	    // Nothing is falling, return the square on the board.
	    return showSquareType(y, x);
	}

	int x1 = (int) fallingPos.getX();
	int y1 = (int) fallingPos.getY();
	/** Since x1 and y1 represents a block in a tetromino,
	 x2 and y2 needs to remove one to give the right coordinate. */
	int x2 = x1 + falling.getWidth() - 1;
	int y2 = y1 + falling.getHeight() - 1;

	if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
	    // X & Y is inside a block so check what kind of
	    // SquareType the coordinate is on.
	    int internalX = x - x1;
	    int internalY = y - y1;
	    if (falling.getSquareAt(internalY, internalX) == SquareType.EMPTY) {
		return showSquareType(y, x);
	    }
		return falling.getSquareAt(internalY, internalX);

	}
	    // X or Y is outside of the block so return the square on the board.
	    return showSquareType(y, x);
    }

    public void tick() {
	if (falling != null) {
	    moveFalling();
	    if (hasCollision()) {
		placePoly();
		removeRow();
	    }
	} else {
	    setFalling();
	    if (hasCollision()) {
		viewer.handleGameOver(playerScore);
		playerScore = 0;
	    }
	}
	notifyListeners();
    }

    public void placePoly() {
	fallingPos.setLocation(fallingX, fallingY -= 1);
	for (int col = 0; col < falling.getHeight(); col++) {
	    for (int row = 0; row < falling.getWidth(); row++) {
		if (falling.getSquareAt(col, row) != SquareType.EMPTY) {
		    squares[fallingY + col + MARGIN][fallingX + row + MARGIN] = falling.getSquareAt(col, row);
		}
	    }
	}
	falling = null;
    }

    public void moveFalling() {
	fallingPos.setLocation(fallingX, fallingY += 1);
    }

    public void setFalling() {
	falling = newPoly.getPoly(RND.nextInt(newPoly.getNumberOfTypes()));
	fallingX = (width / 2) - 1;
	fallingY = 0;
	fallingPos = new Point(fallingX, fallingY);
    }

    public void move(Direction dir) {
	if (falling != null) {
	    if (dir == Direction.LEFT) {
		fallingPos.setLocation(fallingX -= 1, fallingY);
		if (hasCollision()) {
		    fallingPos.setLocation(fallingX += 1, fallingY);
		}
	    }
	    if (dir == Direction.RIGHT) {
		fallingPos.setLocation(fallingX += 1, fallingY);
		if (hasCollision()) {
		    fallingPos.setLocation(fallingX -= 1, fallingY);
		}
	    }
	    if (dir == Direction.DOWN) {
		fallingPos.setLocation(fallingX, fallingY += 1);
		if (hasCollision()) {
		    fallingPos.setLocation(fallingX, fallingY -= 1);
		}
	    }
	    notifyListeners();
	}
    }

    public void rotate(Direction dir) {
	if (falling != null) {
	    Poly oldPoly = falling;
	    if (dir == Direction.RIGHT) {
		falling = falling.rotateRight();
		if (hasCollision()) {
		    falling = oldPoly;
		}
	    }
	    else {
		/* Rotate right three times to make one left rotation */
		final int threeTimes = 3;
		for (int i = 0; i < threeTimes; i++) {
		    falling = falling.rotateRight();
		    if (hasCollision()) {
			falling = oldPoly;
		    }
		}
	    }
	    notifyListeners();
	}
    }

    public boolean hasCollision() {
	for (int col = 0; col < falling.getHeight(); col++) {
	    for (int row = 0; row < falling.getWidth(); row++) {
		if (falling.getSquareAt(col, row) != SquareType.EMPTY &&
		    getType(col + fallingY + MARGIN, row + fallingX + MARGIN) != SquareType.EMPTY) {
		    return true;
		}
	    }
	}
	return false;

    }

    public void removeFullRow(int column) {
	for (int col = column; col > MARGIN; col--) {
	    for (int row = MARGIN; row < width + MARGIN; row++) {
		squares[col][row] = squares[col - 1][row];
		squares[col - 1][row] = SquareType.EMPTY;
	    }
	}
    }

    public void removeRow() {
	int removedRow = 0;
	for (int col = MARGIN; col < height + MARGIN; col++) {
	    boolean full = true;
	    for (int row = MARGIN; row < width + MARGIN; row++) {
		if (getType(col, row) == SquareType.EMPTY) {
		    full = false;
		    break;
		}
	    }
	    if (full) {
		removeFullRow(col);
		removedRow += 1;
	    }
	    if (removedRow > 0) {
		playerScore += addRowScore().get(removedRow);
		removedRow = 0;
	    }
	}
    }

    private Map<Integer, Integer> addRowScore() {
	return Map.of(1, 100, 2, 200, 3, 300, 4, 400);
    }
}

