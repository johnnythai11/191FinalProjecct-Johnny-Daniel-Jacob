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

package model;

import view.CombatView;


public class TankChicken extends PlayerChicken

{

	private int levelOfChicken;
	private int currentHealth;
	private int baseHealth;
	private int baseDefense;
	private int baseDamage;
	private int boneBalance;
	private int expBar;
	private int currentExp;
	private Equipment playerEquipment;
	private Human humanBoost;
	private int attackBoost;
	private int defenseBoost;
	private int experienceCounter;
	public PlayerChicken player;

	/**
	 * Constructor to create the TankChicken with its base status of Attack,
	 * Defense, Health, BoneBalance, and hungerBar
	 * 
	 * @param integer levelOfChicken
	 */
	public TankChicken(int levelOfChicken)

	{

		playerEquipment = new Equipment();
		this.levelOfChicken = levelOfChicken;

		this.levelOfChicken = levelOfChicken;
		if (1 <= this.levelOfChicken && this.levelOfChicken <= 10) {
			humanBoost = new Human(1);
			attackBoost = humanBoost.getAttackBoost();
			defenseBoost = humanBoost.getDefenseBoost();

		} else if (11 <= this.levelOfChicken && this.levelOfChicken <= 20) {
			humanBoost = new Human(2);
			attackBoost = humanBoost.getAttackBoost();
			defenseBoost = humanBoost.getDefenseBoost();

		} else if (21 <= this.levelOfChicken && this.levelOfChicken <= 30) {
			humanBoost = new Human(3);
			attackBoost = humanBoost.getAttackBoost();
			defenseBoost = humanBoost.getDefenseBoost();

		} else if (31 <= this.levelOfChicken && this.levelOfChicken <= 40) {
			humanBoost = new Human(4);
			attackBoost = humanBoost.getAttackBoost();
			defenseBoost = humanBoost.getDefenseBoost();

		} else if (41 <= this.levelOfChicken) {
			humanBoost = new Human(5);
			attackBoost = humanBoost.getAttackBoost();
			defenseBoost = humanBoost.getDefenseBoost();

		}
		baseDamage = (levelOfChicken - 1) * 2 + 5 + attackBoost;
		currentHealth = (levelOfChicken - 1) * 2 + 35;
		baseHealth = (levelOfChicken - 1) * 2 + 35;
		baseDefense = (levelOfChicken - 1) * 2 + 15 + defenseBoost;
		boneBalance = 1000;
		expBar = 100 + (levelOfChicken - 1) * 50;

	}

	/**
	 * removes item from one of the four slots of equipment
	 * 
	 * @param int index
	 */
	@Override
	public void removeEquippedItem(int index) {
		playerEquipment.removeItemFromEquipment(index);
	}

	/**
	 * checks if the player has the item equipped
	 * 
	 * @param index
	 */
	@Override
	public boolean isItemEquipped(int index) {
		return playerEquipment.isItemEquipped(index);
	}

	/**
	 * Gets the expBar of the player
	 * 
	 * @return expBar
	 */
	@Override
	public int expBar() {

		return expBar;
	}

	/**
	 * Equips the Items from the inventory
	 * 
	 * @param int indexX, int indexY
	 */
	@Override
	public boolean equipEquipmentItem(int index) {
		Items inputItem = CombatView.player.playerInventory.getItem(index);
		switch (inputItem.getItemType()) {

		case 0:
			playerEquipment.equipEquipmentItem(inputItem, 0);
			return true;
		case 1:
			playerEquipment.equipEquipmentItem(inputItem, 1);
			return true;
		case 2:
			if (inputItem.getItemTier() != 0) {
				playerEquipment.equipEquipmentItem(inputItem, 2);
				return true;
			} else {
				System.out.println("Can't equip, not right class");
				return false;
			}

		case 3:
			if (inputItem.getItemTier() != 0) {
				playerEquipment.equipEquipmentItem(inputItem, 3);
				return true;
			} else {
				System.out.println("Can't equip, not right class");
				return false;
			}
		default:
			return false;

		}
	}

	/**
	 * gets the item from the player equipment
	 * 
	 * @param int index
	 * @return Items
	 */
	@Override
	public Items getEquippedItem(int index) {
		return playerEquipment.getEquippedItem(index);
	}

	/**
	 * Sets the boneBalance of the player
	 * 
	 * @param int newBalance
	 */
	@Override
	public void setBalance(int newBalance) {
		boneBalance = newBalance;
	}

	/**
	 * Updates all the player's stats
	 * 
	 */
	@Override
	public void resetPlayer() {
		updateDamage();
		updateDefense();
		updateHealth();
		updateExpNeeded();
	}

	/**
	 * Gets the current damage of the player based on level and items
	 * 
	 * @return int baseDamage
	 */
	@Override
	public int dealDamage() {
		updateDamage();
		return baseDamage;
	}

	/**
	 * Updates the health of the player
	 */
	@Override
	public void updateHealth() {
		baseHealth = (levelOfChicken - 1) * 2 + 35 + (heartCounter * 10);
		currentHealth = baseHealth;
	}

	/**
	 * Updates the defense of the player
	 * 
	 */
	@Override
	public void updateDefense() {
		baseDefense = (levelOfChicken - 1) * 2 + 15 + playerEquipment.getItemStat(2) + playerEquipment.getItemStat(1)
				+ defenseBoost;
	}

	/**
	 * Updates the damage of the player
	 * 
	 */
	@Override
	public void updateDamage() {
		baseDamage = (levelOfChicken - 1) * 2 + 5 + playerEquipment.getItemStat(3) + attackBoost;
	}

	/**
	 * Updates the amount of exp needed to go to the next level
	 */
	@Override
	public void updateExpNeeded() {
		expBar = 100 + (levelOfChicken - 1) * 50;
	}

	/**
	 * Makes the player chicken take damage when the enemy attacks it
	 * 
	 * @param int enemyDamage
	 */
	@Override
	public void takeDamage(int enemyDamage) {

		if ((enemyDamage > baseDefense) && baseDefense > 0) {
			baseDefense = 0;
		}
		// Else if defense is not broken it decreases defense
		else if (baseDefense != 0) {
			baseDefense = baseDefense - enemyDamage;
		}
		// finally if there is no defense, decrease current health
		else if (baseDefense == 0 && currentHealth != 0 && currentHealth > 0) {
			currentHealth = currentHealth - enemyDamage;
		}

		if (currentHealth <= 0) {
			currentHealth = 0;
		}
	}

	/**
	 * Gets the level of the player
	 * 
	 * @return levelOfChicken
	 */
	@Override
	public int getLevel() {

		return levelOfChicken;
	}

	/**
	 * Gets the base Health of the player
	 * 
	 * @return baseHealth
	 */
	@Override
	public int getBaseHealth() {
		return baseHealth;
	}

	/**
	 * Gets the current Health of the player
	 * 
	 * @return currentHealth
	 */
	@Override
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * Gets the base damage of the player
	 * 
	 * @return baseDamage
	 */
	@Override
	public int getBaseDamage() {
		return baseDamage;
	}

	/**
	 * Gets the base defense of the player
	 * 
	 * @return int baseDefense
	 */
	@Override
	public int getBaseDefense() {
		return baseDefense;
	}

	/**
	 * Gets the bone balance of the player
	 * 
	 * @return int boneBalance
	 */
	@Override
	public int getBoneBalance() {
		return boneBalance;
	}

	/**
	 * Sets the base Health of the player
	 * 
	 * @param int health
	 */
	@Override
	public void setBaseHealth(int health) {
		this.baseHealth = health;

	}

	/**
	 * Keeps track of the exp gained for the player
	 * 
	 * @param int exp
	 */
	@Override
	public void addExp(int exp) {
		this.currentExp = exp;
	}

	/**
	 * Gets the current exp of the player
	 * 
	 * @return int currentExp
	 */
	@Override
	public int getCurrentExpGained() {
		return this.currentExp;
	}

	/**
	 * Gets the exp of the player
	 * 
	 * @return int expBar
	 */
	@Override
	public int getExp() {

		return this.expBar;
	}

	/**
	 * levels up the player
	 *
	 */
	@Override
	public void levelUp() {

		levelOfChicken++;
		resetPlayer();

	}

	/**
	 * Gets the experienceCounter of the player
	 * 
	 * @return int experienceCounter
	 */
	@Override
	public int getExperienceCounter() {
		return this.experienceCounter;
	}

	/**
	 * Keeps track of the exp of the player
	 * 
	 * @param int add
	 */
	@Override
	public void addExperienceCounter(int add) {
		this.experienceCounter = this.experienceCounter + add;
	}

	/**
	 * Sets the experienceCounter of the player
	 * 
	 * @param int add
	 */
	@Override
	public void setExperienceCounter(int add) {
		this.experienceCounter = add;
	}

}
