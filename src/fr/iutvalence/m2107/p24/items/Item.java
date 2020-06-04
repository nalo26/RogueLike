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
	
	protected BufferedImage image;
	
	/** The spawn probability of the item. */
	protected int probability;
	/** The position of the item. */
	protected Position position;
	/**
	 * Constructor.
	 * @param type name of the item.
	 * @param id id of the item.
	 * @param prob the probability of spawn of the item.
	 * @param pos the position of the item on a room.
	 */
	public Item(int prob, Position pos, Images im) {
		this.probability = prob;
		this.position = pos;
		this.image = im.getImage();
		
	}
	
	public int getProbabilty()	{
		return this.probability;
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
