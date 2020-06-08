package fr.iutvalence.m2107.p24;

import fr.iutvalence.m2107.p24.items.Item;

public class Slot
{
	private Item item;
	private int quantity;
	
	public Slot(Item theItem, int theQuantity) {
		this.item = theItem;
		this.quantity = theQuantity;
	}

	public Item getItem()
	{
		return this.item;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
}
