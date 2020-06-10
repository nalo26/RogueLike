package fr.iutvalence.m2107.p24;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.items.ItemsList;

/**
 * Represent the inventory of the player.
 */
public class Inventory {
	/** The amount of slot. */
	public static final int MAX_SLOTS = 10;
	/** The current selected slot. */
	protected int selectedSlot;
	/** The slots of the inventory. */
	protected Slot[] slots;
	
	/**
	 * Constructor of the inventory.
	 * Initialize the list of items.
	 */
	public Inventory() {
		this.slots = new Slot[MAX_SLOTS];
		for(int i = 0; i < this.slots.length; i++) {
			this.slots[i] = new Slot(null, 0);
		}
		this.selectedSlot = 0;
	}
	
	/**
	 * Add an item to the list.
	 * @param i the item wanted to add.
	 * @return <tt>true</tt> if the Item has successfully been added to inventory, <tt>false</tt> else.
	 */
	public boolean addItem(Item i) {
		for(int j = 0; j < this.slots.length; j++) { //seek if item already on inventory.
			if(this.slots[j].getItem() != null && this.slots[j].getItem().isSame(i)) {
				this.slots[j].add();
				return true;
			}
		}
		
		int firstSlot = this.getFirstEmpty(); //check if any empty slot in inventory
		if(firstSlot != -1) {
			this.slots[firstSlot] = new Slot(i, 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Remove an item from the inventory.
	 * @param i the item wanted to remove.
	 */
	public void removeItem(int i) {
		this.slots[i].remove();
		if(this.slots[i].getQuantity() <= 0) {
			this.slots[i].setItem(null);
			this.slots[i].setQuantity(0);
		}
	}
	
	public void KeyPressed(int k, Player p) {
		if (k >= KeyEvent.VK_0 && k <= KeyEvent.VK_9) {
			if (k == KeyEvent.VK_0) this.selectedSlot = 9;
			else this.selectedSlot = k - KeyEvent.VK_1;
		}
		if(k == KeyEvent.VK_ENTER && this.slots[this.selectedSlot].getItem() != null) {
			this.slots[this.selectedSlot].getItem().tick(p);
			this.removeItem(this.selectedSlot);
		}
	}
	
	/**
	 * Load the inventory from the save.
	 * @param save the save of the inventory to be restored.
	 */
	@SuppressWarnings("unchecked")
	public void load(JSONObject save) {
		HashMap<String, Long> inventory = (HashMap<String, Long>) save;
		for(HashMap.Entry<String, Long> entry : inventory.entrySet()) {
			int empty = this.getFirstEmpty();
			if(entry.getValue().intValue() > 0) {
				this.slots[empty] = new Slot(ItemsList.valueOf(entry.getKey()).item, entry.getValue().intValue());
			}
		}
	}
	
	/**
	 * Get the list of items of the inventory.
	 * @return the list of items of the inventory. 
	 */
	public Slot[] getItems(){
		return this.slots;
	}
	
	/**
	 * Get the first slot of the inventory without any item.
	 * @return The first slot index with no item, or -1. 
	 */
	public int getFirstEmpty() {
		for(int i = 0; i < this.slots.length; i++) {
			if(this.slots[i].getItem() == null) return i;
		}
		return -1;
	}

}
