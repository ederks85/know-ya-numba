package games.knowyanumba.engine;

import java.util.Random;

import games.knowyanumba.exception.WrongNumberAnsweredException;

/**
 * Implements the Know-ya-numba game.
 * 
 * 1. A client receives an initial "previous" value by calling {@link #init()}
 * 2. A client receives an initial "current" value by calling {@link #start()}
 * 3. A client calls the {@link #next(int)} with the last know "previous" value
 * 4. If the last know "previous" value matches, the current value becomes the next "previous" value. If the
 * value doesn't match, a {@code WrongNumberAnsweredException} is thrown and the game will be finished.
 * 5. A new "current" value is generated and returned to the client.
 * 
 * @author Edwin
 *
 */
public class Game {

	public static int MAX = 100;

	private final Random random;

	private volatile int previous;
	private volatile int current;

	public Game() {
		this.random = new Random();
	}

	/**
	 * Initialize the game.
	 * 
	 * @return the  number that is initially set as the "previous" value.
	 */
	public int init() {
		this.previous = this.random.nextInt(MAX);
		return this.previous;
	}

	/**
	 * Start the game and receive the first "current" value.
	 * 
	 * @return the number that is the first valid answer parameter after {@link #init()} was called.
	 */
	public int start() {
		this.current = this.random.nextInt(MAX);
		return this.current;
	}

	/**
	 * Provide the previous number that was generated by {@link #init()} or {@link #start()};
	 * 
	 * @param answer the previous number
	 * 
	 * @return the next current number
	 */
	public int next(int answer) throws WrongNumberAnsweredException {
		if (this.previous == answer) {
			// The current value becomes the new previous
			this.previous = this.current;

			// Generate and set a new current value and return this
			this.current = this.random.nextInt(MAX);
			return this.current;
		} else {
			throw new WrongNumberAnsweredException(answer, this.previous);
		}
	}
}