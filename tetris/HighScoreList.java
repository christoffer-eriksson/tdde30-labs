package se.liu.chrer268.tetris;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreList extends ScoreComparator
{
    private static final String ADRESS = System.getProperty("user.dir") + File.separator + "score.json";
    private static final File SCORE_FILE = new File(ADRESS);
    private List<HighScore> highscore = new ArrayList<>();
    public HighScoreList() {

    }

    public List<HighScore> getHighscore() {
	return highscore;
    }

    public HighScore getScore(int index) {
	return highscore.get(index);
    }

    public int getLength() {
	return highscore.size();
    }

    public void addScore(HighScore score) {
	final int fullScoreboard = 11;

	highscore.add(score);
	highscore.sort(new ScoreComparator());
	Collections.reverse(highscore);
	if (highscore.size() == fullScoreboard) {
	    final int lowestScore = 10;
	    highscore.remove(lowestScore);
	}
    }

    public void saveFile() throws FileNotFoundException {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String readFromJson = gson.toJson(this);
	try (PrintWriter file = new PrintWriter(SCORE_FILE)) {
	    file.write(readFromJson);
	}
	highscore.clear();
    }

    public void readFile() throws IOException, FileNotFoundException {
	Gson gson = new Gson();
	try (FileReader fileReader = new FileReader(SCORE_FILE)) {
	    HighScoreList highscores = gson.fromJson(fileReader, HighScoreList.class);
	    for (HighScore score : highscores.highscore) {
		this.addScore(score);
	    }
	}
    }

    public StringBuilder getScores() {
	StringBuilder scores = new StringBuilder();
	scores.append("Highscore: ");
	scores.append("\n");
	for (int i = 0; i < getLength(); i++) {
	    String name = getScore(i).getName();
	    int score = getScore(i).getScore();
	    scores.append(name).append(": ");
	    scores.append(score);
	    scores.append("\n");
	}
	return scores;
    }
}
