

import java.util.HashMap;

public class LaneProduct implements Cloneable {
	private HashMap scores;
	private ScoreCount currentCumulScores;

	public HashMap getScores() {
		return scores;
	}

	public void setScores(HashMap scores) {
		this.scores = scores;
	}

	public ScoreCount getCurrentCumulScores() {
		return currentCumulScores;
	}

	public void setCurrentCumulScores(ScoreCount currentCumulScores) {
		this.currentCumulScores = currentCumulScores;
	}

	public void markScore(Bowler Cur, int frame, int ball, int score, Lane lane) {
		int[] curScore;
		int index = ((frame - 1) * 2 + ball);
		curScore = (int[]) scores.get(Cur);
		curScore[index - 1] = score;
		scores.put(Cur, curScore);
		currentCumulScores.getScore(Cur, frame, ball, (int[]) scores.get(Cur));
		lane.publish();
	}

	public Object clone() throws CloneNotSupportedException {
		return (LaneProduct) super.clone();
	}
}