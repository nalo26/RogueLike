package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the Health potion.
 */
public class HealthPotion extends Item {

	/** The probability of spawn. */
	public static final float PROBABILITY = 0.01F;
	
	/** The life the potion adds. */
	public static final int HEALTH_BOOST = 2;
	
	
	public HealthPotion() {
		super(Images.POTION_HEALTH);
	}
	
	/**
	 * Heal the player by adding him some life.
	 * @param p the player to heal.
	 */
	@Override
	public void tick(Player p) {
		p.getHealth().addLife(HEALTH_BOOST);
	}

}
