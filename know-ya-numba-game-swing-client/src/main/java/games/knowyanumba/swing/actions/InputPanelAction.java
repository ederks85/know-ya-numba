package games.knowyanumba.swing.actions;

import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.game.GameManager;
import games.knowyanumba.swing.panels.ActionMessagePanel;
import games.knowyanumba.swing.panels.InputPanel;
import games.knowyanumba.swing.panels.MessagePanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;

/**
 * Action that is used to handle a client's input in a game.
 * 
 * @author Edwin
 *
 */
public class InputPanelAction extends AbstractGameManagerAction {

	private static final long serialVersionUID = 3014338015350805575L;

	private final InputPanel inputPanel;
	private final MessagePanel messagePanel;
	private final MessagePanel gamePanel;
	private final MessagePanel scorePanel;
	private final ActionMessagePanel scoreScreen;

	//TODO Refactor this to handling events from input
	public InputPanelAction(final GameManager gameManager, final InputPanel inputPanel, final MessagePanel messagePanel, final MessagePanel gamePanel, final MessagePanel scorePanel, final ActionMessagePanel scoreScreen) {
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
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		final JTextField source;
		if (event.getSource() instanceof JTextField) {
			source = (JTextField)event.getSource();
		} else {
			throw new IllegalStateException("Invalid source: " + event.getSource());
		}
		try {
			final int answer = Integer.parseInt(source.getText());
			final int nextCurrent = getGameManager().processAnswer(answer);

			this.messagePanel.setDisplayMessage("Correct answer: " + source.getText());

			this.gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(nextCurrent) + "</h1></html>");
			this.scorePanel.setDisplayMessage("Score: " + getGameManager().getScoreManager().getCurrentScore());

			inputPanel.clear();
		} catch(NumberFormatException nfe) {
			this.messagePanel.setDisplayMessage(nfe.getMessage());
		} catch (WrongNumberAnsweredException wnae) {
			this.inputPanel.clear();
			this.inputPanel.disable();

			final JPanel glassPane = (JPanel)SwingUtilities.getRootPane(inputPanel).getGlassPane();
			this.scoreScreen.setDisplayMessage(
					"<html><h1>Game Over!</h1><br>" + 
					"<center>Your answer: " + wnae.getInvalidAnswer() + "</center><br>" + 
					"<center>Correct answer: " + wnae.getCorrectAnswer() + "</center><br />" +
					"<center><b>Your score: " + getGameManager().getScoreManager().getCurrentScore() + "</b></center></html>");
			this.scoreScreen.requestDefaultButton();
			glassPane.setVisible(true);
			this.scoreScreen.requestFocus();
		}
	}
}