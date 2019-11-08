package games.knowyanumba.swing.actions;

import games.knowyanumba.manager.game.GameManager;
import games.knowyanumba.swing.panels.ActionMessagePanel;
import games.knowyanumba.swing.panels.InputPanel;
import games.knowyanumba.swing.panels.MessagePanel;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;

/**
 * Action that is used on the score screen for finishing the current game initializing a new game.
 * 
 * @author Edwin
 *
 */
public class ScoreScreenAction extends AbstractGameManagerAction {

	private static final long serialVersionUID = 9220129613985137572L;
	private final InputPanel inputPanel;
	private final MessagePanel messagePanel;
	private final MessagePanel gamePanel;
	private final MessagePanel scorePanel;
	private final ActionMessagePanel scoreScreen;

	public ScoreScreenAction(final GameManager gameManager,  final InputPanel inputPanel, final MessagePanel messagePanel, final MessagePanel gamePanel, final MessagePanel scorePanel, final ActionMessagePanel scoreScreen) {
		super(gameManager);
		if (inputPanel == null) {
			throw new IllegalArgumentException("InputPanel is null");
		}
		if (messagePanel == null) {
			throw new IllegalArgumentException("MessagePanel is null");
		}
		if (gamePanel == null) {
			throw new IllegalArgumentException("GamePanel is null");
		}
		if (scoreScreen == null) {
			throw new IllegalArgumentException("ScoreScreen is null");
		}

		this.inputPanel = inputPanel;
		this.messagePanel = messagePanel;
		this.gamePanel = gamePanel;
		this.scorePanel = scorePanel;
		this.scoreScreen = scoreScreen;

		this.putValue(Action.NAME, "Retry");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Finish the current game
		getGameManager().reset();

		// Initialize the game
		final int initialPrevious = getGameManager().createNewGame();
		final int initialCurrent = getGameManager().startGame();

		this.messagePanel.setDisplayMessage("Your first answer is: " + initialPrevious);
		this.gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(initialCurrent) + "</h1></html>");
		this.scorePanel.setDisplayMessage("Score: " + getGameManager().getScoreManager().getCurrentScore());

		final JPanel glassPane = (JPanel)SwingUtilities.getRootPane(inputPanel).getGlassPane();
		glassPane.setVisible(false);

		SwingUtilities.getRootPane(this.scoreScreen).repaint();
		this.inputPanel.enable();
		this.inputPanel.requestFocus();
	}
}