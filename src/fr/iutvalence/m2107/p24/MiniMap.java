package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.rooms.Room;

/**
 * Represent the operation of the minimap.
 */
public class MiniMap {

	/** Rooms of the World. */
	protected ArrayList<Room> rooms;
	/** A random object to create random values. */
	private Random random = new Random();
	/** The seed of the map. */
	private long seed;
	
	/**
	 * Create a maze of room using the "Recursive Backtracker" algorithm.
	 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
	 */
	public MiniMap() {
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
		
		this.rooms = new ArrayList<Room>();
		
		//create empty rooms.
		for(int y = -9; y < 10; y++) {
			for(int x = -9; x < 10; x++) {
				this.rooms.add(new RoomDisplay(new Position(x, y)));
			}
		}

		// Generate maze with rooms.
		Stack<Room> stack = new Stack<Room>();
		Room current = this.getRoomAt(Player.DEFAULT_ROOM_POSITION);
		current.setVisited(true);
		stack.push(current);
		Room next = null;
		while(stack.size() > 0) {
			current = stack.pop();
			next = checkNeighbors(current.getPosition());
			if(next != null) {
				stack.push(current);
				current.addDoors(next);
				next.setVisited(true);
				stack.push(next);
			}
		}
		
		// Finished up generation.
		for(Room r : this.rooms) {
			r.setImage();
			r.setVisited(false);
		}
	}
	
	/**
	 * Gets a room at a given position.
	 * @param p The position of the room to get.
	 * @return The room standing to this position.
	 */
	public Room getRoomAt(Position p) {
		for(Room r : this.rooms) {
			if(r.getPosition().equals(p)) return r;
		}
		return null;
	}
	
	/**
	 * Checks if a room at the given position has a neighbor,
	 * and choose one of them randomly to be the next.
	 * @param p The position of the room.
	 * @return The next room.
	 */
	public Room checkNeighbors(Position p) {
		ArrayList<Room> neighbors = new ArrayList<Room>();
		
		Room top = this.getRoomAt(new Position(p.getX(), p.getY()-1));
		Room right = this.getRoomAt(new Position(p.getX()+1, p.getY()));
		Room bottom = this.getRoomAt(new Position(p.getX(), p.getY()+1));
		Room left = this.getRoomAt(new Position(p.getX()-1, p.getY()));
		
		if(top != null && !top.isVisited()) neighbors.add(top);
		if(right != null && !right.isVisited()) neighbors.add(right);
		if(bottom != null && !bottom.isVisited()) neighbors.add(bottom);
		if(left != null && !left.isVisited()) neighbors.add(left);
		
		if(neighbors.size() > 0) {
			int r = this.random.nextInt(neighbors.size());
			return neighbors.get(r);
		}
		return null;
		
	}
	
//	/**
//	 * Create a random boss room on the map,
//	 * with its key room next to it.
//	 */
//	private void generateBossRoom() {
//		/*Position bossPos = Position.randomPosition(-9, 10, -9, 10);
//		Direction bossDir = Direction.randomDirection();*/
//		Position bossPos = new Position(0, -2);
//		Direction bossDir = Direction.DOWN;
//		while(bossPos.equals(Player.DEFAULT_ROOM_POSITION)) {
//			bossPos = Position.randomPosition(-9, 10, -9, 10);
//		}
//		this.rooms.put(bossPos, new BossRoom(bossDir));
//
//		Position keyPos = null;
//		Direction keyDir = null;
//		switch (bossDir) {
//			case UP:
//				keyDir = Direction.DOWN;
//				keyPos = new Position(bossPos.getX(), bossPos.getY()-1);
//				break;
//			case RIGHT: 
//				keyDir = Direction.LEFT;
//				keyPos = new Position(bossPos.getX()+1, bossPos.getY());
//				break;
//			case DOWN:
//				keyDir = Direction.UP;
//				keyPos = new Position(bossPos.getX(), bossPos.getY()+1);
//				break;
//			case LEFT: 
//				keyDir = Direction.RIGHT;
//				keyPos = new Position(bossPos.getX()-1, bossPos.getY());
//				break;
//			default: break;
//		}
//		this.rooms.put(keyPos, new KeyRoom(keyDir));
//	}
//	
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
		
		room = this.getRoomAt(p.getRoomPosition());
		room.setVisited(true);
		room.tick(p);
	}
	
	/**
	 * Load all the rooms configuration and positions.
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
			Room newRoom = new RoomDisplay(roomPos, config);
			newRoom.load((JSONObject) room.get("mobs"));
			
			this.rooms.add(newRoom);
		}
	}
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}
	
}
