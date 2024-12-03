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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HowToPlayScreenView extends JFrame {

	final int SCREEN_WIDTH = 1000;
	final int SCREEN_HEIGHT = 800;
	private JPanel mainPanel;
	private JButton backToMainMenu;
	private JTextArea instructions = new JTextArea();
	private JScrollPane instructionScroll = new JScrollPane(instructions);

	/**
	 * Constructor for how to play screen
	 * 
	 */
	HowToPlayScreenView() {
		super("Chicken Fighter Instructions");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setLocation(900, 100);
		setPanels();
		add(mainPanel);
		setVisible(true);
	}

	/**
	 * Sets the button and JTextField
	 * 
	 */
	private void setPanels() {

		final int BUTTON_WIDTH = 150;
		final int BUTTON_HEIGHT = 50;
		final int instructions_WIDTH = 750;
		final int instructions_HEIGHT = 450;
		int verticalOffset = 100;
		int offsetWidthOfButton = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightOfButton = ((SCREEN_HEIGHT - BUTTON_HEIGHT)) - verticalOffset;
		backToMainMenu = new JButton("Back To Main Menu");
		backToMainMenu.setBounds(offsetWidthOfButton, offsetHeightOfButton, BUTTON_WIDTH, BUTTON_HEIGHT);
		backToMainMenu.addActionListener(new MainMenuListener());
		mainPanel.add(backToMainMenu);
		int verticalOffset2 = 325;
		int offsetWidthOfinstructions = ((SCREEN_WIDTH - instructions_WIDTH) / 2);
		int offsetHeightOfinstructions = ((SCREEN_HEIGHT - instructions_HEIGHT)) - verticalOffset2;
		instructionScroll.setSize(instructions_WIDTH, instructions_HEIGHT);
		instructionScroll.setBounds(offsetWidthOfinstructions, offsetHeightOfinstructions, instructions_WIDTH,
				instructions_HEIGHT);

		// reads the file
		File file = new File("HowToPlay.txt");

		try (Scanner reading = new Scanner(file))

		{

			System.out.println("File Found");

			while (reading.hasNext()) {
				String line = reading.nextLine();
				instructions.append(line + "\n");
				instructions.setSize(instructions_WIDTH, instructions_HEIGHT);
				instructions.setVisible(true);
				instructions.setEditable(false);
				instructionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				instructionScroll.setWheelScrollingEnabled(true);
				instructionScroll.setVisible(true);
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("not Found");
			e.printStackTrace();
		}

		mainPanel.add(instructionScroll);
		mainPanel.setVisible(true);
	}

	/**
	 * Button to go back to the main menu
	 * 
	 */
	private class MainMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new MainMenuView();
			dispose();
		}

	}
}