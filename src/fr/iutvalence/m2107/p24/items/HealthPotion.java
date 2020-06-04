package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the Health potion.
 */
public class HealthPotion extends Item{

	public HealthPotion(int prob, Position pos) {
		super(prob, pos, Images.HEALTHPOTION);
	}

	public void heal() {
		 return; // TODO heal the player
	}

}
