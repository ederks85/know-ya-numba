package games.knowyanumba.swing.actions;

import javax.swing.AbstractAction;

import org.apache.commons.lang3.Validate;

import games.knowyanumba.manager.GameManager;

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
		Validate.notNull(gameManager, "GameManager is null");
		this.gameManager = gameManager;
	}

	protected GameManager getGameManager() {
		return gameManager;
	}
}