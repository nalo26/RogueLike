package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.MobDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent a room and all of his component (doors, position, ...).
 */
public class Room {
	
	/** List of all mobs in this room. */
	protected List<MobDisplay> mobs = new ArrayList<MobDisplay>();
	
	protected HashMap<Position, Images> decor = new HashMap<Position, Images>();
	/** The state of the doors in the room. */
	protected boolean[] doors;
	/**  The maximum mob who can spawn at the same time in the room. */
	public static final int MAX_MOBS = 10;
	
	/**
	 * Construct of a new room.
	 * It gets the images according to the binary order of doors.
	 * For example: "1010" will be open on right and left, but close on up and down.
	 * This will be the image "ROOM10".
	 * @param config the configuration of doors of the room (i.e. {false, true, true, false}).
	 * @param bin the binary configuration of doors.
	 */
	public Room(boolean[] config, String bin) {
		this.doors = config;
		
		Random random = new Random();
		int mobAmount = random.nextInt(MAX_MOBS);
		for(int i = 0; i < mobAmount; i ++) {
			this.mobs.add(new MobDisplay(MobType.randomMobType()));
		}
		
		int treeAmount = random.nextInt(5)+5;
		for(int i = 0; i < treeAmount; i ++) {
			this.decor.put(Position.randomPosition(70, GamePanel.WIDTH - 200, 70, GamePanel.HEIGHT-200), Images.valueOf("TREE" + (random.nextInt(4)+1)));
		}
	}
	
	/**
	 * Create a new room, with the configuration as String. 
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public Room(String config) {
		this(Room.computeDoors(config), config);
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
			res[i] = config.charAt(i) == '1';
		}
		
		return res;
	}
	
	/** Instructions executed every tick. 
	 * @param r The display room to pass as parameter to Mob.
	 * @param p the player of the room.
	 */
	public void tick(RoomDisplay r, Player p) {
		Mob isDead = null;
		List<Mob> toDelete = new ArrayList<Mob>();
		for(Mob m : this.mobs) {
			isDead = m.tick(r, p);
			if(isDead != null) toDelete.add(isDead);
		}
		if(toDelete.size() > 0) this.mobs.removeAll(toDelete);
	}
	
	/**
	 * Get the list of mobs of the room.
	 * @return the list of mobs (Getter).
	 */
	public List<MobDisplay> getMobs() {
		return this.mobs;
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

	public String getDoorsString() {
		String res = "";
		for(Boolean door : this.doors) {
			res += (door.booleanValue() ? "1" : "0");
		}
		return res;
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
	
	@SuppressWarnings("unchecked")
	public void load(JSONObject save) {
		this.mobs.clear();
		
		HashMap<String, Object> mobs = (HashMap<String, Object>) save;
		for(HashMap.Entry<String, Object> m : mobs.entrySet()) {
			JSONObject mob = (JSONObject) m.getValue();
			
			MobType type = MobType.valueOf((String) mob.get("type"));
			MobDisplay newMob = new MobDisplay(type);
			newMob.load(mob);
			
			this.mobs.add(newMob);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Room [doors=" + Arrays.toString(this.doors) + "]";
	}
	
}
