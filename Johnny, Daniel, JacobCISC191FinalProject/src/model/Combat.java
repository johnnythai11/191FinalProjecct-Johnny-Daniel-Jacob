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

public class Combat {

	PlayerChicken player;
	EnemyChicken enemy;

	/**
	 * sets the attack damage of the type of the player dependent on the item worn
	 * 
	 * @param PlayerChicken, Items
	 * @return attackDamage of the player
	 */

	public static int basicAttack(PlayerChicken player, Items playeritem) {
		int attackDamage = playeritem.getAttackStat() + player.getBaseDamage();
		return attackDamage;
	}

	/**
	 * Calculates the damage taken for the player base on enemy's attack damage
	 * 
	 * @param PlayerChicken, EnemyChicken, item of the player
	 * @return playerHealth of the player
	 */
	public static int takeDamageForPlayer(PlayerChicken player, EnemyChicken enemy) {
		int health = player.getBaseHealth();
		int defense = player.getBaseDefense();
		int enemyDamage = enemy.doDamage();
		int currentHealth = health;

		if ((enemyDamage > defense) && (defense > 0)) {
			defense = 0;
		}
		// Else if defense is not broken it decreases defense
		else if (defense != 0) {
			defense = defense - enemyDamage;
		}
		// finally if there is no defense, decrease current health
		else if (defense == 0 && currentHealth != 0 && currentHealth > 0) {
			currentHealth = health - enemyDamage;
		}
		return currentHealth;
	}

	/**
	 * Calculates the damage taken for the enemy base on player's attack damage
	 * 
	 * @param PlayerChicken, EnemyChicken
	 * @return enemyHealth of the enemy
	 */
	public static int takeDamageForEnemy(PlayerChicken player, EnemyChicken enemy) {
		int health = enemy.getMaxHealth();
		int defense = enemy.getDefense();
		int playerDamage = player.getBaseDamage();
		int currentHealth = health;

		if ((playerDamage > defense) && (defense > 0)) {
			defense = 0;
		}
		// Else if defense is not broken it decreases defense
		else if (defense != 0) {
			defense = defense - playerDamage;
		}
		// finally if there is no defense, decrease current health
		else if (defense == 0 && currentHealth != 0 && currentHealth > 0) {
			currentHealth = health - playerDamage;
		}
		return currentHealth;
	}

	/**
	 * Completes a full round of combat
	 * 
	 * @param player object, enemy object
	 * @return 1 if player died, 2 if enemy died, 0 if no one died
	 */
	public static int attackCombatRound(PlayerChicken player, EnemyChicken enemy) {
		int playerDamage = player.dealDamage();
		int enemyDamage = enemy.doDamage();
		enemy.takeDamage(playerDamage);
		player.takeDamage(enemyDamage);
		if (player.getCurrentHealth() <= 0) {
			return 1;
		} else if (enemy.getMaxHealth() <= 0) {
			return 2;
		} else {
			return 0;
		}
	}
}
