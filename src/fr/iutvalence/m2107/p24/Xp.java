package fr.iutvalence.m2107.p24;
/**
 * Represents the XP of the player.
 */
public class Xp {
	
	/** The actual xp. */
	protected float xp;
	
	/**
	 * Constructor, set a new XP, and initializes LvlUp on false.
	 * @param theXp the actual XP of the player.
	 */
	public Xp(float theXp) {
		this.xp = theXp;
	}
	
	/**
	 * Add <tt>xp</tt> of XP.
	 * @param xp the xp amount.
	 */
	public void addXp(float xp) {
		this.xp += xp;
	}
	
	/**
	 * @return the current xp.
	 */
	public float getXp() {
		return this.xp;
	}
	
}
