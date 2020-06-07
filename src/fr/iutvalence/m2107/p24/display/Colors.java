package fr.iutvalence.m2107.p24.display;

import java.util.Random;

public enum Colors {

	RED,
	GREEN,
	BLUE;

	public static Colors randomColor() {
		Colors[] allColors= Colors.values();
		Random random = new Random();
		return allColors[random.nextInt(allColors.length)];
	}
	
}
