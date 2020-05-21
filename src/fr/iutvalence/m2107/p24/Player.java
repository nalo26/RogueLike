package fr.iutvalence.m2107.p24;

public class Player {

	/** The default life of the player. */
	public static final int DEFAULT_HEALTH = 20;
	
	/** The default damage of the player. */
	public static final int DEFAULT_DAMAGE = 2;
	
	/** The default position of the player. */
	public static final Position DEFAULT_POSITON = new Position(0, 0);
	
	/** The direction of the player. */
	private Direction direction;
	
	/** The direction the player is watching at. */
	private Direction watchingAt;
	
	/** The health of the player. */
	private float health;
	
	/** The damage of the player. */
	private float damage;
	
	/** The position of the player. */
	private Position position;
	
	/** Constructor of Player, set the health, the damage, and the position. */
	public Player() {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
	}
	
	/**
	 * Get the health (life) of the player.
	 * @return the health of the player (Getter).
	 */
	public float getHealth() {
		return this.health;
	}
	
	/**
	 * Get the damage the player deals.
	 * @return the damage of the player (Getter).
	 */
	public float getDamage() {
		return this.damage;
	}
	
	/**
	 * Get the actual position of the player.
	 * @return the position of the player (Getter).
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Get the actual direction of the player.
	 * @return the direction of the player (Getter).
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Get the actual direction the player is watching at.
	 * @return the direction the player is watching at(Getter).
	 */
	public Direction getWatching() {
		return this.watchingAt;
	}


}
