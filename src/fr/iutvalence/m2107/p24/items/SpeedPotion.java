package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

public class SpeedPotion extends Item {

	/** The probability of spawn. */
	public static final float PROBABILITY = 0.1F;
	
	/** The amount of speed to add to the player when used. */
	private static final int SPEED_POTION = +3;
	/** The duration of the speed effect. */
	private static final int SPEED_TIME = 500;
	/** The counter of the duration of the speed effect. */
	private int counter;
	
	/** 
	 * Create a new Speed potion.
	 * @param pos The position of the item.
	 */
	public SpeedPotion(Position pos) {
		super(pos, Images.POTION_SPEED);
		this.counter = 0;
	}

	@Override
	public void tick(Player p) {
		if(this.counter > 0) {
			p.setSpeed(Player.DEFAULT_SPEED + SPEED_POTION);
			this.counter--;
		}	
	}
	
	public void use() {
		this.counter = SPEED_TIME;
	}

}
