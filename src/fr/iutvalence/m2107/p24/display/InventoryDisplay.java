package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Inventory;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * display the inventory with an image
 *
 */
public class InventoryDisplay extends Inventory {

	/** The default image of the inventory. */
	private BufferedImage inventoryImage;
	/** The transparent image of  the inventory (when player under it). */
	private BufferedImage transparentInventoryImage;
	
	private static final int OFFSET = 15;
	private static final int SIZE = 250;
	
	/**
	 * Initialize the inventory image to draw it later.
	 */
	public InventoryDisplay() {
		this.inventoryImage = Images.INVENTORY.getImage();
		this.transparentInventoryImage = Images.INVENTORY_TRANSPARENT.getImage();
	}

	/**
	 * Draw the inventory.
	 * @param g the draw component.
	 * @param p the player of the game.
	 */
	public void draw(Graphics g, PlayerDisplay p) {
		if(p.getBounds().intersects(new Rectangle(OFFSET, GamePanel.HEIGHT - SIZE - OFFSET, SIZE, SIZE))) {
			g.drawImage(this.transparentInventoryImage,  OFFSET, GamePanel.HEIGHT - SIZE - OFFSET, SIZE, SIZE, null);
		} else g.drawImage(this.inventoryImage, OFFSET, GamePanel.HEIGHT - SIZE - OFFSET, SIZE, SIZE, null);
		
		if (this.items != null) {
			// TODO draw items here
		}
	}

}
