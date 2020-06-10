package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;
/**
 * Represents the key for open the boss room.
 */
public class Key extends Item {
	/** 
	 * Create a new key.
	 */
	public Key() {
		super(Images.KEY);
	}

	/** {@inheritDoc} */
	@Override
	public void tick(Player p) {
		//not use here.
	}

}
