package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
	
	/** The image of the mob. */
	private BufferedImage image;
	
	
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
		switch(this.type) {
			case SLIME:
				this.image = Images.SLIME_GREEN_DOWN.getImage();
				break;
			case ZOMBIE:
				this.image = Images.ZOMBIE_STAY_RIGHT.getImage();
				break;
			case SKELETON:
				this.image = Images.SKELETON_STAY_RIGHT.getImage();
				break;
			default: break;
		}
	}


	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		g.drawRect(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
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
