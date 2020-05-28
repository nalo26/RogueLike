package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Inventory;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * display the inventory with an image
 *
 */
public class InventoryDisplay extends Inventory
{

	/** the image of the inventory */
	private BufferedImage inventoryImage;

	/**
	 * initialize the inventory image to draw it after
	 */
	public InventoryDisplay()
	{
		this.inventoryImage = Images.INVENTORY.getImage();
	}

	/**
	 * draw the inventory
	 * @param g the draw component
	 */
	public void draw(Graphics g)
	{
		g.drawImage(this.inventoryImage, 15, GamePanel.HEIGHT - 15 - 250, 250, 250, null);
		if (this.items != null)
		{
			// TODO draw items here
		}
	}

}
