/**
 * Lead Author(s):
 * @author Full name: Johnny Thai
 * @author Full name: Jacob Wiemann
 * @author Full name: Daniel Soto
 *
 * Other Contributors: none
 *
 * References:
 * Morelli, R., & Walde, R. (2016).
 * Java, Java, Java: Object-Oriented Problem Solving
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Version: 2024-10-16
 * 
 */

package view;

import javax.swing.JOptionPane;

public class MessageBox extends JOptionPane {

	/**
	 * Constructor to create the JOptionPane with a message
	 * 
	 * @param messageString
	 */
	public MessageBox(String messageString) {
		super(messageString);
		super.showMessageDialog(null, messageString, "", JOptionPane.WARNING_MESSAGE);
	}

}
