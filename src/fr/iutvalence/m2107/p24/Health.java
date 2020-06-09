package fr.iutvalence.m2107.p24;
/**
 * Represent the health of the entity.
 */
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
	
	
	/**
	 * Set the life to <tt>h</tt>.
	 * @param h the life amount.
	 */
	public void setHealth(float h) {
		this.life = h;
	}
	
	/**
	 * Remove <tt>h</tt> life.
	 * @param h the life amount.
	 */
	public void removeLife(float h) {
		this.life -= h;
		if(this.life < 0) this.life = 0;
	}
	
	/**
	 * Add <tt>h</tt> life.
	 * @param h the life amount.
	 */
	public void addLife(float h) {
		this.life += h;
		if(this.life > this.defaultLife) this.life = this.defaultLife;
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
