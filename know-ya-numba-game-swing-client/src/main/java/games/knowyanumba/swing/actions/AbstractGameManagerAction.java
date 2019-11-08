package games.knowyanumba.swing.actions;

import games.knowyanumba.manager.game.GameManager;

import javax.swing.AbstractAction;

/**
 * Action that acts as a base for action that can be performed on a {@code GameManager}.
 * 
 * @author Edwin
 *
 */
public abstract class AbstractGameManagerAction extends AbstractAction {

	private static final long serialVersionUID = -3676082738072246923L;

	private GameManager gameManager;

	public AbstractGameManagerAction(final GameManager gameManager) {
		if (gameManager == null) {
			throw new IllegalArgumentException("GameManager is null");
		}
		this.gameManager = gameManager;
	}

	protected GameManager getGameManager() {
		return gameManager;
	}
}