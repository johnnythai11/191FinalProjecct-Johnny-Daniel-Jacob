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

import java.io.*;
import view.CombatView;

public class SaveLoad {

	/**
	 * Saves the player class that has all the stats, items, inventory, and
	 * equipment stored in it.
	 * 
	 * @param object
	 */
	public void binarySave(PlayerChicken object) {
		try {
			FileOutputStream fstream = new FileOutputStream("ChickenFighterSave.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fstream);
			oos.writeObject(object);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Loads the game with the saved player class
	 * 
	 */
	public static void binaryLoad() {
		try {
			FileInputStream fileIn = new FileInputStream("ChickenFighterSave.dat");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			PlayerChicken p = (PlayerChicken) obj;
			new CombatView(p);
		} catch (IOException | ClassNotFoundException e) {

		}
	}
}