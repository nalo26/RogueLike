package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;

public class PoisonPotion extends Item {

	public static final float PROBABILITY = 0.01F;
	
	public static final int DAMAGE_TAKE = 2;
	public static final int DAMAGE_DEAL = +2;
	
	public PoisonPotion() {
		super(Images.POTION_POISON);
	}

	@Override
	public void tick(Player p) {
		p.takeDamage((float)DAMAGE_TAKE);
		p.setDamage(p.getDamage() + DAMAGE_DEAL);
	}

}
