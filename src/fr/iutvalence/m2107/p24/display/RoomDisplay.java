package fr.iutvalence.m2107.p24.display;

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
	private HashMap<Direction, Rectangle> walls;

	/** The bounds of the doors depending on the direction. */
	private HashMap<Direction, Rectangle> doors;
	
	/** The width offset of the walls. */
	private static int offsetW = (int) (0.05 * GamePanel.WIDTH);
	/** The height offset of the walls. */
	private static int offsetH = (int) ((96/1088) * GamePanel.HEIGHT);
	
	/** The width of the door depending on the screen size. */
	private static int doorWidth = GamePanel.WIDTH*64/1920;
	
	/** The height of the door depending on the screen size. */
	private static int doorHeight = GamePanel.HEIGHT*64/1088 - 64;
	
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
		this.walls = new HashMap<Direction, Rectangle>();
		this.doors = new HashMap<Direction, Rectangle>();
		this.updateBounds();
	}
	
	/**
	 * Create a new room, with the configuration as String.
	 * @param config the String configuration of doors of the room (i.e. "0110").
	 */
	public RoomDisplay(String config) {
		this(Room.computeDoors(config), config);
		this.doors = new HashMap<Direction, Rectangle>();
		this.walls = new HashMap<Direction, Rectangle>();
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
		for(HashMap.Entry<Position, Images> entry : this.decor.entrySet()) {
			Position pos = World.updatePosition(entry.getKey());
			BufferedImage im = entry.getValue().getImage();
			Position dim = World.updatePosition(new Position(im.getWidth(), im.getHeight()));
			g.drawImage(im, pos.getX(), pos.getY(), dim.getX(), dim.getY(), null);
		}
	}

	/**
	 * Update the bounds every tick, depending on the screen size.
	 */
	private void updateBounds() {
		//walls
		offsetW = (int) (0.05 * GamePanel.WIDTH);
		offsetH = (int) (96/(float)this.image.getHeight() * GamePanel.HEIGHT);
		this.walls.put(Direction.UP, new Rectangle(0, 0, GamePanel.WIDTH, 1));
		this.walls.put(Direction.LEFT, new Rectangle(0, 0, offsetW, GamePanel.HEIGHT));
		this.walls.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH - offsetW, 0, offsetW, GamePanel.HEIGHT));
		this.walls.put(Direction.DOWN, new Rectangle(0, GamePanel.HEIGHT - offsetH, GamePanel.WIDTH, offsetH));
		
		//doors
		doorWidth = GamePanel.WIDTH*64/1920;
		doorHeight = GamePanel.HEIGHT*64/1088;
		this.doors.put(Direction.UP, new Rectangle(GamePanel.WIDTH/2 - doorWidth/2, 0, doorWidth, offsetH));
		this.doors.put(Direction.LEFT, new Rectangle(0, GamePanel.HEIGHT/2 - doorHeight, offsetW, doorHeight));
		this.doors.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH - offsetW, GamePanel.HEIGHT/2 - doorHeight, offsetW, doorHeight));
		this.doors.put(Direction.DOWN, new Rectangle(GamePanel.WIDTH/2 - doorWidth/2, GamePanel.HEIGHT - offsetH, doorWidth, offsetH));
	}

	/**
	 * Give all of the bounds of the walls.
	 * @return the bounds of the walls depending on the direction.
	 */
	public HashMap<Direction, Rectangle> getWallsBounds() {
		return this.walls;
	}
	/**
	 * Give all of the bounds of the doors.
	 * @return the bounds of the doors depending on the direction.
	 */
	public HashMap<Direction, Rectangle> getDoorBounds() {
		return this.doors;
	}
	/**
	 * Give a bound of a wall depending on a given direction.
	 * @param key the direction on what you want to know the bound.
	 * @return the bound corresponding to the given direction.
	 */
	public Rectangle getWallBoundFromKey(Direction key) {
		return this.walls.get(key);
	}
	/**
	 * Give a bound of a door depending on a given direction.
	 * @param key the on what you want to know the bound.
	 * @return the bound corresponding to the given direction.
	 */
	public Rectangle getDoorBoundFromKey(Direction key)	{
		return this.doors.get(key);
	}

}
