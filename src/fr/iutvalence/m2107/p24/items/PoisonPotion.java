package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;
/**
 * Represent the Poison potion.
 */
public class PoisonPotion extends Item {

	/** The probability of spawn. */
	public static final float PROBABILITY = 0.01F;
	/** The damage taken when you take one. */
	public static final int DAMAGE_TAKE = 2;
	/** The number of additional damages per stroke. */
	public static final int DAMAGE_DEAL = +2;
	
	/** 
	 * Create a new Poison potion.
	 */
	public PoisonPotion() {
		super(Images.POTION_POISON);
	}

	/**
	 * Poison the player by remove him some life and adding him some damage per stroke.
	 * @param p the player to poison and add some damage per stroke.
	 */
	@Override
	public void tick(Player p) {
		p.takeDamage((float)DAMAGE_TAKE);
		p.setDamage(p.getDamage() + DAMAGE_DEAL);
	}

}
