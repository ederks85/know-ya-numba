package games.knowyanumba.swing.actions;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.Validate;

import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.game.GameManager;
import games.knowyanumba.swing.panels.ActionMessagePanel;
import games.knowyanumba.swing.panels.InputPanel;
import games.knowyanumba.swing.panels.MessagePanel;

/**
 * Action that is used to handle a client's input in a game.
 * 
 * @author Edwin
 *
 */
public class InputPanelAction extends AbstractGameManagerAction {

	private static final long serialVersionUID = 3014338015350805575L;

	private final InputPanel inputPanel;
	private final ActionMessagePanel scoreScreen;
	private final MessagePanel gamePanel;
	private final MessagePanel messagePanel;

	//TODO Refactor this to handling events from input
	public InputPanelAction(final GameManager gameManager, final InputPanel inputPanel, final MessagePanel messagePanel, final MessagePanel gamePanel, final ActionMessagePanel scoreScreen) {
		super(gameManager);
		Validate.notNull(inputPanel, "InputPanel is null");
		Validate.notNull(messagePanel, "MessagePanel is null");
		Validate.notNull(gamePanel, "GamePanel is null");
		Validate.notNull(scoreScreen, "ScoreScreen is null");

		this.inputPanel = inputPanel;
		this.messagePanel = messagePanel;
		this.gamePanel = gamePanel;
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

			messagePanel.setDisplayMessage("Correct answer: " + source.getText());

			gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(nextCurrent) + "</h1></html>");

			inputPanel.clear();
		} catch(NumberFormatException nfe) {
			messagePanel.setDisplayMessage(nfe.getMessage());
		} catch (WrongNumberAnsweredException wnae) {
			inputPanel.clear();
			inputPanel.disable();

			final JPanel glassPane = (JPanel)SwingUtilities.getRootPane(inputPanel).getGlassPane();
			scoreScreen.setDisplayMessage(
					"<html><h1>Game Over!</h1><br>" + 
					"<center>Your answer: " + wnae.getInvalidAnswer() + "</center><br>" + 
					"<center>Correct answer: " + wnae.getCorrectAnswer() + "</center></html>");
			scoreScreen.requestDefaultButton();
			glassPane.setVisible(true);
			scoreScreen.requestFocus();
		}
	}
}