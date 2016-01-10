package games.knowyanumba.manager.score;

/**
 * Manager that keeps track of the score achieved by playing the game. This class can be used in multithreaded environments but does not block threads.
 * 
 * @author Edwin
 *
 */
public class ScoreManager {

	private volatile int currentScore;

	public ScoreManager() {
		this.currentScore = 0;
	}

	/**
	 * Increase the current score by 1.
	 */
	public void increaseScore() {
		this.currentScore++;
	}

	/**
	 * @return the current score value.
	 */
	public int getCurrentScore() {
		return this.currentScore;
	}

	/**
	 * Reset the score to 0.
	 */
	public void resetScore() {
		this.currentScore = 0;
	}
}