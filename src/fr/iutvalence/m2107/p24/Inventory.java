package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;

import fr.iutvalence.m2107.p24.items.Item;


/**
 * Represent the inventory of the player
 *
 */
public class Inventory {
	protected List<Item> items;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
	}
	
	public void addItem(Item i) {
		this.items.add(i);
	}
	
	public void removeItem(Item i) {
		this.items.remove(i);
	}
}
