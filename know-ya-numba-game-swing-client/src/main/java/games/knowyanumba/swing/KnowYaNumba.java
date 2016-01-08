/**
 * 
 */
package games.knowyanumba.swing;

import javax.swing.SwingUtilities;

/**
 * @author Edwin
 *
 */
public class KnowYaNumba {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {new KnowYaNumbaJFrame();});
	}
}