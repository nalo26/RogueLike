package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.items.Key;
import fr.iutvalence.m2107.p24.rooms.BossRoom;
import fr.iutvalence.m2107.p24.rooms.KeyRoom;
import fr.iutvalence.m2107.p24.rooms.Room;

/**
 * Represent the operation of the minimap.
 */
public class MiniMap {

	/** Rooms of the World. */
	protected ArrayList<Room> rooms;
	/** A random object to create random values. */
	public static final Random random = new Random();
	/** The seed of the map. */
	private long seed;
	
	/**
	 * Create a maze of room using the "Recursive Backtracker" algorithm.
	 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
	 */
	public MiniMap() {
		this.seed = random.nextLong();
		random.setSeed(this.seed);
		
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
				current.generateDoors(next);
				next.setVisited(true);
				stack.push(next);
			}
		}
		
		// Add some connections between rooms.
		for(Room r : this.rooms) {
			if(random.nextFloat() > 0.5) {
				int index = MiniMap.random.nextInt(r.getDoors().length);
				Position pos = r.getPosition().copy();
				if(index == 0) pos.move( 0, -1);
				if(index == 1) pos.move(+1,  0);
				if(index == 2) pos.move( 0, +1);
				if(index == 3) pos.move(-1,  0);
				Room n = this.getRoomAt(pos);
				if(n != null) {
					r.openDoor(index);
					n.openDoor((index + 2) % 4);
				}
			}
		}
		
		// Create boss room
		this.generateBossRoom();
		
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
			int r = random.nextInt(neighbors.size());
			return neighbors.get(r);
		}
		return null;
		
	}
	
	/**
	 * Create a random boss room on the map,
	 * with its key room next to it.
	 * Also put key item on four random room on the map.
	 */
	private void generateBossRoom() {
		Position bossPos = Position.randomPosition(-8, 8, -8, 8);
		Direction bossDir = Direction.randomDirection();

		while(bossPos.equals(Player.DEFAULT_ROOM_POSITION)) {
			bossPos = Position.randomPosition(-8, 8, -8, 8);
		}
		
		int i;
		for(i = 0; i < this.rooms.size(); i++) {
			if(this.rooms.get(i).getPosition().equals(bossPos)) break;
		}
		this.rooms.remove(i);
		this.rooms.add(new BossRoom(bossPos, bossDir));

		Position keyPos = bossPos.copy();
		Direction keyDir = null;
		Room r = null;
		switch (bossDir) {
			case UP:
				r = this.getRoomAt(new Position(bossPos.getX()+1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, false);
				r = this.getRoomAt(new Position(bossPos.getX()-1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, false);
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, false);
				keyDir = Direction.DOWN;
				keyPos.move(0, -1);
				r = this.getRoomAt(new Position(keyPos.getX()+1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, false);
				r = this.getRoomAt(new Position(keyPos.getX()-1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, false);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, true);
				break;
			case RIGHT: 
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, false);
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, false);
				r = this.getRoomAt(new Position(bossPos.getX()-1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, false);
				keyDir = Direction.LEFT;
				keyPos.move(+1, 0);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, false);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, false);
				r = this.getRoomAt(new Position(keyPos.getX()+1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, true);
				break;
			case DOWN:
				r = this.getRoomAt(new Position(bossPos.getX()+1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, false);
				r = this.getRoomAt(new Position(bossPos.getX()-1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, false);
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, false);
				keyDir = Direction.UP;
				keyPos.move(0, +1);
				r = this.getRoomAt(new Position(keyPos.getX()+1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, false);
				r = this.getRoomAt(new Position(keyPos.getX()-1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, false);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, true);
				break;
			case LEFT: 
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, false);
				r = this.getRoomAt(new Position(bossPos.getX(), bossPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, false);
				r = this.getRoomAt(new Position(bossPos.getX()+1, bossPos.getY()));
				if(r != null) r.setDoor(Direction.LEFT, false);
				keyDir = Direction.RIGHT;
				keyPos.move(-1, 0);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()+1));
				if(r != null) r.setDoor(Direction.UP, false);
				r = this.getRoomAt(new Position(keyPos.getX(), keyPos.getY()-1));
				if(r != null) r.setDoor(Direction.DOWN, false);
				r = this.getRoomAt(new Position(keyPos.getX()-1, keyPos.getY()));
				if(r != null) r.setDoor(Direction.RIGHT, true);
				break;
			default: break;
		}
		
		for(i = 0; i < this.rooms.size(); i++) {
			if(this.rooms.get(i).getPosition().equals(keyPos)) break;
		}
		this.rooms.remove(i);
		this.rooms.add(new KeyRoom(keyPos, keyDir));
		
		for(i = 0; i < 4; i++) { // add the key on random rooms.
			this.rooms.get(random.nextInt(this.rooms.size())).addItem(new Key());
		}
	}
	
	/**
	 * Describe the behavior of the map every tick for a given player.
	 * @param room the current room.
	 * @param p the player wanted.
	 * @return if the boss is dead or not.
	 */
	public boolean tick(Room room, Player p) {
		// Changing of room
		// TODO TP the player to the other side of the room
		
		if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.LEFT)) && room.isOpen(Direction.LEFT)) {
			p.getRoomPosition().move(-1, 0);
			p.getPosition().set(GamePanel.WIDTH - 150, p.getPosition().getY());
			for(Mob m : room.getMobs()) {
				if(p.getBounds().intersects(m.getBounds())) {
					m.setPosition(new Position(m.getPosition().getX(), m.getPosition().getY() + 100));
				}
			}
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.RIGHT)) && room.isOpen(Direction.RIGHT)) {
			p.getRoomPosition().move(1, 0);
			p.getPosition().set(150, p.getPosition().getY());
			for(Mob m : room.getMobs()) {
				if(p.getBounds().intersects(m.getBounds())) {
					m.setPosition(new Position(m.getPosition().getX(), m.getPosition().getY() + 100));
				}
			}
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.UP)) && room.isOpen(Direction.UP)) {
			p.getRoomPosition().move(0, -1);
			p.getPosition().set(p.getPosition().getX(), GamePanel.HEIGHT - 200);
			for(Mob m : room.getMobs()) {
				if(p.getBounds().intersects(m.getBounds())) {
					m.setPosition(new Position(m.getPosition().getX() + 100, m.getPosition().getY() + 100));
				}
			}
		} else if (p.getBounds().intersects(MiniMapDisplay.getDoorBoundFromKey(Direction.DOWN)) && room.isOpen(Direction.DOWN)) {
			p.getRoomPosition().move(0, 1);
			p.getPosition().set(p.getPosition().getX(), 80);
			for(Mob m : room.getMobs()) {
				if(p.getBounds().intersects(m.getBounds())) {
					m.setPosition(new Position(m.getPosition().getX() + 100, m.getPosition().getY() + 100));
				}
			}
		}
		
		room = this.getRoomAt(p.getRoomPosition());
		room.setVisited(true);
		return room.tick(p);
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
			newRoom.load(room);
			newRoom.setVisited((boolean) room.get("visited"));
			
			this.rooms.add(newRoom);
		}
	}
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}
	
}
