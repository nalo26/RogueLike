package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

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
	
	/**
	 * Construct the room displaying.
	 * It gets the images according to the binary order of doors.
	 * For example: "1010" will be open on right and left, but close on up and down.
	 * This will be the image "ROOM10".
	 * @param config the configuration of the room (which type of room)
	 * @param bin the value of the room (in the file name)
	 */
	public RoomDisplay(boolean[] config, String bin) {
		super(config, bin);
		this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2)).getImage();
	}
	
	/**
	 * Create a new room, with the configuration as String.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public RoomDisplay(String config) {
		this(Room.computeDoors(config), config);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void generateDecorElement() {
		Images im = Images.valueOf("TREE" + (new Random().nextInt(4)+1));
		
		//TODO adapt to the size of window.
		Position pos = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		Rectangle rect = new Rectangle(pos.getX(), pos.getY(), im.getImage().getWidth(), im.getImage().getHeight());
		while(!MiniMapDisplay.canBeCreatedAt(rect)) {
			pos = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			rect = new Rectangle(pos.getX(), pos.getY(), im.getImage().getWidth(), im.getImage().getHeight());
		}
		this.decor.put(pos, im);
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
		for(HashMap.Entry<Position, Images> entry : this.decor.entrySet()) {
			Position pos = World.updatePosition(entry.getKey());
			BufferedImage im = entry.getValue().getImage();
			Position dim = World.updatePosition(new Position(im.getWidth(), im.getHeight()));
			g.drawImage(im, pos.getX(), pos.getY(), dim.getX(), dim.getY(), null);
		}
	}

}
