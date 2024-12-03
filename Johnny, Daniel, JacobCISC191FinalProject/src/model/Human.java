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

public class Human implements java.io.Serializable {

	private int level;
	private int statBoostAttack;
	private int statBoostDefense;

	/**
	 * Constructor for a Human with all the boost status of Attack, Defense, chicken
	 * level 1-10 will be able to get level 1 human boost Attack by 0, Defense by 0
	 * chicken level 11-20 will be able to get level 2 human boost Attack by 2,
	 * Defense by 0 chicken level 21-30 will be able to get level 3 human boost
	 * Attack by 4, Defense by 0 chicken level 31-40 will be able to get level 4
	 * human boost Attack by 6, Defense by 6 chicken level 41-50 will be able to get
	 * level 5 human boost Attack by 8, Defense by 8
	 * 
	 * @param integer level
	 */
	public Human(int level) {
		if (level < 4 && level > 0) {
			this.level = level;
			statBoostAttack = level * 2 - 2;
		} else if (level > 3 && level < 6) {
			statBoostDefense = level * 2 - 2;
			statBoostAttack = level * 2 - 2;
		} else {
			this.level = 1;
			statBoostDefense = level * 2 - 2;
			statBoostAttack = level * 2 - 2;
		}
	}

	/**
	 * gets the level of the human for the PlayerChicken
	 * 
	 * @return Level
	 */
	public int getHumanLevel() {
		return level;
	}

	/**
	 * Gets the attack boost status of the human
	 * 
	 * @return integer statBoostAttack
	 */
	public int getAttackBoost() {
		return statBoostAttack;
	}

	/**
	 * Gets the defense boost status of the human
	 * 
	 * @return integer statBoostDefense
	 */
	public int getDefenseBoost() {
		return statBoostDefense;
	}

}
