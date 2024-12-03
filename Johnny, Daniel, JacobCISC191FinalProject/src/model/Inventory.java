/**
 * Lead Author(s):
 * 
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

import java.util.ArrayList;

public class Inventory extends Items implements java.io.Serializable {
	private ArrayList<Items> inventory = new ArrayList<Items>();

	Inventory() {
	}

	/**
	 * Constructor to add the items to the inventory
	 * 
	 * @param Items item
	 */
	Inventory(Items item) {
		inventory.add(item);
		getInventory();
	}

	/**
	 * Gets all Items in the inventory
	 * 
	 * @return ArrayList<Items>
	 */
	public ArrayList<Items> getInventory() {
		return inventory;
	}

	/**
	 * Adds items to the ArrayList<Items>
	 * 
	 * @param Items item
	 */
	public void addItemToInventory(Items item) {
		if (inventory.size() <= 16) {
			inventory.add(item);
		}
	}

	/**
	 * Checks if an item at an index exists in the inventory. Returns true if it is
	 * false if it doesnt.
	 * 
	 * @param index
	 * @return boolean
	 */
	public boolean itemExist(int index) {
		try {

			if (inventory.get(index) != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if there is room in the inventory for more items. True if more room.
	 * False if no room
	 * 
	 * @return boolean
	 */
	public boolean roomExist() {
		if (this.inventory.size() <= 16) {
			return true;
		}
		return false;
	}

	/**
	 * Removes the items from the inventory
	 * 
	 * @param int row, int column
	 */
	public void removeItemFromInventory(Items item) {
		inventory.remove(item);
	}

	/**
	 * Gets the item from the inventory
	 * 
	 * @param int indexX, int indexY
	 * @return Items
	 */
	public Items getItem(int index) {
		if (inventory.get(index) != null) {
			return inventory.get(index);
		}
		return null;
	}

}
