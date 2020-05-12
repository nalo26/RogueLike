package fr.iutvalence.m2107.p24;

public class Player {

	/**
	 * The default life of the player
	 */
	public static final int DEFAULT_HEALTH = 20;
	
	/**
	 * The default damage of the player
	 */
	public static final int DEFAULT_DAMAGE = 2;
	
	/**
	 * The default position of the player
	 */
	public static final Position DEFAULT_POSITON = new Position(0, 0); // TODO center of the room
	
	/**
	 * the health of the player
	 */
	private float health;
	
	/**
	 * the damage of the player
	 */
	private float damage;
	
	/**
	 * the position of the player
	 */
	private Position position;
	
	/**
	 * Constructor of Player, set the health, the damage, and the position.
	 */
	public Player() {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
	}
	
	/**
	 * @return the health of the player (Getter)
	 */
	public float getHealth() {
		return this.health;
	}
	
	/**
	 * @return the damage of the player (Getter)
	 */
	public float getDamage() {
		return this.damage;
	}
	
	/**
	 * @return the position of the player (Getter)
	 */
	public Position getPosition() {
		return this.position;
	}

}
