package games.knowyanumba.servlet;

import org.apache.commons.lang3.Validate;

public class KnowYaNumberAnswerDO {

	private final KnowYaNumberAnswer answer;
	private final String value;

	public KnowYaNumberAnswerDO(final KnowYaNumberAnswer answer, final String value) {
		Validate.notNull(answer, "KnowYaNumberAnswer is null");

		this.answer = answer;
		this.value = value;
	}

	public KnowYaNumberAnswer getAnswer() {
		return answer;
	}

	public String getValue() {
		return value;
	}
}