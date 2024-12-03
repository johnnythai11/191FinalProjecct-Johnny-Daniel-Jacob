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

public class Equipment implements java.io.Serializable {

	public Items[] equippedItemArray = new Items[4];

	/**
	 * Constructor for the equipment class. And it sets the indices of the equipment
	 * array
	 */
	public Equipment() {

	}

	/**
	 * Equipment for the player with 4 item slots 0 = Heart 1 = Head (Defence) 2 =
	 * Body (Defence) 3 = Feet (Weapon)
	 */
	public Equipment(Items item1, Items item2, Items item3, Items item4) {
		this.equippedItemArray[0] = item1;
		this.equippedItemArray[1] = item2;
		this.equippedItemArray[2] = item3;
		this.equippedItemArray[3] = item4;
	}

	/**
	 * Gets the Equipment from the item array
	 * 
	 * @return the equipment array
	 */
	public Items[] getEquipment() {
		return this.equippedItemArray;
	}

	/**
	 * Adds the items to the equipment to its corresponding index
	 * 
	 * @param item, index
	 */
	public void equipEquipmentItem(Items item, int index) {

		if (item.getItemType() == 0) {

			CombatView.player.heartCounter += 1;
			CombatView.player.updateHealth();
		} else {

			this.equippedItemArray[index] = item;
		}
	}

	/**
	 * Checks if an item is equipped at the given index
	 * 
	 * @param index of array
	 * @return true if there is an item at the index given, false if there is not at
	 *         item at the index given.
	 */
	public boolean isItemEquipped(int index) {
		try {
			if (equippedItemArray[index] != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("Equipment : Item Not Found (Null, Intended)");
			return false;
		}
	}

	/**
	 * Returns the items stat at the given index if no item it returns 0
	 * 
	 * @param index
	 * @return item, 0
	 */
	public int getItemStat(int index) {
		if (equippedItemArray[index] != null) {
			switch (index) {
			case 1:
				return equippedItemArray[index].getDefenseStat();
			case 2:
				return equippedItemArray[index].getDefenseStat();
			case 3:
				return equippedItemArray[index].getAttackStat();
			default:
				return 0;
			}
		}
		return 0;
	}

	/**
	 * returns the equipped item at the given index
	 * 
	 * @param index
	 * @return item
	 */
	public Items getEquippedItem(int index) {
		if (equippedItemArray[index] != null) {
			return equippedItemArray[index];
		} else {
			System.out.println("I have no Items equipped.");
			return null;
		}
	}

	/**
	 * Removes an item from the array at the given index, and returns the item that
	 * was removed
	 * 
	 * @param index
	 * @return item
	 */

	public void removeItemFromEquipment(int index) {
		this.equippedItemArray[index] = null;
	}

}
