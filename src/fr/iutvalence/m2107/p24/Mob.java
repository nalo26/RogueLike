package fr.iutvalence.m2107.p24;

import java.util.Random;

import fr.iutvalence.m2107.p24.display.HealthDisplay;

/**
 * Represent a mob with all of his characteristics (health, damage, type, direction, ...).
 */
public class Mob {
	
	/** The default life of the mob. */
	public static final int DEFAULT_HEALTH = 20;
	/** The default damage of the mob. */
	public static final int DEFAULT_DAMAGE = 2;
	/** The health of the mob. */
	protected HealthDisplay health;
	/** The damage of the mob. */
	protected float damage;
	/** The position of the mob. */
	protected Position position;
	/** The type of the mob. */
	protected MobType type;
	/** The direction the mob is watching at. */
	protected Direction watchingAt;
	/** The direction the mob is walking to. */
	protected Direction direction;
	/** Indicates if the mob is moving or not. */
	protected boolean wantToMove;
	/** Indicates the length of the move of the mob. */
	protected int lengthOfMove;
	
	/**
	 * Create a new mob, with its specific attributes.
	 * @param theType of the mob
	 */
	public Mob(MobType theType) {
		this.health = new HealthDisplay(DEFAULT_HEALTH);
		this.damage = DEFAULT_DAMAGE;
		this.position = Position.randomPosition(GamePanel.WIDTH/2-GamePanel.HEIGHT/2, GamePanel.WIDTH/2+GamePanel.HEIGHT/2, 0, GamePanel.HEIGHT);
		this.type = theType;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.wantToMove = false;
		this.lengthOfMove = 0;
	}
	
	/** Describe the behavior of a mob every tick. */
	public void tick() {
		if (this.wantToMove) {
			if (this.direction == Direction.RIGHT) this.position.move(1, 0);
			if (this.direction == Direction.LEFT)  this.position.move(-1, 0);
			if (this.direction == Direction.DOWN)  this.position.move(0, -1);
			if (this.direction == Direction.UP)    this.position.move(0, 1);
			
			this.lengthOfMove -= 1;
			if(this.lengthOfMove == 0) this.wantToMove = false;
		} else {
			if (new Random().nextInt(150) == 0) {
				this.direction = Direction.randomDirection();
				if(this.direction == Direction.RIGHT || this.direction == Direction.LEFT) this.watchingAt = this.direction;
				this.lengthOfMove = new Random().nextInt(100) + 20;
				this.wantToMove = true;
			}
		}
		updateImage();
	}
	
	/**
	 * Update the image of the mob considering:
	 * - Its type,
	 * - If it is moving or no,
	 * - The direction it is watching at.
	 */
	protected void updateImage() {
		// Override by sub MobDisplay, which handle images.		
	}

	/**
	 * Get the health (life) of the mob.
	 * @return the health of the mob (Getter). 
	 */
	public HealthDisplay getHealth() {
		return this.health;
	}
	
	/**
	 * Get the damage the mob deals.
	 * @return the damage of the mob (Getter). 
	 */
	public float getDamage() {
		return this.damage;
	}
	
	/**
	 * Get the actual position of the mob.
	 * @return the position of the mob (Getter). 
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Get the type of the mob.
	 * @return the type of the mob (Getter). 
	 */
	public MobType getType() {
		return this.type;
	}
	
	/**
	 * Get the direction the mob is watching at.
	 * @return the direction the mob is watching at (Getter). 
	 */
	public Direction getWatching() {
		return this.direction;
	}

}
