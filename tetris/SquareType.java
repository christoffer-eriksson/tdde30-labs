package se.liu.chrer268.tetris;

import java.util.Random;

public enum SquareType
{
    I, O, T, S, Z, J, L, EMPTY, OUTSIDE;

    public static void main(String[] args) {
	Random rnd = new Random();
	// For-loop that loops 25 times and prints random indexes
	// from SquareType.
	for (int i = 0; i < 25; i++) {
	    // System.out.println(rnd.nextInt(100));
	    System.out.println(rnd.nextInt(SquareType.values().length));
	}
    }
}
