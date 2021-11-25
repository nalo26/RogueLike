package fr.iutvalence.m2107.p24;

import java.util.Random;

/**
 * Represent all of the possible directions.
 */
public enum Direction {

	UP,
	RIGHT,
	DOWN,
	LEFT;

	/**
	 * Pick up a random direction.
	 * @return a random direction.
	 */
	public static Direction randomDirection() {
		Direction[] allDirections = Direction.values();
		Random random = new Random();
		return allDirections[random.nextInt(allDirections.length)];
	}
	
}
