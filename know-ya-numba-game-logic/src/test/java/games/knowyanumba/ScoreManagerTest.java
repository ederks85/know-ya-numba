package games.knowyanumba;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import games.knowyanumba.engine.Game;
import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.game.GameManager;

public class ScoreManagerTest {

	@Mock(name="game")
	private Game game;

	private GameManager gameManager;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(game.init()).thenReturn(1);
		when(game.start()).thenReturn(2);
		when(game.next(any(Integer.class))).thenReturn(3);

		this.gameManager = new GameManager(this.game);
	}

	@Test
	public void testScoreManager() throws WrongNumberAnsweredException {
		assertEquals(this.gameManager.createNewGame(), 1);
		assertEquals(0, this.gameManager.getScoreManager().getCurrentScore());

		assertEquals(this.gameManager.startGame(), 2);
		assertEquals(0, this.gameManager.getScoreManager().getCurrentScore());

		assertEquals(this.gameManager.processAnswer(any(Integer.class)), 3);
		assertEquals(1, this.gameManager.getScoreManager().getCurrentScore());

		assertEquals(this.gameManager.processAnswer(any(Integer.class)), 3);
		assertEquals(2, this.gameManager.getScoreManager().getCurrentScore());

		this.gameManager.reset();
		assertEquals(0, this.gameManager.getScoreManager().getCurrentScore());
	}
}