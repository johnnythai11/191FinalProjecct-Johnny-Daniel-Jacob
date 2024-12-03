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

public class MageChicken extends PlayerChicken {

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

	/**
	 * Constructor to create the MageChicken with its base status of Attack,
	 * Defense, Health, BoneBalance, and hungerBar
	 * 
	 * @param integer levelOfChicken
	 */
	public MageChicken(int levelOfChicken) {
		playerEquipment = new Equipment();
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
		baseDamage = (levelOfChicken - 1) * 2 + 10 + attackBoost;
		currentHealth = (levelOfChicken - 1) * 2 + 15;
		baseHealth = (levelOfChicken - 1) * 2 + 15;
		baseDefense = (levelOfChicken - 1) * 2 + 5 + defenseBoost;
		boneBalance = 1000;
		expBar = 100 + (levelOfChicken - 1) * 50;

	}

	/**
	 * gets the equipped item at the given index
	 * 
	 * @param index
	 * @return item
	 */
	@Override
	public Items getEquippedItem(int index) {
		return playerEquipment.getEquippedItem(index);
	}

	/**
	 * gets the exp bar
	 * 
	 * @return int
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
			System.out.println("Can't equip, not right class");
			return false;
		case 2:
			if (inputItem.getItemTier() != 0) {
				playerEquipment.equipEquipmentItem(inputItem, 2);
				return true;
			} else {
				System.out.println("Can't equip, not right class");
				return false;
			}
		case 3:

			playerEquipment.equipEquipmentItem(inputItem, 3);
			return true;
		default:
			return false;

		}
	}

	/**
	 * checks if an is equipped at the given index
	 * 
	 * @param index
	 * @return boolean
	 */
	@Override
	public boolean isItemEquipped(int index) {
		return playerEquipment.isItemEquipped(index);
	}

	/**
	 * updates the player's stats to new ones
	 */
	@Override
	public void resetPlayer() {
		updateDamage();
		updateDefense();
		updateHealth();
		updateExpNeeded();
	}

	/**
	 * removes the Item from the player's equipment
	 */
	@Override
	public void removeEquippedItem(int index) {
		playerEquipment.removeItemFromEquipment(index);

	}

	/**
	 * sets the bone balance to the given balance
	 * 
	 * @param int newBalance
	 */
	@Override
	public void setBalance(int newBalance) {
		boneBalance = newBalance;
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
	 * updates the chickens health
	 */
	@Override
	public void updateHealth() {
		baseHealth = (levelOfChicken - 1) * 2 + 15 + (heartCounter * 10);
		currentHealth = baseHealth;
	}

	/**
	 * Makes the chicken take damage
	 */
	@Override
	public int dealDamage() {
		updateDamage();
		return baseDamage;
	}

	/**
	 * Updates the chickens defense
	 */
	@Override
	public void updateDefense() {
		baseDefense = (levelOfChicken - 1) * 2 + 5 + playerEquipment.getItemStat(2) + defenseBoost;
	}

	/**
	 * Updates the chickens damage
	 */
	@Override
	public void updateDamage() {
		baseDamage = (levelOfChicken - 1) * 2 + 10 + playerEquipment.getItemStat(3) + attackBoost;
	}

	/**
	 * Updates the amount of exp needed to go to the next level
	 */
	@Override
	public void updateExpNeeded() {
		expBar = 100 + (levelOfChicken - 1) * 50;
	}

	/**
	 * gets the level of the player chicken
	 * 
	 * @return int levelOfChicken
	 */
	@Override
	public int getLevel() {

		return levelOfChicken;
	}

	/**
	 * gets the base health of the player chicken
	 * 
	 * @return int baseHealth
	 */
	@Override
	public int getBaseHealth() {
		return baseHealth;
	}

	/**
	 * gets the current health of the player chicken
	 * 
	 * @return int currentHealth
	 */
	@Override
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * gets the base damage of the player chicken
	 * 
	 * @return int baseDamage
	 */
	@Override
	public int getBaseDamage() {
		return baseDamage;
	}

	/**
	 * gets the base defense of the player chicken
	 * 
	 * @return int baseDefense
	 */
	@Override
	public int getBaseDefense() {
		return baseDefense;
	}

	/**
	 * gets the current bone balance of the player chicken
	 * 
	 * @return int boneBalance
	 */
	@Override
	public int getBoneBalance() {
		return boneBalance;
	}

	/**
	 * sets the base health of the player chicken
	 * 
	 * @param int health
	 */
	@Override
	public void setBaseHealth(int health) {
		this.baseHealth = health;

	}

	/**
	 * adds exp to the player chickens exp bar
	 * 
	 * @param int exp
	 */
	@Override
	public void addExp(int exp) {
		this.currentExp = exp;
	}

	/**
	 * @return int currentExp
	 */
	@Override
	public int getCurrentExpGained() {
		return this.currentExp;
	}

	/**
	 * gets the exp bars
	 * 
	 * @return int expBar
	 */
	@Override
	public int getExp() {

		return this.expBar;
	}

	/**
	 * increase the level of chicken by one
	 */
	@Override
	public void levelUp() {

		levelOfChicken++;
		resetPlayer();

	}

	/**
	 * gets the experienceCounter
	 * 
	 * @return int experienceCounter
	 */
	@Override
	public int getExperienceCounter() {
		return this.experienceCounter;
	}

	/**
	 * adds exp to the counter
	 * 
	 * @param int add
	 */
	@Override
	public void addExperienceCounter(int add) {
		this.experienceCounter = this.experienceCounter + add;
	}

	/**
	 * sets the exp counter
	 * 
	 * @param int add
	 */
	@Override
	public void setExperienceCounter(int add) {
		this.experienceCounter = add;
	}
}
