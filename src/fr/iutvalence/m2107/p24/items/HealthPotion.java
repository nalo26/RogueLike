package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the Health potion.
 */
public class HealthPotion {

	public HealthPotion(ItemsList type, int id, int prob, Position pos) {
		this.changeImage(Images.HEALTHPOTION);
	}

	public void heal() {
		 return; // TODO heal the player
	}

	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}

	public void changeImage(Images i) {
		return;
	}

}
