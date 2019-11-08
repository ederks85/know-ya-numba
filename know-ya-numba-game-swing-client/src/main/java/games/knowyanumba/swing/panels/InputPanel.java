package games.knowyanumba.swing.panels;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 2968810068658634760L;

	private final JTextField textField;

	public InputPanel() {
		this.textField = new JTextField();

		this.setLayout(new BorderLayout());
		this.add(this.textField);
	}

	public void setAction(Action action) {
		if (action == null) {
			throw new IllegalArgumentException("Action is null");
		}
		this.textField.setAction(action);
	}

	public void clear() {
		this.textField.setText("");
	}

	public void requestFocus() {
		this.textField.requestFocusInWindow();
	}

	public void enable() {
		this.textField.setEnabled(true);
	}

	public void disable() {
		this.textField.setEnabled(false);
	}
}