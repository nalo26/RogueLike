package fr.iutvalence.m2107.p24;

import java.util.Random;

public enum Direction {
/**
 * All possible directions
 */
	UP,
	RIGHT,
	DOWN,
	LEFT;

	public static Direction randomDirection() {
		Direction[] allDirections = Direction.values();
		Random random = new Random();
		return allDirections[random.nextInt(allDirections.length)];
	}
	
}
