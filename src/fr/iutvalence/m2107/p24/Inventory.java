package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;

import fr.iutvalence.m2107.p24.items.Item;


/**
 * Represent the inventory of the player
 */
public class Inventory {
	
	/** List of the player's items. */
	protected List<Item> items;
	
	/**
	 * Constructor of the inventory.
	 * Initialize the list of items.
	 */
	public Inventory() {
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * Add an item to the list.
	 * @param i the item wanted to add.
	 */
	public void addItem(Item i) {
		this.items.add(i);
	}
	
	/**
	 * Remove an item from the list.
	 * @param i the item wanted to remove.
	 */
	public void removeItem(Item i) {
		this.items.remove(i);
	}
}
