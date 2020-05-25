package fr.iutvalence.m2107.p24.items;

import java.awt.Graphics;

public abstract class Item
{
	protected String name;
	protected int id;
	
	public Item(String theName, int theId)
	{
		this.name = theName;
		this.id = theId;
	}
	
	public abstract void tick();
	public abstract void draw(Graphics g);
}
