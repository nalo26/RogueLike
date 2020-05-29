package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.display.MobDisplay;

/**
 * Represent a room and all of his component (doors, position, ...).
 */
public class Room {
	
	/** List of all mobs in this room. */
	protected List<MobDisplay> mobs = new ArrayList<MobDisplay>();
	/** The position of the room. */
	protected Position position;
	/** The state of the doors in the room. */
	protected boolean[] doors;
	/**  The maximum mob who can spawn at the same time in the room. */
	public static final int MAX_MOBS = 10;
	
	/**
	 * Construct of a new room.
	 * It gets the images according to the binary order of doors.
	 * For example: "1010" will be open on right and left, but close on up and down.
	 * This will be the image "ROOM10".
	 * @param pos the position of the room in the map.
	 * @param config the configuration of doors of the room (i.e. {false, true, true, false}).
	 * @param bin the binary configuration of doors.
	 */
	public Room(Position pos, boolean[] config, String bin) {
		this.position = pos;
		this.doors = config;
		
		Random random = new Random();
		int mobAmount = random.nextInt(MAX_MOBS);
		
		for(int i = 0; i < mobAmount; i ++) {
			this.mobs.add(new MobDisplay(MobType.randomMobType()));
		}
	}
	
	/**
	 * Create a new room, with the configuration as String. 
	 * @param pos the position of the room in the map.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public Room(Position pos, String config) {
		this(pos, Room.computeDoors(config), config);
	}
	
	/**
	 * Compute the string configuration to convert it in a boolean array.
	 * @param config the String configuration of doors (i.e. "0110").
	 * @return a boolean array of door values (i.e. {false, true, true, false}).
	 */
	protected static boolean[] computeDoors(String config) {
		if(config.length() != 4) return null;
		
		boolean[] res = new boolean[4];
		for(int i = 0; i < config.length(); i++) {
			res[i] = (config.charAt(i) == '1' ? true : false);
		}
		
		return res;
	}
	
	/** Instructions executed every tick. */
	public void tick() {
		for(Mob m : this.mobs) {
			m.tick();
		}
	}
	
	/**
	 * Get the list of mobs of the room.
	 * @return the list of mobs (Getter).
	 */
	public List<MobDisplay> getMobs() {
		return this.mobs;
	}

	/**
	 * Get the position of the room in the map.
	 * @return the position of the room (Getter).
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Get the doors of the room.
	 * @return the boolean array of doors:
	 *  [0] right
	 *  [1] up
	 *  [2] left
	 *  [3] down
	 */
	public boolean[] getDoors() {
		return this.doors;
	}
	
	/**
	 * Check if the given door is open.
	 * @param dir the direction of the door.
	 * @return true is the door is open.
	 */
	public boolean isOpen(Direction dir) {
		switch (dir) {
			case RIGHT: return (this.doors[0]);
			case UP: return (this.doors[1]);
			case LEFT: return (this.doors[2]);
			case DOWN: return (this.doors[3]);
			default: return false;
		}
	}
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Room [position=" + this.position + ", doors=" + Arrays.toString(this.doors) + "]";
	}
	
}
