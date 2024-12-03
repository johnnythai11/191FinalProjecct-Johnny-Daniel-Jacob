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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Combat;
import model.EnemyChicken;
import model.MageChicken;
import model.PlayerChicken;
import model.TankChicken;
import model.MeleeChicken;
import model.SaveLoad;

public class CombatView extends JFrame {

	final int SCREEN_WIDTH = 1500;
	final int SCREEN_HEIGHT = 1000;

	private JPanel mainPanel;

	private StoreView myStore;
	public InventoryView playerInventory;

	private JButton inventory;
	private JButton attack;
	private JButton specialAttack;
	private JButton retreat;
	private JButton store;
	private JButton save;

	private JPanel characterPanel;
	private JPanel enemyPanel;

	private JTextArea characterInfo;
	private JTextArea enemyInfo;

	public static PlayerChicken player;

	private static EnemyChicken enemy;

	private Font info;

	private JTextArea characterPanelText;
	private int classType;

	private int ItemTierOfHead;
	private int ItemTierOfBody;
	private int ItemTierOfFeet;

	/**
	 * Constructor for the combat Screen to load the player
	 * 
	 * @param player
	 */
	public CombatView(PlayerChicken player) {
		super("Chicken Fighter");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.player = player;
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		enemy = new EnemyChicken();

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		setButtons();
		setPanels();
		add(mainPanel);
		setVisible(true);

	}

	/**
	 * Constructor to load a combat screen with selected character
	 * 
	 * @param int selection
	 */
	public CombatView(int selection) {
		super("Chicken Fighter");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.classType = selection;
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);

		if (selection == 1) {
			player = new MageChicken(1);
		} else if (selection == 2) {
			player = new TankChicken(1);
		} else if (selection == 3) {
			player = new MeleeChicken(1);
		}

		enemy = new EnemyChicken();

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		setButtons();
		setPanels();
		add(mainPanel);
		setVisible(true);
	}

	/**
	 * Sets all the buttons needed for the combat screen
	 */
	private void setButtons() {
		final int BUTTON_WIDTH = 150;
		final int BUTTON_HEIGHT = 50;

		int verticalOffset = 40;

		int offsetLengthInventory = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightInventory = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) - verticalOffset;

		int offsetLengthAttack = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightAttack = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) + verticalOffset;

		int offsetLengthSpecialAttack = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightSpecialAttack = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) + 3 * verticalOffset;

		int offsetLengthRetreat = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightRetreat = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) + 5 * verticalOffset;

		int offsetLengthStore = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightStore = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) + 7 * verticalOffset;

		int offsetLengthSave = ((SCREEN_WIDTH - BUTTON_WIDTH) / 2);
		int offsetHeightSave = ((SCREEN_HEIGHT - BUTTON_HEIGHT) / 5) + 9 * verticalOffset;

		inventory = new JButton("Inventory");
		attack = new JButton("Attack");
		specialAttack = new JButton("Special Attack");
		retreat = new JButton("Retreat");
		store = new JButton("Store");
		save = new JButton("Save");

		retreat.addActionListener(new RetreatListener());
		attack.addActionListener(new AttackListener());
		specialAttack.addActionListener(new AttackListener());
		store.addActionListener(new StoreListener());
		inventory.addActionListener(new InventoryListener());
		save.addActionListener(new SaveListener());

		inventory.setBounds(offsetLengthInventory, offsetHeightInventory, BUTTON_WIDTH, BUTTON_HEIGHT);
		attack.setBounds(offsetLengthAttack, offsetHeightAttack, BUTTON_WIDTH, BUTTON_HEIGHT);
		specialAttack.setBounds(offsetLengthSpecialAttack, offsetHeightSpecialAttack, BUTTON_WIDTH, BUTTON_HEIGHT);
		retreat.setBounds(offsetLengthRetreat, offsetHeightRetreat, BUTTON_WIDTH, BUTTON_HEIGHT);
		store.setBounds(offsetLengthStore, offsetHeightStore, BUTTON_WIDTH, BUTTON_HEIGHT);
		save.setBounds(offsetLengthSave, offsetHeightSave, BUTTON_WIDTH, BUTTON_HEIGHT);

		mainPanel.add(inventory);
		mainPanel.add(attack);
		mainPanel.add(specialAttack);
		mainPanel.add(retreat);
		mainPanel.add(store);
		mainPanel.add(save);
	}

	/**
	 * Displays all the necessary information of the player and the enemy
	 * 
	 */
	private void setPanels() {

		characterPanel = new JPanel();
		characterPanel.setSize(660, 400);
		characterPanel.setLocation(0, 10);

		if (player instanceof MageChicken) {
			info = new Font("Times New Roman", Font.BOLD, 45);
			characterPanel.setBackground(Color.blue);
			characterPanelText = new JTextArea();
			characterPanelText.append("Mage Chicken");
			characterPanelText.setEditable(false);
			characterPanelText.setFont(info);
			characterPanel.add(characterPanelText);
		} else if (player instanceof TankChicken) {
			info = new Font("Times New Roman", Font.BOLD, 45);
			characterPanel.setBackground(Color.green);
			characterPanelText = new JTextArea();
			characterPanelText.append("Tank Chicken");
			characterPanelText.setEditable(false);
			characterPanelText.setFont(info);
			characterPanel.add(characterPanelText);
		} else if (player instanceof MeleeChicken) {
			info = new Font("Times New Roman", Font.BOLD, 45);
			characterPanel.setBackground(Color.red);
			characterPanelText = new JTextArea();
			characterPanelText.append("Melee Chicken");
			characterPanelText.setEditable(false);
			characterPanelText.setFont(info);
			characterPanel.add(characterPanelText);
		}

		enemyPanel = new JPanel();
		enemyPanel.setSize(660, 400);
		enemyPanel.setLocation(840, 10);
		enemyPanel.setBackground(Color.cyan);

		characterInfo = new JTextArea(1, 5);
		info = new Font("Times New Roman", Font.BOLD, 45);
		characterInfo.setFont(info);
		characterInfo.append("Health: " + player.getCurrentHealth() + "\n");
		characterInfo.append("Defense: " + player.getBaseDefense() + "\n");
		characterInfo.append("Attack: " + player.getBaseDamage() + "\n");
		characterInfo.append("Balance: " + player.getBoneBalance() + "\n");
		characterInfo.append("Level: " + player.getLevel() + "\n");
		characterInfo.append("Exp gained: " + player.getExperienceCounter() + "\n");
		characterInfo.append("Exp needed to levelup: " + player.expBar());
		characterInfo.setEditable(false);
		characterInfo.setSize(660, 400);
		characterInfo.setLocation(0, 440);

		enemyInfo = new JTextArea(1, 5);

		enemyInfo.setFont(info);
		enemyInfo.append("Level: " + enemy.getLevelOfChicken() + "\n");
		enemyInfo.append("Health: " + enemy.getMaxHealth() + "\n");
		enemyInfo.append("Defense: " + enemy.getDefense() + "\n");
		enemyInfo.append("Attack: " + enemy.getAttack() + "\n");
		enemyInfo.append("Exp Given when killed: " + enemy.getExpGiven());
		enemyInfo.setEditable(false);
		enemyInfo.setSize(660, 400);
		enemyInfo.setLocation(840, 440);

		mainPanel.add(characterPanel);
		mainPanel.add(enemyPanel);
		mainPanel.add(characterInfo);
		mainPanel.add(enemyInfo);

	}

	/**
	 * Updates the info for the player and the enemy
	 * 
	 */
	public void updateInfo() {
		enemyInfo.setText("");
		enemyInfo.setText("Level: " + enemy.getLevelOfChicken() + "\n" + "Health: " + enemy.getMaxHealth() + "\n"
				+ "Defense: " + enemy.getDefense() + "\n" + "Attack: " + enemy.getAttack() + "\n"
				+ "Exp Given when killed: " + enemy.getExpGiven());

		characterInfo.setText("");
		characterInfo.setText("Health: " + player.getCurrentHealth() + "\n" + "Defense: " + player.getBaseDefense()
				+ "\n" + "Attack: " + player.getBaseDamage() + "\n" + "Balance: " + player.getBoneBalance() + "\n"
				+ "Level: " + player.getLevel() + "\n" + "Exp gained: " + player.getExperienceCounter() + "\n"
				+ "Exp needed to levelup: " + player.expBar());
	}

	/**
	 * Closes the store and the player's inventory
	 * 
	 */
	public void closeGui() {
		if (myStore != null) {
			myStore.dispose();
		}

		if (playerInventory != null) {
			playerInventory.dispose();
		}
	}

	/**
	 * Player gets new enemy when they retreat
	 * 
	 */
	private class RetreatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeGui();
			enemy = null;
			enemy = new EnemyChicken();

			player.resetPlayer();
			updateInfo();
		}

	}

	/**
	 * Player attacks when they attack or special attack
	 * 
	 */
	private class AttackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == attack) {
				closeGui();
				int result = Combat.attackCombatRound(player, enemy);
				System.out.println(result);
				if (result == 1) { // WHAT HAPPENS WHEN YOU DIE
					new MessageBox("You Died!");
					new MainMenuView();
					dispose();
				} else if (result == 2) { // WHAT YOU WANT TO HAPPEN WHEN THE ENEMY IS KILLED
					player.addExperienceCounter(enemy.getExpGiven());
					enemy = null;
					enemy = new EnemyChicken();
					System.out.println("when enemy dies:" + player.getExperienceCounter());
					player.setBalance(player.getBoneBalance() + enemy.getBoneToken());
					player.getExperienceCounter();
					if (player.getExp() <= player.getExperienceCounter()) {
						player.levelUp(); // Checks if you can level up
						System.out.println("levelup");
					}
				}
				updateInfo();
			}

			else if (e.getSource() == specialAttack) {
				// Checks if the player has equipped the special item
				// this does not work for some reason
				if (player.getEquippedItem(1) != null) {
					ItemTierOfHead = player.getEquippedItem(1).getItemTier();
					System.out.println("this is when helemet is equipped." + ItemTierOfHead);
				} else {
					ItemTierOfHead = -1;
				}
				// Checks if the player has equipped the special item
				if (player.getEquippedItem(2) != null) {
					ItemTierOfBody = player.getEquippedItem(2).getItemTier();
				} else {
					ItemTierOfBody = -1;
				}
				// Checks if the player has equipped the special item
				if (player.getEquippedItem(3) != null) {
					ItemTierOfFeet = player.getEquippedItem(3).getItemTier();

				} else {
					ItemTierOfFeet = -1;
				}
				if (ItemTierOfBody == 0 || ItemTierOfFeet == 0 || ItemTierOfHead == 2 || ItemTierOfHead == 4
						|| ItemTierOfHead == 0 || ItemTierOfHead == 3 || ItemTierOfHead == 1) {
					closeGui();
					int result = Combat.attackCombatRound(player, enemy);
					if (result == 1) { // WHAT HAPPENS WHEN YOU DIE
						new MessageBox("You Died!");
						new MainMenuView();
						dispose();
					} else if (result == 2) { // WHAT YOU WANT TO HAPPEN WHEN THE ENEMY IS KILLED
						player.addExperienceCounter(enemy.getExpGiven());
						enemy = null;
						enemy = new EnemyChicken();
						System.out.println("when enemy dies:" + player.getExperienceCounter());
						player.setBalance(player.getBoneBalance() + enemy.getBoneToken());
						player.getExperienceCounter();
						if (player.getExp() <= player.getExperienceCounter()) {
							player.levelUp(); // Checks if you can level up
							System.out.println("levelup");
						}
					}

				}

				else {
					new MessageBox("Class unique item not equipped, can't special attack.");
				}

				updateInfo();

			}

		}
	}

	/**
	 * Button to open the game's store for the player
	 * 
	 */
	private class StoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeGui();
			myStore = new StoreView();

		}

	}

	/**
	 * Button to open the players inventory
	 * 
	 */
	private class InventoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			closeGui();
			playerInventory = new InventoryView();
		}

	}

	/**
	 * Button to save all the info of the player's stat
	 * 
	 */
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SaveLoad saveLoad = new SaveLoad();
			saveLoad.binarySave(player);

		}

	}

}
