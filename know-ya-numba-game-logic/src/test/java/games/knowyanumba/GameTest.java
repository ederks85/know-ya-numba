package games.knowyanumba;

import static org.junit.Assert.*;

import org.junit.Test;

import games.knowyanumba.engine.Game;
import games.knowyanumba.exception.WrongNumberAnsweredException;

public class GameTest {

	@Test
	public void testGame() {
		Game game = new Game();

		final int initialNumber = game.init();
		assertTrue(initialNumber >= 0);
		assertTrue(initialNumber < Game.MAX);

		final int currentNumber = game.start();
		assertTrue(currentNumber >= 0);
		assertTrue(currentNumber < Game.MAX);

		Exception thrownAtFirstAnswer = null;
		try {
			final int nextNumber = game.next(initialNumber);
			assertTrue(nextNumber >= 0);
			assertTrue(nextNumber < Game.MAX);
		} catch (WrongNumberAnsweredException e) {
			e.printStackTrace();
			thrownAtFirstAnswer = e;
		}
		assertNull(thrownAtFirstAnswer);

		Exception thrownAtSecondAnswer = null;
		try {
			final int nextNumber = game.next(currentNumber);
			assertTrue(nextNumber >= 0);
			assertTrue(nextNumber < Game.MAX);
		} catch (WrongNumberAnsweredException e) {
			e.printStackTrace();
			thrownAtSecondAnswer = e;
		}
		assertNull(thrownAtSecondAnswer);
	}

	@Test(expected=WrongNumberAnsweredException.class)
	public void testWrongAnswer() throws WrongNumberAnsweredException {
		Game game = new Game();

		final int initialNumber = game.init();
		assertTrue(initialNumber >= 0);
		assertTrue(initialNumber < Game.MAX);

		game.next(Math.negateExact(initialNumber));
	}
}