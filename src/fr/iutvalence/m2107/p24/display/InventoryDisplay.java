package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Inventory;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Display the inventory with an image.
 */
public class InventoryDisplay extends Inventory {

	/** The default image of the inventory. */
	private BufferedImage inventoryImage;
	/** The transparent image of  the inventory (when player under it). */
	private BufferedImage transparentInventoryImage;
	/** The offset of the inventory display. */
	private static final int OFFSET = 15;
	/** The width of the inventory display. */
	private static final int WIDTH = 250;
	/** The height of the inventory display. */
	private static final int HEIGHT = 119;
	
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
		if(p.getBounds().intersects(new Rectangle(OFFSET, GamePanel.HEIGHT - HEIGHT - OFFSET, WIDTH, HEIGHT))) {
			g.drawImage(this.transparentInventoryImage,  OFFSET, GamePanel.HEIGHT - HEIGHT - OFFSET, WIDTH, HEIGHT, null);
		} else g.drawImage(this.inventoryImage, OFFSET, GamePanel.HEIGHT - HEIGHT - OFFSET, WIDTH, HEIGHT, null);
		
		g.setFont(new Font("Montserrat", 0, 20));
		g.setColor(Color.BLACK);
		
		int x = 30;
		int y = GamePanel.HEIGHT - 92;
		for(int s = 0; s < this.slots.length; s++){
			Item item = this.slots[s].getItem();
			int quantity = this.slots[s].getQuantity();
			if(s == 5) {
				x = 30;
				y += 47;		
			}
			if(item != null) {
				g.drawImage(item.getImageDisplay(), x, y, 30, 30, null);
				g.drawString(""+quantity, x + 20, y + 30);
			}
			if(s == this.selectedSlot) {
				g.drawRect(x, y, 35, 26);
				g.drawRect(x-1, y-1, 37, 28);
			}
			x += 46;
		}
	}



}
