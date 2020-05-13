package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
	
	/**
	 * List of all mobs in this room.
	 */
	private List<Mob> mobs = new ArrayList<Mob>();
	
	public static final int MAX_MOBS = 10;
	
	
	/**
	 * Constructor of your Room.
	 */
	public Room() {
		Random random = new Random();
		int mobAmount = random.nextInt(MAX_MOBS);
		
		for(int i = 0; i < mobAmount; i ++) {
			this.mobs.add(new Mob(MobType.randomMobType()));
		}
	}

	/**
	 * @return the list of mobs (Getter)
	 */
	public List<Mob> getMobs() {
		return this.mobs;
	}
	
}
