package fr.iutvalence.m2107.p24.items;
/**
 * Represent the different characteristics of an item.
 *
 */
public abstract class Item {
	
	/** The name of the item. */
	protected String name;
	
	/** The id of the item. */
	protected int id;
	
	/**
	 * Constructor.
	 * @param theName name of the item.
	 * @param theId id of the item.
	 */
	public Item(String theName, int theId) {
		this.name = theName;
		this.id = theId;
	}
	


}
