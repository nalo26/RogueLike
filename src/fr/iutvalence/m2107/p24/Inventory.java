package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;


/**
 * Represent the inventory of the player
 *
 */
public class Inventory
{
	private ArrayList<Item> items;
	
	private BufferedImage inventoryImage;
	
	public Inventory()
	{
		this.items = null;
		this.inventoryImage = Images.INVENTORY.getImage();
	}
	
	public void addItem(Item i)
	{
		this.items.add(i);
	}
	
	public void removeItem(Item i)
	{
		this.items.remove(i);
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(this.inventoryImage, GamePanel.WIDTH /2 - 625 , GamePanel.HEIGHT /2 + 100 ,250, 250, null);
		if(this.items != null)
		{
			//TODO draw items here
		}
	}
}
