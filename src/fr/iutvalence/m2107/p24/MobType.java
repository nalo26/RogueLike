package fr.iutvalence.m2107.p24;

import java.util.Random;

public enum MobType {
/**
 * All possible types of mobs
 */
	SKELETON,
	ZOMBIE,
	SLIME;
	
	public static MobType randomMobType() {
		MobType[] allMobs = MobType.values();
		Random random = new Random();
		return allMobs[random.nextInt(allMobs.length)];
	}
	
}
