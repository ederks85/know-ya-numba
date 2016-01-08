package games.knowyanumba.exception;

/**
 * Exception that can be thrown when a wrong number is being anwered in the game.
 * 
 * @author Edwin
 *
 */
public class WrongNumberAnsweredException extends Exception {

	private static final long serialVersionUID = -5800057832146786122L;

	private final int invalidAnswer;
	private final int correctAnswer;

	public WrongNumberAnsweredException(final int invalidAnswer, int correctAnswer) {
		this.invalidAnswer = invalidAnswer;
		this.correctAnswer = correctAnswer;
	}

	public int getInvalidAnswer() {
		return this.invalidAnswer;
	}

	public int getCorrectAnswer() {
		return this.correctAnswer;
	}
}