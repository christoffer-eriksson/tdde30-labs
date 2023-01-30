package se.liu.chrer268.tetris;

public class BoardToTextConverter
{
    public String convertToText(Board board) {
	StringBuilder builder = new StringBuilder();

	for (int i = 0; i < board.getHeight(); i++) {
	    for (int j = 0; j < board.getWidth(); j++) {
		switch (board.getVisibleSquareAt(i, j)) {
		    case EMPTY:
			builder.append("-");
			break;
		    case I:
			builder.append("I");
			break;
		    case J:
			builder.append("J");
			break;
		    case L:
			builder.append("L");
			break;
		    case O:
			builder.append("O");
			break;
		    case S:
			builder.append("S");
			break;
		    case T:
			builder.append("T");
			break;
		    case Z:
			builder.append("Z");
			break;
		    default:
			break;
		}

	    }
	    builder.append("\n");
	}
	String result = builder.toString();
	return result;
    }
}
