package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

public class SpeedPotion extends Item {

	public SpeedPotion(ItemsList type, int id, int prob, Position pos) {
		super(type, id, prob, pos);
		this.changeImage(Images.SPEEDPOTION);
	}

	public void addSpeed() {
		 return; // TODO speed up the player
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}

	@Override
	public void changeImage(Images i) {
		return;
	}

}
