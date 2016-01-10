package games.knowyanumba.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import games.knowyanumba.manager.game.GameManager;
import games.knowyanumba.swing.actions.InputPanelAction;
import games.knowyanumba.swing.actions.ScoreScreenAction;
import games.knowyanumba.swing.panels.ActionMessagePanel;
import games.knowyanumba.swing.panels.InputPanel;
import games.knowyanumba.swing.panels.MessagePanel;

public class KnowYaNumbaJFrame extends JFrame {

	private static final long serialVersionUID = 5180224106006182308L;

	private final ActionMessagePanel scoreScreenPanel;

	public KnowYaNumbaJFrame() {
		this.setTitle("Know-ya-numba Game");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// Create the game
		final GameManager knowYaNumbaGameManager = new GameManager();

		// Configure GUI
		this.setLayout(new BorderLayout());

		// Configure score screen
		final JPanel glassPane = (JPanel)this.getRootPane().getGlassPane();
		glassPane.setLayout(new BorderLayout());
		this.scoreScreenPanel = new ActionMessagePanel();
		glassPane.setBackground(Color.WHITE);
		glassPane.add(this.scoreScreenPanel, BorderLayout.CENTER);

		// Configure game panels
		final MessagePanel messagePanel = new MessagePanel();
		messagePanel.setDisplayMessage("KNOW YA NUMBA");
		this.add(messagePanel, BorderLayout.NORTH);

		final MessagePanel gamePanel = new MessagePanel();
		this.add(gamePanel, BorderLayout.CENTER);

		final InputPanel inputPanel = new InputPanel();
		this.add(inputPanel, BorderLayout.SOUTH);

		final MessagePanel scorePanel = new MessagePanel();
		this.add(scorePanel, BorderLayout.EAST);
		// Set actions for handling client input
		inputPanel.setAction(new InputPanelAction(knowYaNumbaGameManager, inputPanel, messagePanel, gamePanel, scorePanel, this.scoreScreenPanel));
		this.scoreScreenPanel.setAction(new ScoreScreenAction(knowYaNumbaGameManager, inputPanel, messagePanel, gamePanel, scorePanel, this.scoreScreenPanel));

		// Initialize the game
		final int initialPrevious = knowYaNumbaGameManager.createNewGame();
		final int initialCurrent = knowYaNumbaGameManager.startGame();

		messagePanel.setDisplayMessage("Your first answer is: " + initialPrevious);
		gamePanel.setDisplayMessage("<html><h1>" + String.valueOf(initialCurrent) + "</h1></html>");
		scorePanel.setDisplayMessage("Score: " + knowYaNumbaGameManager.getScoreManager().getCurrentScore());

		// Launch game GUI
		inputPanel.requestFocus();
		this.setVisible(true);
	}
}