package games.knowyanumba.manager;

import games.knowyanumba.engine.Game;
import games.knowyanumba.exception.WrongNumberAnsweredException;

/**
 * Manage the lifecycle of {@code Game} objects. This class is can be used in multi-threaded environments. it's internal state is being 
 * synchronized but the methods are not blocking.
 * 
 * @author Edwin
 *
 */
public class GameManager {

	private GameState gameState;
	private Game game;

	/**
	 * Default constructor that should be used by clients.
	 */
	public GameManager() {
		this(new Game());
	}

	/**
	 * Constructor used for testing. 
	 */
	public GameManager(final Game game) {
		this.game = game;
		this.gameState = GameState.NEW;
	}

	public int createNewGame() {
		if (this.gameState != GameState.NEW) {
			throw new IllegalStateException("Cannot create new game because another game is still in progress.");
		}
		final int initValue = this.game.init();
		this.gameState = GameState.INITIALIZED;
		return initValue;
	}

	public int startGame() {
		if (this.gameState != GameState.INITIALIZED) {
			throw new IllegalStateException("Cannot start game because no game has been initialized.");
		} else {
			final int startValue = this.game.start();
			this.gameState = GameState.IN_PROGRESS;
			return startValue;
		}
	}

	public int processAnswer(int answer) throws WrongNumberAnsweredException {
		if (this.gameState != GameState.IN_PROGRESS) {
			throw new IllegalStateException("Cannot process answer because no game is in progress.");
		} else {
			try {
				return this.game.next(answer);
			} catch (WrongNumberAnsweredException e) {
				this.gameState = GameState.FINISHED;
				throw e;
			}
		}
	}

	public void reset() {
		this.game = new Game();
		this.gameState = GameState.NEW;
	}

	public GameState getGameState() {
		return this.gameState;
	}
}