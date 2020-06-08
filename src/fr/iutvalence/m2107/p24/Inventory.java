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
	
	protected Slot[] slots;
	
	public static final int MAX_SLOTS = 10;
	
	/**
	 * Constructor of the inventory.
	 * Initialize the list of items.
	 */
	public Inventory() {
		this.items = new HashMap<Item, Integer>();
		this.selectedSlot = 0;
		this.slots = new Slot[MAX_SLOTS];
	}
	
	/**
	 * Add an item to the list.
	 * @param i the item wanted to add.
	 */
	public void addItem(Item i) {
		//Integer quantity = this.items.get(i);

		boolean pass = false;
		//if (quantity != null) this.items.put(i, quantity.intValue()+1);
		
		for(int j = 0; j < this.slots.length; j++) {
			if(this.slots[j].getItem().equals(i) && this.slots[j].getItem() != null) {
				this.slots[j] = new Slot(i, +1);
				pass = true;
			}
		}
		 
		if(!pass) {
			this.slots[this.getFirstEmpty()] = new Slot(i, 1);
		}
		/*else {
			this.items.put(i, 1);
			this.slots[this.getFirstEmpty()].setItem(i);
			this.slots[this.getFirstEmpty()].setQuantity(quantity.intValue());
		}*/
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
	
	public void KeyPressed(int k, Player p) {
		if (k >= KeyEvent.VK_0 && k <= KeyEvent.VK_9) {
			if (k == KeyEvent.VK_0) this.selectedSlot = 9;
			else this.selectedSlot = k - KeyEvent.VK_1;
		}
		if(k == KeyEvent.VK_ENTER) {
			this.slots[this.selectedSlot].getItem().tick(p);
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
	
	public int getFirstEmpty() {
		int res = 0;
		for(int i = 0; i < this.slots.length; i++) {
			if(this.slots[i] == null) {
				res = i;
			}
		}
		return res;
	}

}
