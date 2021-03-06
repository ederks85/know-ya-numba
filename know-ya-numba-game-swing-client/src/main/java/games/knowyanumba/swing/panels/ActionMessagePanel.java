package games.knowyanumba.swing.panels;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class ActionMessagePanel extends MessagePanel {

	private static final long serialVersionUID = 8632306653917660259L;

	private final JButton actionButton;

	public ActionMessagePanel() {
		this.actionButton = new JButton();

		this.add(this.actionButton, BorderLayout.SOUTH);
	}

	public void requestFocus() {
		this.actionButton.requestFocusInWindow();
	}

	public void setAction(final Action action) {
		if (action == null) {
			throw new IllegalArgumentException("Action is null");
		}
		this.actionButton.setAction(action);
	}

	public void requestDefaultButton() {
		SwingUtilities.getRootPane(this).setDefaultButton(this.actionButton);
	}
}