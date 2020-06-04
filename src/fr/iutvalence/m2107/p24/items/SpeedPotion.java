package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

public class SpeedPotion extends Item {

	public SpeedPotion(int prob, Position pos) {
		super(prob, pos, Images.SPEEDPOTION);
	}

	public void addSpeed() {
		 return; // TODO speed up the player
	}

}
