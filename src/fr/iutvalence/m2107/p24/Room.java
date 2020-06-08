package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.MobDisplay;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.items.ItemsList;
import fr.iutvalence.m2107.p24.items.Key;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent a room and all of his component (doors, position, ...).
 */
public class Room {
	
	/** List of all mobs in this room. */
	protected List<MobDisplay> mobs = new ArrayList<MobDisplay>();
	/** List of all decorations in this room. */
	protected HashMap<Position, Images> decor = new HashMap<Position, Images>();
	/** The state of the doors in the room. */
	protected boolean[] doors;
	/**  The maximum amount of mobs that can spawn at the same time on a room. */
	public static final int MAX_MOBS = 10;
	/** The minimal number of trees on a room. */
	public static final int MIN_TREES = 5;
	/** The maximal number of trees on a room. */
	public static final int MAX_TREES = 10;
	/** The maximal number of items on a room. */
	public static final int MAX_ITEMS = 3;
	/** List of all items in this room. */
	protected List<Item> allItems;
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
		
		int decorAmount = random.nextInt(MAX_TREES-MIN_TREES)+MIN_TREES;
		for(int i = 0; i < decorAmount; i ++) {
			this.generateDecorElement();
		}
		
		int itemAmount = random.nextInt(MAX_ITEMS);
		this.allItems = new ArrayList<Item>();
		for(int i =0; i < itemAmount; i++) {
			Item it = ItemsList.randomItem();
			if(it != null) this.allItems.add(it);
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
	 * Create a room that MUST have a key on it.
	 * @param config the String configuration of doors of the room.
	 * @return the special room with the key on it.
	 */
	public Room keyRoom(String config) {
		Room r = new Room(config);
		r.allItems.add(new Key());
		return r;
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
	
	protected void generateDecorElement() {
		// Override later.
	}
	
	/** Instructions executed every tick. 
	 * @param room The display room to pass as parameter to Mob.
	 * @param p the player of the room.
	 */
	public void tick(Room room, Player p) {
		Mob isDead = null;
		List<Mob> toDelete = new ArrayList<Mob>();
		for(Mob m : this.mobs) {
			isDead = m.tick(room, p);
			if(isDead != null) toDelete.add(isDead);
		}
		if(toDelete.size() > 0) this.mobs.removeAll(toDelete);
	}
	
	public void draw(Graphics g) {
		//Override later.
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
	 /** Load a save from a Json file.
	 * @param save the file that you want to load.
	 */
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

	/** Give an array of all items in the room.
	 * @return all of the items in the room.
	 */
	public List<Item> getAllItems()	{
		return this.allItems;
	}
	
	public void removeItem(Item i) {
		this.allItems.remove(i);
	}
	
	
}
