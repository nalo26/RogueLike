package fr.iutvalence.m2107.p24;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.RoomDisplay;

/**
 * Represent the operation of the minimap.
 */
public class MiniMap {

	/** Rooms of the World. */
	protected HashMap<Position, RoomDisplay> rooms;
	/** A random object to create random values. */
	private Random random = new Random();
	/** The seed of the map. */
	private long seed;
	
	/**
	 * Constructor.
	 * Create a new room of 4 doors, and set a seed.
	 */
	public MiniMap() {
		this.rooms = new HashMap<Position, RoomDisplay>();
		this.rooms.put(Player.DEFAULT_ROOM_POSITION.copy(), new RoomDisplay("1111"));
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
	}
	
	/**
	 * Describe the behavior of the map every tick for a given player.
	 * @param p the player wanted.
	 */
	public void tick(Player p) {		
		// Changing of room
		// TODO TP the player to the other side of the room
		// TODO better positions
		RoomDisplay room = this.rooms.get(p.getRoomPosition());
		
		if (p.getBounds().intersects(room.getDoorBoundFromKey(Direction.LEFT)) && room.isOpen(Direction.LEFT) && room.getDoorBoundFromKey(Direction.LEFT) != null) {	//p.getPosition().getX() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.LEFT)
			p.getRoomPosition().move(-1, 0);
			p.getPosition().move(GamePanel.WIDTH/2, 0);
		} else if (p.getBounds().intersects(room.getDoorBoundFromKey(Direction.RIGHT)) && room.isOpen(Direction.RIGHT)) {
			p.getRoomPosition().move(1, 0);
			p.getPosition().move(-GamePanel.WIDTH/2, 0);
		} else if (p.getBounds().intersects(room.getDoorBoundFromKey(Direction.UP)) && room.isOpen(Direction.UP)) {	//p.getPosition().getY() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.UP)
			p.getRoomPosition().move(0, -1);
			p.getPosition().move(0, GamePanel.HEIGHT/2);
		} else if (p.getBounds().intersects(room.getDoorBoundFromKey(Direction.DOWN)) && room.isOpen(Direction.DOWN)) {
			p.getRoomPosition().move(0, 1);
			p.getPosition().move(0, -GamePanel.HEIGHT/2);
		}
		
		if(this.rooms.get(p.getRoomPosition()) == null) {
			this.rooms.put(p.getRoomPosition().copy(), this.randomRoom(p.getRoomPosition()));
		}
		room = this.rooms.get(p.getRoomPosition());
		room.tick(room);
	}
	
	/**
	 * Create a random room from a given position.
	 * This generation is procedural :
	 * The algorithm will tests if the new room should or not have a door
	 * on every sides according to all the rooms surrounding this new room.
	 * If no room is found on a specific side, a door is randomly set (1/2 chance).
	 * @param pos the position where the room will be created.
	 * @return the new room randomly created.
	 */
	private RoomDisplay randomRoom(Position pos) {
		String doors = "";
		Room query = null;
		
		query = this.rooms.get(new Position(pos.getX(), pos.getY()+1));
		if(query != null) {
			if(query.isOpen(Direction.UP)) doors = "1" + doors;
			else doors = "0" + doors;
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.rooms.get(new Position(pos.getX()-1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.RIGHT)) doors = "1" + doors;
			else doors = "0" + doors;
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.rooms.get(new Position(pos.getX(), pos.getY()-1));
		if(query != null) {
			if(query.isOpen(Direction.DOWN)) doors = "1" + doors;
			else doors = "0" + doors;
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.rooms.get(new Position(pos.getX()+1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.LEFT)) doors = "1" + doors;
			else doors = "0" + doors;
		} else doors = this.random.nextInt(2) + doors;
		
		return new RoomDisplay(doors);
	}
	
	@SuppressWarnings("unchecked")
	public void load(JSONObject save) {
		this.rooms.clear();
		
		HashMap<String, Object> rooms = (HashMap<String, Object>) save;
		for(HashMap.Entry<String, Object> r : rooms.entrySet()) {
			JSONObject room = (JSONObject) r.getValue();
			
			String config = (String) room.get("connections");
			JSONObject pos = (JSONObject) room.get("position");
			Position roomPos = new Position(((Long) pos.get("x")).intValue(), ((Long) pos.get("y")).intValue());
			RoomDisplay newRoom = new RoomDisplay(config);
			newRoom.load((JSONObject) room.get("mobs"));
			
			this.rooms.put(roomPos, newRoom);
		}
	}
	
	public HashMap<Position, RoomDisplay> getRooms(){
		return this.rooms;
	}
	
}
