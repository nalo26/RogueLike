package fr.iutvalence.m2107.p24;

import java.awt.Graphics;

import fr.iutvalence.m2107.p24.ressources.Images;

public class Mob {
	
	/** The default life of the mob. */
	public static final int DEFAULT_HEALTH = 20;
	
	/** The default damage of the mob. */
	public static final int DEFAULT_DAMAGE = 2;
	
	/** The health of the mob. */
	private float health;
	
	/** The damage of the mob. */
	private float damage;
	
	/** The position of the mob. */
	private Position position;
	
	/** The type of the mob. */
	private MobType type;
	
	/** The direction the mob is watching at. */
	private Direction watchingAt;
	
	
	/**
	 * Create a new mob, with its specific attributes.
	 * @param theType of the mob
	 */
	public Mob(MobType theType) {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = Position.randomPosition(GamePanel.WIDTH/2-GamePanel.HEIGHT/2, GamePanel.WIDTH/2+GamePanel.HEIGHT/2, 0, GamePanel.HEIGHT);
		this.type = theType;
		this.watchingAt = Direction.RIGHT;
	}


	public void draw(Graphics g) {
		if (this.type == MobType.SLIME) {
			g.drawImage(Images.SLIME_GREEN_DOWN.getImage(), this.position.getX(), this.position.getY(), null);
		}
		if (this.type == MobType.ZOMBIE) {
			g.drawImage(Images.ZOMBIE_STAY_RIGHT.getImage(), this.position.getX(), this.position.getY(), null);
		}
		if (this.type == MobType.SKELETON) {
			g.drawImage(Images.SKELETON_STAY_RIGHT.getImage(), this.position.getX(), this.position.getY(), null);
		}
	}
	
	/**
	 * Get the health (life) of the mob.
	 * @return the health of the mob (Getter). 
	 */
	public float getHealth() {
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
		return this.watchingAt;
	}

}
