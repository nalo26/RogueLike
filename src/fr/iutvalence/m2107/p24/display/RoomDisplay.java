package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;
import fr.iutvalence.m2107.p24.rooms.Room;

/**
 * Display a room with its correspondent image.
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
		//this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2)).getImage();
	}
	
	/**
	 * Create a room ready to be displayed, with a specific configuration.
	 * @param p The position of the Room on the map.
	 * @param config The doors' configuration.
	 * @see #getDoors()
	 */
	public RoomDisplay(Position p, String config) {
		super(p, config);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void generateDecorElement() {
		Images im = Images.valueOf("TREE" + (new Random().nextInt(4)+1));
		
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
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		for(Item i : this.items) {
			i.draw(g);
		}
		
		for(Mob m : this.mobs) {
			m.draw(g);
		}
		
		for(HashMap.Entry<Position, Images> entry : this.decor.entrySet()) {
			Position pos = World.updatePosition(entry.getKey());
			BufferedImage im = entry.getValue().getImage();
			Position dim = World.updatePosition(new Position(im.getWidth(), im.getHeight()));
			g.drawImage(im, pos.getX(), pos.getY(), dim.getX(), dim.getY(), null);
		}
	}
	
	@Override
	public void setImage() {
		this.image = Images.valueOf("ROOM"+Integer.parseInt(this.getDoorsString(), 2)).getImage();
	}

}
