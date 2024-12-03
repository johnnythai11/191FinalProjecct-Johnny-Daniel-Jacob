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

public class Store extends Items {
	private Items[] Stock = new Items[20];
	private String itemName;
	private int typeOfArmor;
	private int tierOfArmor;

	/**
	 * Constructor to create a Store with the randomly generated items
	 */
	public Store() {
		generateStock();

	}

	/*
	 * Prints out the item in the store
	 * 
	 * @Return String itemName, integer randInt1
	 */
	@Override
	public String toString() {
		StringBuilder myString = new StringBuilder();
		for (int row = 0; row < Stock.length; row++) {
			myString.append(Stock[row] + "\n");
		}

		return myString.toString();

	}

	/*
	 * Create the store with randomly generated 20 items
	 */
	public void generateStock() {
		Random rand = new Random();

		for (int row = 0; row < Stock.length; row++) {

			typeOfArmor = rand.nextInt(4) + 0;
			tierOfArmor = rand.nextInt(6) + 0;

			if (typeOfArmor == 0) {
				itemName = "Heart";
			}

			else if (typeOfArmor == 1) {
				itemName = "Helmet";
			} else if (typeOfArmor == 2 && tierOfArmor == 0) {
				itemName = "Shield";
			} else if (typeOfArmor == 3 && tierOfArmor == 0) {
				itemName = "Staff";
			}

			else if (typeOfArmor == 2 && tierOfArmor == 1) {
				itemName = "Wood ChestPlate";
			} else if (typeOfArmor == 2 && tierOfArmor == 2) {
				itemName = "Bronze ChestPlate";
			}

			else if (typeOfArmor == 2 && tierOfArmor == 3) {
				itemName = "Copper ChestPlate";
			}

			else if (typeOfArmor == 2 && tierOfArmor == 4) {
				itemName = "Iron ChestPlate";
			}

			else if (typeOfArmor == 2 && tierOfArmor == 5) {
				itemName = "Diamond ChestPlate";
			}

			else if (typeOfArmor == 3 && tierOfArmor == 1) {
				itemName = "Wood Claw";
			} else if (typeOfArmor == 3 && tierOfArmor == 2) {
				itemName = "Bronze Claw";
			}

			else if (typeOfArmor == 3 && tierOfArmor == 3) {
				itemName = "Copper Claw";
			}

			else if (typeOfArmor == 3 && tierOfArmor == 4) {
				itemName = "Iron Claw";
			}

			else if (typeOfArmor == 3 && tierOfArmor == 5) {
				itemName = "Diamond Claw";
			}

			switch (tierOfArmor) {
			case 0:
				Items myItem1 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem1;
				break;

			case 1:
				Items myItem2 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem2;
				break;

			case 2:
				Items myItem3 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem3;
				break;

			case 3:
				Items myItem4 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem4;
				break;

			case 4:
				Items myItem5 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem5;
				break;

			case 5:
				Items myItem6 = new Items(typeOfArmor, tierOfArmor);
				Stock[row] = myItem6;
				break;
			}
		}
	}

	/**
	 * returns the item from the store
	 * 
	 * @param int index
	 * @return Items
	 */
	public Items getItem(int index) {
		return Stock[index];
	}
}
