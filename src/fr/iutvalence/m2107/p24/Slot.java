package fr.iutvalence.m2107.p24;

import fr.iutvalence.m2107.p24.items.Item;
/**
 * Represent the slots of the inventory.
 */
public class Slot {
	/** The item stored on the slot. */
	private Item item;
	/** The amount of item on the slot. */
	private int quantity;
	
	/** Create a new slot.
	 * @param item The item to put on the slot.
	 * @param quantity The quantity of this item. */
	public Slot(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	
	/**
	 * Get the Item of the slot.
	 * @return the Item of the slot.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Get the quantity of the Item of the slot.
	 * @return the quantity of the Item of the slot.
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Set a new Item on the slot.
	 * @param item the Item to set on the slot.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Set a quantity of the Item of the slot.
	 * @param quantity the amount of item on the slot.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/** Add one to the quantity of the slot. */
	public void add() {
		this.quantity ++;
	}

	/** Remove one to the quantity of the slot. */
	public void remove() {
		this.quantity --;
	}
	
}
