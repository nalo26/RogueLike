package fr.iutvalence.m2107.p24.display;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.HashMap;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Inventory;
import fr.iutvalence.m2107.p24.items.Item;
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
	/** The offset of the inventory display. */
	private static final int OFFSET = 15;
	/** The size of the inventory display. */
	private static final int WIDTH = 250;
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
		
		int slot = 0;
		int x = 30;
		int y = GamePanel.HEIGHT - 100;
		for(HashMap.Entry<Item, Integer> i : this.items.entrySet()){
			Item item = i.getKey();
			String quantity = String.valueOf(i.getValue());
			if(slot == 5) {
				x = 30;
				y = GamePanel.HEIGHT - 50;		
			}
			g.drawImage(item.getImage(), x, y, item.getImage().getWidth()/4, item.getImage().getHeight()/4, null);
			g.setFont(new Font("Montserrat", 0, 20));
			g.setColor(new Color(0, 230, 11));
			g.drawString(quantity, x + 20, y + 30);
			if(slot == this.selectedSlot) g.drawRect(x, y, item.getImage().getWidth()/4, item.getImage().getHeight()/4);
			x += 50;
			slot++;
		}
	}



}
