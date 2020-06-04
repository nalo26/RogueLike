package fr.iutvalence.m2107.p24.items;

import java.awt.Graphics;

import fr.iutvalence.m2107.p24.Position;

/**
 * Represent all of the possible items in the game.
 *
 */
public enum ItemsList {
	HealthPotion(new HealthPotion(50, new Position(500, 500)));
	//PoisonPotion("2"),
	//SpeedPotion("3");

	private Object item;
	
	private ItemsList(Item item)	{
		this.item = item;
	}
	
	public void draw(Graphics g) {
		((Item) item).draw(g);
	}
	
}
