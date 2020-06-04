package fr.iutvalence.m2107.p24.items;
/**
 * Represent all of the possible items in the game.
 *
 */
public enum ItemsList {
	HealthPotion("1"),
	PoisonPotion("2"),
	SpeedPotion("3");
	
	private String item;
	
	private ItemsList(String theitem)	{
		this.item = theitem;
	}
	
}
