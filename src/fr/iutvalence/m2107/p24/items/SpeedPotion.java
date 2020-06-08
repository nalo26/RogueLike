package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;

public class SpeedPotion extends Item {

	/** The probability of spawn. */
	public static final float PROBABILITY = 0.01F;
	
	/** The amount of speed to add to the player when used. */
	private static final int SPEED_POTION = +3;
	/** The duration of the speed effect. */
	private static final int SPEED_TIME = 500;
	/** The counter of the duration of the speed effect. */
	private int counter;
	
	/** 
	 * Create a new Speed potion.
	 */
	public SpeedPotion() {
		super(Images.POTION_SPEED);
		this.counter = 0;
	}

	@Override
	public void tick(Player p) {
		this.counter = SPEED_TIME;
		if(this.counter > 0) {
			p.setSpeed(Player.DEFAULT_SPEED + SPEED_POTION);
			this.counter--;
		}	
	}
	
}
