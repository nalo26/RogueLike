package fr.iutvalence.m2107.p24.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the different characteristics of an item.
 *
 */
public abstract class Item {
	
	/** The image of the item. */
	protected BufferedImage image;
	/** The position of the item. */
	protected Position position;
	
	/**
	 * Constructor.
	 * @param pos The position of the item on a room.
	 * @param im The image of the Item
	 */
	public Item(Position pos, Images im) {
		this.position = pos;
		this.image = im.getImage();
		
	}
	
	public Position getPos() {
		return this.position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Position(x,y);
	}

	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
	}
	
	public abstract void tick(Player p);
	
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
	}
}
