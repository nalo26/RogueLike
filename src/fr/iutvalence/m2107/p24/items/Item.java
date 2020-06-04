package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.Player;

/**
 * Represent the different characteristics of an item.
 *
 */
public abstract class Item {
	
	/** The name of the item. */
	protected String name;
	/** The id of the item. */
	protected int id;
	/** The spawn probability of the item. */
	protected int probability;
	
	protected Player player;
	/**
	 * Constructor.
	 * @param theName name of the item.
	 * @param theId id of the item.
	 */
	public Item(String theName, int theId, int theprobability, Player thePlayer) {
		this.name = theName;
		this.id = theId;
		this.probability = theprobability;
		this.player = thePlayer;
	}

	public Player getPlayer()
	{
		return this.player;
	}
	
	public int getProbabilty()	{
		return this.probability;
	}
	
	public int getId() {
		return this.id;
	}
}
