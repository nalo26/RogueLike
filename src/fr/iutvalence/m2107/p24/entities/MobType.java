package fr.iutvalence.m2107.p24.entities;

import java.util.Random;

/**
 * All possible types of mobs
 */
public enum MobType {

	SKELETON,
	ZOMBIE,
	SLIME,
	BOSS;
	
	/**
	 * Randomly choose a mob type.
	 * @return a random mob type.
	 */
	public static MobType randomMobType() {
		MobType[] allMobs = MobType.values();
		Random random = new Random();
		return allMobs[random.nextInt(allMobs.length-1)];
	}
	
}
