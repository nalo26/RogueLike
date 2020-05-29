package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
}
