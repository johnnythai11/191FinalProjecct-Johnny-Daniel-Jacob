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

import javax.swing.JButton;
import javax.swing.JFrame;
import model.Store;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class StoreView extends JFrame {
	private JLabel[] itemSlots = new JLabel[20];
	private JButton[] itemButtons = new JButton[20];
	private JButton closeShopButton = new JButton("Exit");
	private Store myStore = new Store();
	private ArrayList<Integer> alreadyBoughtList;
	private JLabel balance;
	// Margins around the top and left side of screen, margins between each panel X,
	// Y
	private final int margins = 15, betweenMarginsY = 30, betweenmarginsX = 10;

	// Changes lenght, width of the panels and the relating variables that depend on
	// it
	private final int panelLengthWidth = 100;

	// amount of Jpanels per row
	private final int amountPerRow = 5;

	final int SCREEN_WIDTH = 650, SCREEN_HEIGHT = 800;

	/**
	 * Constructor to create the store for the game
	 * 
	 */
	public StoreView() {
		super("The Store");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		super.setLayout(null);

		alreadyBoughtList = new ArrayList<Integer>();

		setStockSlots(itemSlots);
		setStockButtons(itemButtons);
		setCloseButton();

		setVisible(true);
	}

	/**
	 * Sets the stock labels
	 * 
	 * @param theLabels
	 */
	private void setStockSlots(JLabel[] theLabels) {
		// offsets to be used later, DO NOT CHANGE
		int yOffset = 0, xOffset = 0;

		// Populates the array with JLabels, Sets them blue, and aligns them
		for (int i = 0; i < theLabels.length; i++) {
			// For each "slot" in the array, create JPanel
			theLabels[i] = new JLabel(" " + String.valueOf(myStore.getItem(i).getItemName()));
			theLabels[i].setBackground(Color.white);
			theLabels[i].setOpaque(true);
			// amountPerRow changes amountPerRow
			if (xOffset > (panelLengthWidth * amountPerRow)) {
				xOffset = 0;
				yOffset = yOffset + panelLengthWidth + margins + betweenMarginsY;
			}
			theLabels[i].setBounds(xOffset + margins, yOffset + margins, panelLengthWidth, panelLengthWidth);
			add(theLabels[i]);
			xOffset = xOffset + panelLengthWidth + margins + betweenmarginsX;
		}
	}

	/**
	 * Sets the Stock Buttons
	 * 
	 * @param theButtons
	 */
	private void setStockButtons(JButton[] theButtons) {
		// offsets to be used later, DO NOT CHANGE
		int yOffset = 100, xOffset = 0;
		for (int i = 0; i < theButtons.length; i++) {
			theButtons[i] = new JButton("Price : " + String.valueOf(myStore.getItem(i).getPrice()));
			if (xOffset > (panelLengthWidth * amountPerRow)) {
				xOffset = 0;
				yOffset = yOffset + panelLengthWidth + margins + betweenMarginsY;
			}

			theButtons[i].setBounds(xOffset + margins, yOffset + margins + 10, panelLengthWidth, 25);
			theButtons[i].addActionListener(new BuyListener());

			add(theButtons[i]);
			xOffset = xOffset + panelLengthWidth + margins + betweenmarginsX;
		}
	}

	/**
	 * Sets the close button for the shop
	 * 
	 */
	private void setCloseButton() {
		balance = new JLabel("Balance : " + CombatView.player.getBoneBalance());
		balance.setBounds(200, 650, 100, 50);

		closeShopButton.setBounds(500, 650, 100, 50);
		closeShopButton.addActionListener(new CloseListener());
		add(closeShopButton);
		add(balance);
	}

	/**
	 * Changes the text from buy to Sold! once the player purchase the item from the
	 * store
	 * 
	 * @param itemButtonActivated
	 */
	private void updateInfo(int itemButtonActivated) {
		balance.setText("Balance : " + CombatView.player.getBoneBalance());
		itemButtons[itemButtonActivated].setText("Sold!");

	}

	private class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}

	/**
	 * Goes through the item button list, checks which one made the event, then
	 * checks if the player has enough balance to buy the item, also prevents buying
	 * the same item multiple times
	 */
	private class BuyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < itemButtons.length; i++) {
				if (itemButtons[i] == e.getSource()) { // Sets index to the button that got clicked
					if (CombatView.player.getBoneBalance() > myStore.getItem(i).getPrice()) { // makes sure player has
																								// enough balance
						if (!alreadyBoughtList.contains(i)) { // makes sure the player hasnt already bought the item
							if (CombatView.player.playerInventory.roomExist()) // makes sure the inventory isnt full
							{
								CombatView.player
										.setBalance(CombatView.player.getBoneBalance() - myStore.getItem(i).getPrice());
								updateInfo(i);
								alreadyBoughtList.add(i);
								CombatView.player.playerInventory.addItemToInventory(myStore.getItem(i));
							} else {
								System.out.println("OUT OF INVENTORY ROOM");
							}
						}
					}
				}
			}
		}

	}
}
