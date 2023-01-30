package se.liu.chrer268.tetris;

public class HighScore
{
    private int score;
    private String name;

    public int getScore(){
	return score;
    }
    public String getName() {
	return name;
    }

    public HighScore(String name, int score) {
	this.name = name;
	this.score = score;
    }
}
