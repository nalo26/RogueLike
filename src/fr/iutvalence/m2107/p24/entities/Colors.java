package fr.iutvalence.m2107.p24.entities;

import java.util.Random;
/**
 * All possible types of colors.
 */
public enum Colors {

	RED,
	GREEN,
	BLUE;
	
	/**
	 * Randomly choose a color.
	 * @return a random color.
	 */
	public static Colors randomColor() {
		Colors[] allColors= Colors.values();
		Random random = new Random();
		return allColors[random.nextInt(allColors.length)];
	}
	
}
