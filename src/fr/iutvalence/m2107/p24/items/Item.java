package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;

import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;

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
	/** The concerned player. */
	protected Player player;
	/** The position of the item. */
	protected Position pos;
	/**
	 * Constructor.
	 * @param theName name of the item.
	 * @param theId id of the item.
	 */
	public Item(String theName, int theId, int theprobability, Player thePlayer, Position thePos) {
		this.name = theName;
		this.id = theId;
		this.probability = theprobability;
		this.player = thePlayer;
		this.pos = thePos;
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
	
	public Position getPos() {
		return this.pos;
	}
	
	public abstract Rectangle getBounds();
	
	public void setPosition(int x, int y) {
		this.pos = new Position(x,y);
	}
}
