package se.liu.chrer268.tetris;

public class Poly
{
    private SquareType[][] polyShape;
    private int width;
    private int height;
    private int y;
    private int x;

    public Poly(final SquareType[][] polyShape) {
	this.polyShape = polyShape;
	height = polyShape.length;
	width = polyShape[0].length;
    }

    public int getY() {
	return y;
    }

    public int getX() {
	return x;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public SquareType getSquareAt(final int y, final int x) {
	return polyShape[y][x];
    }

    public Poly rotateRight() {
	// "Rotates" a tetromino by copy a nearby square and
	// paste it in another place

	int size = this.polyShape.length;
	Poly newPoly = new Poly(new SquareType[size][size]);

	for (int r = 0; r < getHeight(); r++) {
	    for (int c = 0; c < getWidth(); c++) {
		newPoly.polyShape[c][size - 1 - r] = this.polyShape[r][c];
	    }
	}
	return newPoly;
    }
}
