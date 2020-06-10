package fr.iutvalence.m2107.p24.rooms;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.display.MobDisplay;
import fr.iutvalence.m2107.p24.entities.Boss;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.entities.MobType;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.items.ItemsList;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent a room and all of his component (doors, position, ...).
 */
public class Room {
	
	/** The maximum amount of mobs that can spawn at the same time on a room. */
	public static final int MAX_MOBS = 10;
	/** The minimal number of trees on a room. */
	public static final int MIN_TREES = 5;
	/** The maximal number of trees on a room. */
	public static final int MAX_TREES = 10;
	/** The maximal number of items on a room. */
	public static final int MAX_ITEMS = 3;
	/** The position of the room. */
	private Position position;
	/** The different doors of the room. */
	private boolean[] doors = {false, false, false, false};
	/** If the room has been visited. */
	private boolean visited;
	
	/** List of all items in this room. */
	protected ArrayList<Item> items = new ArrayList<Item>();
	/** List of all mobs in this room. */
	protected ArrayList<Mob> mobs = new ArrayList<Mob>();
	/** List of all decorations in this room. */
	protected HashMap<Position, Images> decor = new HashMap<Position, Images>();
	
	/**
	 * Create a new Room, fully close.
	 * Generate some mobs, items & decoration on it.
	 * @param p The position of the room on the map.
	 */
	public Room(Position p) {
		this.position = p;
		this.visited = false;
		
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
		for(int i = 0; i < itemAmount; i ++) {
			Item it = ItemsList.randomItem();
			if(it != null) this.items.add(it);
		}
	}
	
	/**
	 * Create a room with a specific configuration.
	 * @param p The position of the room on the map.
	 * @param config The doors' configuration. (i.e. "0110");
	 * @see #getDoors()
	 */
	public Room(Position p, String config) {
		this(p);
		this.doors = computeDoors(config);
	}
	
	/**
	 * Compute the string configuration to convert it in a boolean array.
	 * @param config the String configuration of doors (i.e. "0110").
	 * @return a boolean array of door values (i.e. {false, true, true, false}).
	 * @see #getDoors()
	 */
	protected static boolean[] computeDoors(String config) {
		if(config.length() != 4) return null;
		
		boolean[] res = new boolean[4];
		for(int i = 0; i < config.length(); i++) {
			res[i] = config.charAt(i) == '1';
		}
		
		return res;
	}
	/**
	 * Generate doors in the room.
	 * @param r The room that is generated.
	 */
	public void generateDoors(Room r) {
		int x = this.position.getX() - r.position.getX();
		if(x == 1) {
			this.doors[3] = true;
			r.doors[1] = true;
		} else if (x == -1) {
			this.doors[1] =  true;
			r.doors[3] = true;
		}
		
		int y = this.position.getY() - r.position.getY();
		if(y == 1) {
			this.doors[0] = true;
			r.doors[2] = true;
		} else if (y == -1) {
			this.doors[2] =  true;
			r.doors[0] = true;
		}
	}
	
	public void openDoor(int d) {
		this.doors[d] = true;
	}

	public void setDoor(Direction d, boolean b) {
		switch(d) {
			case UP:
				this.doors[0] = b;
				break;
			case RIGHT:
				this.doors[1] = b;
				break;
			case DOWN:
				this.doors[2] = b;
				break;
			case LEFT:
				this.doors[3] = b;
				break;
			default: break;
		}
	}
	
	protected void generateDecorElement() {
		// Override later.
	}
	
	protected void update(Player p) {
		// Override later.
	}
	
	public void setImage() {
		// Override later.
	}
	
	/**
	 * Describe the behavior of the room every game tick.
	 * @param p the Player of the room.
	 * @return if the boss is dead or not.
	 */
	public boolean tick(Player p) {
		Mob isDead = null;
		ArrayList<Mob> toDelete = new ArrayList<Mob>();
		for(Mob m : this.mobs) {
			isDead = m.tick(this, p);
			if(isDead != null) toDelete.add(isDead);
		}
		if(toDelete.size() > 0) {
			this.mobs.removeAll(toDelete);
			p.addXp(50);
			if(toDelete.get(0) instanceof Boss) {
				this.update(p);
				return true;
			}
		}
		
		if(this instanceof KeyRoom) this.update(p);
		return false;
	}
	
	public void draw(Graphics g) {
		// Override later.
	}
	
	/**
	 * Check if the door at the given Direction on the room is open.
	 * @param dir The direction of the door.
	 * @return <tt>true</tt> is the door is open, <tt>false</tt> else.
	 */
	public boolean isOpen(Direction dir) {
		switch (dir) {
			case UP: return this.doors[0];
			case RIGHT: return this.doors[1];
			case DOWN: return this.doors[2];
			case LEFT: return this.doors[3];
			default: return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void load(JSONObject save) {
		this.mobs.clear();
		this.items.clear();
		
		HashMap<String, Object> mobs = (HashMap<String, Object>) save.get("mobs");
		for(HashMap.Entry<String, Object> m : mobs.entrySet()) {
			JSONObject mob = (JSONObject) m.getValue();
			
			Mob newMob = new Mob(MobType.randomMobType());
			String sType = (String) mob.get("type");
			if(MobType.valueOf(sType) != MobType.BOSS) {
				MobType type = MobType.valueOf(sType);
				newMob = new MobDisplay(type);
			} else newMob = new Boss();
			
			newMob.load(mob);
			this.mobs.add(newMob);
		}
		
		HashMap<String, Object> items = (HashMap<String, Object>) save.get("items");
		for(HashMap.Entry<String, Object> i : items.entrySet()) {
			JSONObject item = (JSONObject) i.getValue();
			
			Item newItem = ItemsList.valueOf((String) item.get("item")).item;
			JSONObject pos = (JSONObject) item.get("position");
			newItem.setPosition(((Long) pos.get("x")).intValue(), ((Long) pos.get("y")).intValue());
			
			this.items.add(newItem);
		}	
	}
	/**
	 * @return if the room has been visited.
	 */
	public boolean isVisited() {
		return this.visited;
	}
	/**
	 * Set if the room has already been visited.
	 * @param v if the room is already visited.
	 */
	public void setVisited(boolean v) {
		this.visited = v;
	}
	/**
	 * @return items on the room.
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}
	/**
	 * Add an item to the room.
	 * @param i the item who's going to be add.
	 */
	public void addItem(Item i) {
		this.items.add(i);
	}
	/**
	 * Remove an item to the room.
	 * @param i the item who's going to be remove.
	 */
	public void removeItem(Item i) {
		this.items.remove(i);
	}
	/**
	 * @return the mobs in the room.
	 */
	public ArrayList<Mob> getMobs() {
		return this.mobs;
	}
	/**
	 * @return the room position.
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Get the doors of the room.
	 * @return the boolean array of doors:
	 *  [0] up
	 *  [1] right
	 *  [2] down
	 *  [3] left
	 */
	public boolean[] getDoors() {
		return this.doors;
	}
	
	public String getDoorsString() {
		String res = "";
		for(boolean d : this.doors) {
			res += (d ? "1" : "0");
		}
		return res;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Room [position=" + this.position + ", doors=" + Arrays.toString(this.doors) + "]";
	}
	
}
