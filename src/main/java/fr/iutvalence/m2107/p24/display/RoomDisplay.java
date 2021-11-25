package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;
import fr.iutvalence.m2107.p24.rooms.Room;

/**
 * Display a room with it's correspondent image.
 */
public class RoomDisplay extends Room {

	/** The image of the room. */
	private BufferedImage image;
	
	/**
	 * Create a room ready to be displayed.
	 * @param p The position of the Room on the map.
	 */
	public RoomDisplay(Position p) {
		super(p);
	}
	
	/**
	 * Create a room ready to be displayed, with a specific configuration.
	 * @param p The position of the Room on the map.
	 * @param config The doors' configuration.
	 * @see #getDoors()
	 */
	public RoomDisplay(Position p, String config) {
		super(p, config);
		this.image = Images.valueOf("ROOM"+Integer.parseInt(config, 2)).getImage();
	}
	
	/** {@inheritDoc} */
	@Override
	protected void generateDecorElement() {
		Images im = Images.valueOf("TREE" + (new Random().nextInt(4)+1));
		
		Position pos = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		Rectangle rect = new Rectangle(pos.getX(), pos.getY(), im.getImage().getWidth(), im.getImage().getHeight());
		while(!MiniMapDisplay.canBeCreatedAt(rect) || this.isOverlap(rect)) {
			pos = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			rect = new Rectangle(pos.getX(), pos.getY(), im.getImage().getWidth(), im.getImage().getHeight());
		}
		this.decoration.put(pos, im);
	}
	
	/**
	 * Check if an element of the decor is overlapping on another one.
	 * @param r the rectangle on what you want to test with.
	 * @return true if overlapping otherwise false.
	 */
	private boolean isOverlap(Rectangle r) {
		for(HashMap.Entry<Position, Images> entry : this.decoration.entrySet()) {
			Position p = entry.getKey();
			BufferedImage m = entry.getValue().getImage();
			if(r.intersects(new Rectangle(p.getX(), p.getY(), m.getWidth(), m.getHeight()))) return true;
		}
		return false;
	}

	/**
	 * Draw the room.
	 * @param g the draw component
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		for(Item i : this.items) {
			i.draw(g);
		}
		
		for(HashMap.Entry<Position, Images> entry : this.decoration.entrySet()) {
			Position pos = entry.getKey();
			BufferedImage im = entry.getValue().getImage();
			Position dim = new Position(im.getWidth(), im.getHeight());
			g.drawImage(im, pos.getX(), pos.getY(), dim.getX(), dim.getY(), null);
		}
		
		for(Mob m : this.mobs) {
			m.draw(g);
		}
	}
	
	/**
	 * Set the image who's going to be draw.
	 */
	@Override
	public void setImage() {
		this.image = Images.valueOf("ROOM"+Integer.parseInt(this.getDoorsString(), 2)).getImage();
	}

}
