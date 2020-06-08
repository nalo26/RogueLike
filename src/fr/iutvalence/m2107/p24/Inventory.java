package fr.iutvalence.m2107.p24;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.items.Item;


/**
 * Represent the inventory of the player
 */
public class Inventory {
	
	/** List of the player's items. */
	protected HashMap<Item, Integer> items;
	
	protected int selectedSlot;
	
	/**
	 * Constructor of the inventory.
	 * Initialize the list of items.
	 */
	public Inventory() {
		this.items = new HashMap<Item, Integer>();
		this.selectedSlot = 0;
	}
	
	/**
	 * Add an item to the list.
	 * @param i the item wanted to add.
	 */
	public void addItem(Item i) {
		Integer quantity = this.items.get(i);
		if (quantity != null) this.items.put(i, quantity.intValue()+1);
		else this.items.put(i, 1);
	}
	
	/**
	 * Remove an item from the list.
	 * @param i the item wanted to remove.
	 */
	public void removeItem(Item i) {
		Integer quantity = this.items.get(i);
		if (quantity == null) return;
		if (quantity.intValue() == 1) this.items.remove(i);
		else this.items.put(i, quantity.intValue()-1);
	}
	
	public void KeyPressed(int k) {
		if (k >= KeyEvent.VK_0 && k <= KeyEvent.VK_9) {
			if (k == KeyEvent.VK_0) this.selectedSlot = 9;
			else this.selectedSlot = k - KeyEvent.VK_1;
		}
	}
	
	/**
	 * Load the inventory from the save.
	 * @param save the save of the inventory to be restored.
	 */
	public void load(JSONObject save) {
		@SuppressWarnings("unchecked")
		HashMap<Item, Integer> inv = (HashMap<Item, Integer>) save;
		this.items = inv;
	}
	
	/**
	 * Get the list of items of the inventory.
	 * @return the list of items of the inventory. 
	 */
	public HashMap<Item, Integer> getItems(){
		return this.items;
	}

}
