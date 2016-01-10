package games.knowyanumba;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import games.knowyanumba.engine.Game;
import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.game.GameManager;
import games.knowyanumba.manager.game.GameState;

public class GameManagerTest {

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
	public void testGameRoundTrip() throws WrongNumberAnsweredException {
		assertEquals(GameState.NEW, this.gameManager.getGameState());
		assertEquals(this.gameManager.createNewGame(), 1);

		assertEquals(GameState.INITIALIZED, this.gameManager.getGameState());
		assertEquals(this.gameManager.startGame(), 2);

		assertEquals(GameState.IN_PROGRESS, this.gameManager.getGameState());
		assertEquals(this.gameManager.processAnswer(any(Integer.class)), 3);
		assertEquals(GameState.IN_PROGRESS, this.gameManager.getGameState());

		when(this.game.next(any(Integer.class))).thenThrow(new WrongNumberAnsweredException(4,3));
		WrongNumberAnsweredException wnae = null;
		try {
			this.gameManager.processAnswer(4);
		} catch (WrongNumberAnsweredException e) {
			wnae = e;
		}
		assertNotNull(wnae);
		assertEquals(GameState.FINISHED, this.gameManager.getGameState());

		this.gameManager.reset();
		assertEquals(GameState.NEW, this.gameManager.getGameState());
	}
}