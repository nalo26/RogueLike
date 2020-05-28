package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.display.RoomDisplay;

/**
 * Represent the operation of the minimap
 *
 */
public class MiniMap {

	/** Rooms of the World. */
	protected List<RoomDisplay> rooms;
	
	/** A random object to create random values. */
	private Random random = new Random();
	
	/** the seed of the map. */
	private long seed;
	
	/**
	 * Constructor.
	 */
	public MiniMap() {
		this.rooms = new ArrayList<RoomDisplay>();
		this.rooms.add(new RoomDisplay(new Position(0, 0), "1111"));
		this.seed = this.random.nextLong();
		this.random.setSeed(this.seed);
	}
	/**
	 * Describe the behavior of the minimap every tick for a given player.
	 * @param p the player wanted.
	 */
	public void tick(Player p) {
		// Changing of room
		// TODO TP the player to the other side of the room
		// TODO better positions
		if (p.getPosition().getX() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.LEFT)) {
			p.getRoomPosition().move(-1, 0);
			p.getPosition().move(GamePanel.WIDTH/2, 0);
		}
		if (p.getPosition().getX() >= GamePanel.WIDTH && this.getRoom(p.getRoomPosition()).isOpen(Direction.RIGHT)) {
			p.getRoomPosition().move(1, 0);
			p.getPosition().move(-GamePanel.WIDTH/2, 0);
		}
		if (p.getPosition().getY() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.UP)) {
			p.getRoomPosition().move(0, -1);
			p.getPosition().move(0, GamePanel.HEIGHT/2);
		}
		if (p.getPosition().getY() >= GamePanel.HEIGHT && this.getRoom(p.getRoomPosition()).isOpen(Direction.DOWN)) {
			p.getRoomPosition().move(0, 1);
			p.getPosition().move(0, -GamePanel.HEIGHT/2);
		}
		
		if(this.getRoom(p.getRoomPosition()) == null) {
			this.rooms.add(this.randomRoom(p.getRoomPosition()));
		}
        this.getRoom(p.getRoomPosition()).tick();
	}
	
	/** 
	 * Allow to know the current room by a given position.
	 * @param pos the position what you want to know the room.
	 * @return the display of the room for the given position.
	 */
	protected RoomDisplay getRoom(Position pos) {
		for(RoomDisplay r : this.rooms) {
			if(r.getPosition().equals(pos)) return r;
		}
		return null;
	}
	
	/**
	 * Create a random room from a given position.
	 * @param pos the position where you want to create the room.
	 * @return a displaying of a room.
	 */
	private RoomDisplay randomRoom(Position pos) {
		String doors = "";
		Room query = null;
		System.out.println("----------------------");
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()+1));
		if(query != null) {
			if(query.isOpen(Direction.UP)) doors = "1" + doors;
			else doors = "0" + doors;
			System.out.println("Must be " + (doors.charAt(0) == '1' ? "open" : "close") + " on DOWN");
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.getRoom(new Position(pos.getX()-1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.RIGHT)) doors = "1" + doors;
			else doors = "0" + doors;
			System.out.println("Must be " + (doors.charAt(0) == '1' ? "open" : "close") + " on LEFT");
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()-1));
		if(query != null) {
			if(query.isOpen(Direction.DOWN)) doors = "1" + doors;
			else doors = "0" + doors;
			System.out.println("Must be " + (doors.charAt(0) == '1' ? "open" : "close") + " on UP");
		} else doors = this.random.nextInt(2) + doors;
		
		query = this.getRoom(new Position(pos.getX()+1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.LEFT)) doors = "1" + doors;
			else doors = "0" + doors;
			System.out.println("Must be " + (doors.charAt(0) == '1' ? "open" : "close") + " on RIGHT");
		} else doors = this.random.nextInt(2) + doors;
		
		return new RoomDisplay(new Position(pos.getX(), pos.getY()), doors);
	}
	
}
