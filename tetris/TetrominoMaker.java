package se.liu.chrer268.tetris;

public class TetrominoMaker
{
    public int getNumberOfTypes() {
	return (SquareType.values().length - 2);
    }

    public Poly getPoly(int n) {
	switch (SquareType.values()[n]) {
	    case I:
		return new Poly(createI());
	    case O:
		return new Poly(createO());
	    case T:
		return new Poly(createT());
	    case S:
		return new Poly(createS());
	    case Z:
		return new Poly(createZ());
	    case J:
		return new Poly(createJ());
	    case L:
		return new Poly(createL());
	    default:
		throw new IllegalArgumentException("Invalid index: " + n);
	}
    }

    private SquareType[][] createI() {
	return new SquareType[][] {
	    { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
	    { SquareType.I, SquareType.I, SquareType.I, SquareType.I },
	    { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
	    { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
	};
    }
    private SquareType[][] createL() {
	return new SquareType[][] {
		{ SquareType.EMPTY, SquareType.EMPTY, SquareType.L },
		{ SquareType.L, SquareType.L, SquareType.L },
		{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } };
    }
    private SquareType[][] createS() {
	return new SquareType[][] {
		{ SquareType.EMPTY, SquareType.S, SquareType.S },
		{ SquareType.S, SquareType.S, SquareType.EMPTY },
		{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } };
    }
    private SquareType[][] createZ() {
	return new SquareType[][] {
		{ SquareType.Z, SquareType.Z, SquareType.EMPTY },
		{ SquareType.EMPTY, SquareType.Z, SquareType.Z },
		{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } };
    }
    private SquareType[][] createT() {
	return new SquareType[][] {
		{ SquareType.EMPTY, SquareType.T, SquareType.EMPTY },
		{ SquareType.T, SquareType.T, SquareType.T },
		{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY } };
    }
    private SquareType[][] createO() {
	return new SquareType[][] {
		{ SquareType.O, SquareType.O },
		{ SquareType.O, SquareType.O } };
    }
    private SquareType[][] createJ() {
	return new SquareType[][] {
		{ SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
		{ SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
		{ SquareType.J, SquareType.J, SquareType.EMPTY } };
    }
}
