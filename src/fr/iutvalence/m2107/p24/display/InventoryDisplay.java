package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Inventory;
import fr.iutvalence.m2107.p24.ressources.Images;

public class InventoryDisplay extends Inventory {

	private BufferedImage inventoryImage;

	public InventoryDisplay() {
		this.inventoryImage = Images.INVENTORY.getImage();
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.inventoryImage, GamePanel.WIDTH /2 - 625 , GamePanel.HEIGHT /2 + 100 ,250, 250, null);
		if(this.items != null) {
			//TODO draw items here
		}
	}
	
}
