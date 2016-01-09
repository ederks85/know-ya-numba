package games.knowyanumba.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.GameManager;

public class KnowYaNumbaJFrame extends JFrame {

	private static final long serialVersionUID = 5180224106006182308L;

	public KnowYaNumbaJFrame() {
		this.setTitle("Know-ya-numba Game");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Create the game
		final GameManager knowYaNumbaGameManager = new GameManager();

		// Configure GUI
		this.setLayout(new BorderLayout());

		final MessagePanel messagePanel = new MessagePanel();
		messagePanel.setDisplayMessage("KNOW YA NUMBA");
		this.add(messagePanel, BorderLayout.NORTH);

		final MessagePanel gamePanel = new MessagePanel();
		this.add(gamePanel, BorderLayout.CENTER);

		final InputPanel inputPanel = new InputPanel();
		inputPanel.setAction(new AbstractAction() {
			private static final long serialVersionUID = 1L;
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
					final int nextCurrent = knowYaNumbaGameManager.processAnswer(answer);

					messagePanel.setDisplayMessage("Correct answer: " + source.getText());

					gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(nextCurrent) + "</h1></html>");

					inputPanel.clear();
				} catch(NumberFormatException nfe) {
					messagePanel.setDisplayMessage(nfe.getMessage());
				} catch (WrongNumberAnsweredException wnae) {
					inputPanel.disable();
					final JPanel glassPane = (JPanel)SwingUtilities.getRootPane(inputPanel).getGlassPane();
					glassPane.setLayout(new BorderLayout());
					MessagePanel gameOverPanel = new MessagePanel();
					gameOverPanel.setBackground(Color.WHITE);
					gameOverPanel.setDisplayMessage(
							"<html><h1>Game Over!</h1><br>" + 
							"<center>Your answer: " + wnae.getInvalidAnswer() + "</center><br>" + 
							"<center>Correct answer: " + wnae.getCorrectAnswer() + "</center></html>");
					glassPane.add(gameOverPanel, BorderLayout.CENTER);
					glassPane.setVisible(true);
				}
			}
		});
		this.add(inputPanel, BorderLayout.SOUTH);

		// Initialize the game
		final int initialPrevious = knowYaNumbaGameManager.createNewGame();
		final int initialCurrent = knowYaNumbaGameManager.startGame();

		messagePanel.setDisplayMessage("Your first answer is: " + initialPrevious);
		gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(initialCurrent) + "</h1></html>");

		inputPanel.requestFocus();
		this.setVisible(true);
	}
}