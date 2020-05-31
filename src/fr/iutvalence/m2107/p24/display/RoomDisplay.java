package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Room;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Display a room with its correspondent image.
 */
public class RoomDisplay extends Room {

	/** The image of the room. */
	private BufferedImage image;
	/** The bounds of the room depending on the direction. */
	private HashMap<Direction, Rectangle> bounds;

	/** The bounds of the doors depending on the direction. */
	private HashMap<Direction, Rectangle> doors;
	
	/** The width offset of the walls. */
	private static int offsetW = (int) (0.05 * GamePanel.WIDTH);
	
	/** The height offset of the walls. */
	private static int offsetH = (int) ((96/1088) * GamePanel.HEIGHT);
	/**
	 * Construct the room displaying.
	 * It gets the images according to the binary order of doors.
	 * For example: "1010" will be open on right and left, but close on up and down.
	 * This will be the image "ROOM10".
	 * @param pos the position of the room
	 * @param config the configuration of the room (which type of room)
	 * @param bin the value of the room (in the file name)
	 */
	public RoomDisplay(Position pos, boolean[] config, String bin) {
		super(pos, config, bin);
		this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2)).getImage();
		this.bounds = new HashMap<Direction, Rectangle>();
		this.updateBounds();
		this.doors = new HashMap<Direction, Rectangle>();
	}
	
	/**
	 * Create a new room, with the configuration as String. 
	 * @param pos the position of the room in the map.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public RoomDisplay(Position pos, String config) {
		this(pos, Room.computeDoors(config), config);
		this.doors = new HashMap<Direction, Rectangle>();
		this.bounds = new HashMap<Direction, Rectangle>();
	}

	/**
	 * Draw the room.
	 * @param g the draw component
	 */
	public void draw(Graphics g) {
		this.updateBounds();
		
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		for(MobDisplay m : this.mobs) {
			m.draw(g);
		}
		g.setColor(Color.RED);
	}

	private void updateBounds() {
		offsetW = (int) (0.05 * GamePanel.WIDTH);
		offsetH = (int) (96/(float)this.image.getHeight() * GamePanel.HEIGHT);
		
		this.bounds.put(Direction.UP, new Rectangle(0, 0, GamePanel.WIDTH, 1));
		this.bounds.put(Direction.LEFT, new Rectangle(0, 0, offsetW, GamePanel.HEIGHT));
		this.bounds.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH - offsetW, 0, offsetW, GamePanel.HEIGHT));
		this.bounds.put(Direction.DOWN, new Rectangle(0, GamePanel.HEIGHT - offsetH, GamePanel.WIDTH, offsetH));
		
		this.doors.put(Direction.UP, new Rectangle(GamePanel.WIDTH/2 - 10, 0, GamePanel.WIDTH/2 + 10, 60));
	}

	/**
	 * Give the bounds of the walls.
	 * @return the bounds of the walls depending on the direction.
	 */
	public HashMap<Direction, Rectangle> getBounds() {
		return this.bounds;
	}
	
	/**
	 * Give a bound of a wall depending on a given direction.
	 * @param key the direction on what you want to know the bound.
	 * @return the bound corresponding to the given direction.
	 */
	public Rectangle getBoundFromKey(Direction key) {
		return this.bounds.get(key);
	}
	
	public Rectangle getDoorBoundFromKey(Direction key)	{
		return this.doors.get(key);
	}

}
