package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.ressources.Images;

public class Room {
	
	/** List of all mobs in this room. */
	private List<Mob> mobs = new ArrayList<Mob>();
	
	private Position position;
	
	private boolean[] doors;
	
	private Images image;
	
	public static final int MAX_MOBS = 10;
	
	
	/**
	 * Constructor of your Room.
	 * @param pos the position of the room in the map.
	 * @param config the configuration of doors of the room (i.e. 0110).
	 * @param bin the binary configuration of doors.
	 */
	public Room(Position pos, boolean[] config, String bin) {
		this.position = pos;
		if(config.length == 4 && config != null) {
			this.doors = config;
			this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2));
		}
		else this.doors = null; //TODO procedural generation
		
		Random random = new Random();
		int mobAmount = random.nextInt(MAX_MOBS);
		
		for(int i = 0; i < mobAmount; i ++) {
			this.mobs.add(new Mob(MobType.randomMobType()));
		}
	}
	
	public Room(Position pos, String config) {
		this(pos, (boolean[])Room.computeDoors(config), config);
	}

	public Room(Position pos) {
		this(pos, (boolean[])null, (String)null);
	}
	
	private static boolean[] computeDoors(String config) {
		if(config.length() != 4) return null;
		
		boolean[] res = new boolean[4];
		for(int i = 0; i < config.length(); i++) {
			res[i] = (config.charAt(i) == '1' ? true : false);
		}
		
		return res;
	}

	public void draw(Graphics g) {
		g.drawImage(this.image.getImage(), (int)GamePanel.WIDTH/2-GamePanel.HEIGHT/2, 0, GamePanel.HEIGHT, GamePanel.HEIGHT, null);
	}
	
	/**
	 * @return the list of mobs (Getter)
	 */
	public List<Mob> getMobs() {
		return this.mobs;
	}

	public Position getPosition() {
		return this.position;
	}

	public boolean[] getDoors() {
		return this.doors;
	}
	
}
