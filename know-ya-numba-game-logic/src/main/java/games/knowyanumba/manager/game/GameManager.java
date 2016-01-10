package games.knowyanumba.manager.game;

import games.knowyanumba.engine.Game;
import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.score.ScoreManager;

/**
 * Manage the lifecycle of {@code Game} objects. This class is can be used in multi-threaded environments. it's internal state is being 
 * synchronized but the methods are not blocking.
 * 
 * @author Edwin
 *
 */
public class GameManager {

	private volatile GameState gameState;
	private volatile Game game;

	private ScoreManager scoreManager;
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
		this.scoreManager = new ScoreManager();
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
				final int newCurrentValue = this.game.next(answer);
				this.scoreManager.increaseScore();
				return newCurrentValue;
			} catch (WrongNumberAnsweredException e) {
				this.gameState = GameState.FINISHED;
				throw e;
			}
		}
	}

	/**
	 * Reset the game to it's initial state as if it was newly constructed
	 */
	public void reset() {
		this.game = new Game();
		this.gameState = GameState.NEW;
		this.scoreManager.resetScore();
	}

	/**
	 * @return never null
	 */
	public GameState getGameState() {
		return this.gameState;
	}

	/**
	 * @return never null
	 */
	public ScoreManager getScoreManager() {
		return this.scoreManager;
	}
}