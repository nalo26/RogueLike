package fr.iutvalence.m2107.p24.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
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
	
	public Item(Images im) {
		this.image = im.getImage();
		this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		Rectangle rect = new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
		while(!MiniMapDisplay.canBeCreatedAt(rect)) {
			this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			rect = new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
		}
	}
	
	public Position getPos() {
		return this.position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Position(x,y);
	}

	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), this.image.getWidth()/2, this.image.getHeight()/2, null);
	}
	
	public abstract void tick(Player p);
	
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth()/2, this.image.getHeight()/2);
	}

	public BufferedImage getImage()
	{
		return this.image;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.image == null) ? 0 : this.image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (this.image == null)
		{
			if (other.image != null)
				return false;
		} else if (!this.image.equals(other.image))
			return false;
		return true;
	}
	
}
