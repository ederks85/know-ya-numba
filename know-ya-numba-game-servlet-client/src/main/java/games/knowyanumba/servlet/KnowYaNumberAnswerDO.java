package games.knowyanumba.servlet;

import org.apache.commons.lang3.Validate;

public class KnowYaNumberAnswerDO {

	private final KnowYaNumberAnswer answer;
	private final String value;
	private final int currentScore;

	public KnowYaNumberAnswerDO(final KnowYaNumberAnswer answer, final String value, final int currentScore) {
		Validate.notNull(answer, "KnowYaNumberAnswer is null");

		this.answer = answer;
		this.value = value;
		this.currentScore = currentScore;
	}

	public KnowYaNumberAnswer getAnswer() {
		return this.answer;
	}

	public String getValue() {
		return this.value;
	}

	public int getCurrentScore() {
		return this.currentScore;
	}
}