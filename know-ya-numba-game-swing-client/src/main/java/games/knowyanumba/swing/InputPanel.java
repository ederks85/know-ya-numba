package games.knowyanumba.swing;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.Validate;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 2968810068658634760L;

	private final JTextField textField;

	public InputPanel() {
		this.textField = new JTextField();

		this.setLayout(new BorderLayout());
		this.add(this.textField);
	}

	public void setAction(Action action) {
		Validate.notNull(action, "Action is null");
		this.textField.setAction(action);
	}

	public void clear() {
		this.textField.setText("");
	}

	public void requestFocus() {
		this.textField.requestFocusInWindow();
	}

	public void disable() {
		this.textField.setEnabled(false);
	}
}