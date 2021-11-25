package fr.iutvalence.m2107.p24.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the different characteristics of an item.
 *
 */
public abstract class Item {
	
	/** The image of the item. */
	protected BufferedImage imageDisplay;
	/** The image of the item. */
	protected Images image;
	/** The position of the item. */
	protected Position position;
	
	/**
	 * Constructor, draw the item on <tt>pos</tt>.
	 * @param pos The position of the item on a room.
	 * @param im The image of the Item
	 */
	public Item(Position pos, Images im) {
		this.position = pos;
		this.image = im;
		this.imageDisplay = im.getImage();
		
	}
	
	/**
	 * Constructor, draw the item on a random position.
	 * @param im The image of the Item
	 */
	public Item(Images im) {
		this.image = im;
		this.imageDisplay = im.getImage();
		this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		Rectangle rect = new Rectangle(this.position.getX(), this.position.getY(), this.imageDisplay.getWidth(), this.imageDisplay.getHeight());
		while(!MiniMapDisplay.canBeCreatedAt(rect)) {
			this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			rect = new Rectangle(this.position.getX(), this.position.getY(), this.imageDisplay.getWidth(), this.imageDisplay.getHeight());
		}
	}
	
	/**
	 * Give the position of the item.
	 * @return the item position.
	 */
	public Position getPos() {
		return this.position;
	}
	
	/**
	 * Set the position of the Item.
	 * @param x the x Position.
	 * @param y the y Position.
	 */
	public void setPosition(int x, int y) {
		this.position = new Position(x,y);
	}
	
	/**
	 * Draw the item.
	 * @param g the draw component. 
	 */
	public void draw(Graphics g) {
		g.drawImage(this.imageDisplay, this.position.getX(), this.position.getY(), this.imageDisplay.getWidth()/2, this.imageDisplay.getHeight()/2, null);
	}
	
	public abstract void tick(Player p);
	
	/**
	 * Give the bounds of the item.
	 * @return a Rectangle who represent the bounds of the item.
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.imageDisplay.getWidth(), this.imageDisplay.getHeight());
	}
	
	/**
	 * @return The image display of the item.
	 */
	public BufferedImage getImageDisplay() {
		return this.imageDisplay;
	}
	/**
	 * @return the Image item.
	 */
	public Images getImage() {
		return this.image;
	}
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.image == null) ? 0 : this.image.hashCode());
		result = prime * result + ((this.position == null) ? 0 : this.position.hashCode());
		return result;
	}
	/**
	 * Check if two items are the same in the room.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Item other = (Item) obj;
		if (this.image != other.image) return false;
		if (this.position == null) {
			if (other.position != null) return false;
		} else if (!this.position.equals(other.position)) return false;
		return true;
	}
	/**
	 * @param i the item to check with our item.
	 * @return if the images are the same in the inventory.
	 */
	public boolean isSame(Item i) {
		if(this == i) return true;
		if(i == null) return false;
		if(this.image != i.image) return false;
		return true;
	}
	
	
}
