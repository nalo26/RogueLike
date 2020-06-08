package fr.iutvalence.m2107.p24;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;

/**
 * Represent the operation of the minimap.
 */
public class MiniMap {

	/** Rooms of the World. */
	protected HashMap<Position, Room> rooms;
	/** A random object to create random values. */
	private Random random = new Random();
	/** The seed of the map. */
	private long seed;
	
	/**
	 * Constructor.
	 * Create a new room of 4 doors, and set a seed.
	 */
	public MiniMap() {
		this.rooms = new HashMap<Position, Room>();
		this.rooms.put(Player.DEFAULT_ROOM_POSITION.copy(), new RoomDisplay("1111"));
		this.generateBossRoom();
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
	}
	
	/**
	 * Create a random boss room on the map,
	 * with its key room next to it.
	 */
	private void generateBossRoom() {
		/*Position bossPos = Position.randomPosition(-9, 10, -9, 10);
		Direction bossDir = Direction.randomDirection();*/
		Position bossPos = new Position(0, -2);
		Direction bossDir = Direction.DOWN;
		while(bossPos.equals(Player.DEFAULT_ROOM_POSITION)) {
			bossPos = Position.randomPosition(-9, 10, -9, 10);
		}
		this.rooms.put(bossPos, new BossRoom(bossDir));

		Position keyPos = null;
		Direction keyDir = null;
		switch (bossDir) {
			case UP:
				keyDir = Direction.DOWN;
				keyPos = new Position(bossPos.getX(), bossPos.getY()-1);
				break;
			case RIGHT: 
				keyDir = Direction.LEFT;
				keyPos = new Position(bossPos.getX()+1, bossPos.getY());
				break;
			case DOWN:
				keyDir = Direction.UP;
				keyPos = new Position(bossPos.getX(), bossPos.getY()+1);
				break;
			case LEFT: 
				keyDir = Direction.RIGHT;
				keyPos = new Position(bossPos.getX()-1, bossPos.getY());
				break;
			default: break;
		}
		this.rooms.put(keyPos, new KeyRoom(keyDir));
	}
	
	/**
	 * Describe the behavior of the map every tick for a given player.
	 * @param room the current room.
	 * @param p the player wanted.
	 */
	public void tick(Room room, Player p) {
		// Changing of room
		// TODO TP the player to the other side of the room
		
		if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.LEFT)) && room.isOpen(Direction.LEFT)) {
			p.getRoomPosition().move(-1, 0);
			p.getPosition().set(GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.RIGHT)) && room.isOpen(Direction.RIGHT)) {
			p.getRoomPosition().move(1, 0);
			p.getPosition().set(GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.UP)) && room.isOpen(Direction.UP)) {
			p.getRoomPosition().move(0, -1);
			p.getPosition().set(GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.DOWN)) && room.isOpen(Direction.DOWN)) {
			p.getRoomPosition().move(0, 1);
			p.getPosition().set(GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		}
		
		p.updateRealPosition();
		
		if(this.rooms.get(p.getRoomPosition()) == null) {
			this.rooms.put(p.getRoomPosition().copy(), this.randomRoom(p.getRoomPosition()));
		}
		room = this.rooms.get(p.getRoomPosition());
		room.tick(p);
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
	
	/**
	 * Load all the rooms configs and positions.
	 * @param save the game that was saved
	 */
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
	
	public HashMap<Position, Room> getRooms(){
		return this.rooms;
	}
	
}
