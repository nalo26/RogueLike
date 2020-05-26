package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.ressources.Images;

public class Room {
	
	/** List of all mobs in this room. */
	private List<Mob> mobs = new ArrayList<Mob>();
	
	private Position position;
	
	private boolean[] doors;
	
	private BufferedImage image;
	
	public static final int MAX_MOBS = 10;
	
	
	/**
	 * Constructor of a Room.
	 * @param pos the position of the room in the map.
	 * @param config the configuration of doors of the room (i.e. {false, true, true, false}).
	 * @param bin the binary configuration of doors.
	 */
	public Room(Position pos, boolean[] config, String bin) {
		this.position = pos;
		if(config.length == 4 && config != null) {
			this.doors = config;
			this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2)).getImage();
		}
		
		Random random = new Random();
		int mobAmount = random.nextInt(MAX_MOBS);
		
		for(int i = 0; i < mobAmount; i ++) {
			this.mobs.add(new Mob(MobType.randomMobType()));
		}
	}
	
	/**
	 * Create a new room, with the configuration as String. 
	 * @param pos the position of the room in the map.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public Room(Position pos, String config) {
		this(pos, (boolean[])Room.computeDoors(config), config);
	}
	
	/**
	 * Compute the string configuration to convert it in a boolean array.
	 * @param config the String configuration of doors (i.e. "0110").
	 * @return a boolean array of door values (i.e. {false, true, true, false}).
	 */
	private static boolean[] computeDoors(String config) {
		if(config.length() != 4) return null;
		
		boolean[] res = new boolean[4];
		for(int i = 0; i < config.length(); i++) {
			res[i] = (config.charAt(i) == '1' ? true : false);
		}
		
		return res;
	}

	public void tick() {
		for(Mob m : this.mobs) {
			m.tick();
		}
	}

	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		for(Mob m : this.mobs) {
			m.draw(g);
		}
		g.setColor(Color.RED);
	}
	
	/**
	 * Get the list of mobs of the room.
	 * @return the list of mobs (Getter).
	 */
	public List<Mob> getMobs() {
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
	
	public boolean isOpen(Direction dir) {
		switch (dir) {
			case RIGHT: return (this.doors[0]);
			case UP: return (this.doors[1]);
			case LEFT: return (this.doors[2]);
			case DOWN: return (this.doors[3]);
			default: return false;
		}
	}

	@Override
	public String toString() {
		return "Room [position=" + this.position + ", doors=" + Arrays.toString(this.doors) + "]";
	}
	
}
