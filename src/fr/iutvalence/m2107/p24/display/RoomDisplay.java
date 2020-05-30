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
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Display a room with its correspondent image.
 */
public class RoomDisplay extends Room {

	/** The image of the room. */
	private BufferedImage image;
	/** The bounds of the room depending on the direction. */
	private HashMap<Direction, Rectangle> bounds;
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
		this.bounds.put(Direction.UP, new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT/8 -89));
		this.bounds.put(Direction.LEFT, new Rectangle(0, 0, GamePanel.WIDTH/2 -580, GamePanel.HEIGHT));
		this.bounds.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH-60, 0, GamePanel.WIDTH, GamePanel.HEIGHT));
		this.bounds.put(Direction.DOWN, new Rectangle(0, GamePanel.HEIGHT-60, GamePanel.WIDTH, GamePanel.HEIGHT));
	}
	
	/**
	 * Create a new room, with the configuration as String. 
	 * @param pos the position of the room in the map.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public RoomDisplay(Position pos, String config) {
		this(pos, Room.computeDoors(config), config);
	}

	/**
	 * Draw the room.
	 * @param g the draw component
	 */
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		for(MobDisplay m : this.mobs) {
			m.draw(g);
		}
		g.setColor(Color.RED);
	}

	public HashMap<Direction, Rectangle> getBounds()
	{
		return this.bounds;
	}
	
	public Rectangle getBoundFromKey(Direction key)
	{
		return this.bounds.get(key);
	}

}
