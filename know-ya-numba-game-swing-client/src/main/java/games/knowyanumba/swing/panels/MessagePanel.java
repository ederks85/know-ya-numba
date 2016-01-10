package games.knowyanumba.swing.panels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 8632306653917660259L;

	private final JLabel display;

	public MessagePanel() {
		this.display = new JLabel();
		this.display.setHorizontalAlignment(SwingConstants.CENTER);
		this.display.setVerticalAlignment(SwingConstants.CENTER);

		this.setLayout(new BorderLayout());
		this.add(this.display, BorderLayout.CENTER);
	}

	public void setDisplayMessage(final String message) {
		this.display.setText(message);
	}
}