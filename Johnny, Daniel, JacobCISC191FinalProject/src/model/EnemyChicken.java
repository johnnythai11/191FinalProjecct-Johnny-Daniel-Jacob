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

import java.util.Random;

public class EnemyChicken {

	private int maxHealth;
	private int attack;
	private int levelOfChicken;
	private int defense;
	private int level;
	private int expGiven;
	private int boneToken;

	/**
	 * Constructs an enemyChicken
	 * 
	 * @param level Input the level of the generated chicken
	 */
	public EnemyChicken(int level) {
		levelOfChicken = level;
		maxHealth = (int) ((level * 0.5) + 10) / 4;
		attack = (int) ((level * 0.5) + 10) / 4;
		defense = (int) ((level * 0.5) + 10) / 4;
		expGiven = level;
		Random r = new Random();
		boneToken = r.nextInt(10) + 1;
	}

	/**
	 * Constructs a chicken with random level
	 */
	public EnemyChicken() {
		level = ((int) (Math.random() * 49)) + 1;
		levelOfChicken = level;
		maxHealth = (int) ((level * 0.5) + 10) / 4;
		attack = (int) ((level * 0.5) + 10) / 4;
		defense = (int) ((level * 0.5) + 10) / 4;
		expGiven = level;
		Random r = new Random();
		boneToken = r.nextInt(10) + 1;
	}

	/**
	 * setter for maxHealth of enemy chicken
	 * 
	 * @param int health
	 */
	public void setMaxHealth(int health) {
		maxHealth = health;
	}

	/**
	 * setter for level of enemy chicken
	 * 
	 * @param int level
	 */
	public void setLevelOfChicken(int level) {
		levelOfChicken = level;
	}

	/**
	 * gets the attack stat of the enemy
	 * 
	 * @return int attack stat
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * returns the amount of exp given when the enemy is killed
	 * 
	 * @return int of expGiven
	 */
	public int getExpGiven() {
		return expGiven;
	}

	/**
	 * gets the amount of bone tokens given when the enemy is killed
	 * 
	 * @return int of boneToken
	 */
	public int getBoneToken() {
		return boneToken;
	}

	/**
	 * gets the defense stat of the enemy
	 * 
	 * @return int defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * gets the level of enemy chicken
	 * 
	 * @return int levelOfChicken
	 */
	public int getLevelOfChicken() {
		return levelOfChicken;
	}

	/**
	 * returns the max health of the enemy
	 * 
	 * @return int max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Return's a damage number dependent on the level
	 * 
	 * @return integer
	 */
	public int doDamage() {
		return (int) (levelOfChicken * 0.5) + 10;
	}

	/**
	 * Calculate incoming damage for the chicken
	 * 
	 * @param incomingDamage Damage incoming to the chicken
	 */
	public void takeDamage(int incomingDamage) {
		// Checks if damage is greater than total defense, and defense is > 0
		if ((incomingDamage > defense) && (defense > 0)) {
			defense = 0;
		}
		// Else if defense is not broken it decreases defense
		else if (defense != 0) {
			defense = defense - incomingDamage;
		}
		// finally if there is no defense, decrease current health
		else {
			maxHealth = maxHealth - incomingDamage;
			if (maxHealth < 0) // If the health is less than 0 set to 0
			{
				maxHealth = 0;
			}
		}
	}

	/**
	 * Method that randomly create an enemyChicken
	 * 
	 * @Return EnemyChicken
	 */
	public static EnemyChicken generateChicken() {
		return new EnemyChicken();
	}
}
