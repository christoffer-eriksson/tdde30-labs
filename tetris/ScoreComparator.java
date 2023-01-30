package se.liu.chrer268.tetris;

import java.util.Comparator;

public class ScoreComparator implements Comparator<HighScore>
{
    public int compare(final HighScore o1, final HighScore o2) {
	if (o1.getScore() > o2.getScore()) return 1;
	else if (o1.getScore() < o2.getScore()) return -1;
	else return 0;
    }
}
