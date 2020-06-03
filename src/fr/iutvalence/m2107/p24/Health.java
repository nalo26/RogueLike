package fr.iutvalence.m2107.p24;

public class Health {

	/** The default life. Will never change. */
	protected final float defaultLife;
	/** The current life, changing. */
	protected float life;
	
	/**
	 * Create a new health.
	 * @param defaultLife the default life of this life.
	 */
	public Health(float defaultLife) {
		this.defaultLife = defaultLife;
		this.life = defaultLife;
	}
	
	public void setHealth(float h) {
		this.life = h;
	}
	
	public void removeLife(float h) {
		this.life -= h;
	}
	
	/**
	 * Get the current life.
	 * @return the current life.
	 */
	public float getLife() {
		return this.life;
	}
	
	/**
	 * Get the default life.
	 * @return the default life.
	 */
	public float getDefault() {
		return this.defaultLife;
	}
}
