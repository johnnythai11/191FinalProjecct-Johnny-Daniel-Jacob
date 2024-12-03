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

public abstract class PlayerChicken implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public PlayerChicken player;

	public PlayerChicken() {

	}

	public abstract Items getEquippedItem(int index);

	public abstract void removeEquippedItem(int index);

	public abstract boolean isItemEquipped(int index);

	public static int heartCounter;

	public Inventory playerInventory = new Inventory();

	public abstract void addExp(int exp);

	public abstract void levelUp();

	public abstract void setBalance(int balance);

	public abstract void updateHealth();

	public abstract void resetPlayer();

	public abstract void takeDamage(int enemyDamage);

	public abstract int dealDamage();

	public abstract void updateDamage();

	public abstract void updateDefense();

	public abstract void updateExpNeeded();

	public abstract int expBar();

	public abstract int getLevel();

	public abstract int getBaseDamage();

	public abstract int getBaseHealth();

	public abstract int getCurrentHealth();

	public abstract int getBaseDefense();

	public abstract int getBoneBalance();

	public abstract void setBaseHealth(int health);

	public abstract boolean equipEquipmentItem(int index);

	public abstract int getExp();

	public int experienceCounter;

	public abstract int getCurrentExpGained();

	public abstract int getExperienceCounter();

	public abstract void addExperienceCounter(int add);

	public abstract void setExperienceCounter(int add);
}
