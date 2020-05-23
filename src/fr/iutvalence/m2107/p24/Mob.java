package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	
	/** The direction the mob is walking to. */
	private Direction direction;
	
	/** The image of the mob. */
	private BufferedImage image;

	/** Indicates if the mob is moving or not. */
	private boolean wantToMove;

	/** Indicates the length of the move of the mob. */
	private int lengthOfMove;
	
	
	/**
	 * Create a new mob, with its specific attributes.
	 * @param theType of the mob
	 */
	public Mob(MobType theType) {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = Position.randomPosition(GamePanel.WIDTH/2-GamePanel.HEIGHT/2, GamePanel.WIDTH/2+GamePanel.HEIGHT/2, 0, GamePanel.HEIGHT);
		this.type = theType;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.wantToMove = false;
		this.lengthOfMove = 0;
		updateImage();
	}

	/**
	 * Update the image of the mob considering:
	 * - Its type,
	 * - If it is moving or no,
	 * - The direction it is watching at.
	 */
	private void updateImage() {
		switch(this.type) {
			case SLIME:
				this.image = Images.SLIME_GREEN_DOWN.getImage();
				break;
			case ZOMBIE:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_STAY_LEFT.getImage();
				}
				break;
			case SKELETON:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_STAY_LEFT.getImage();
				}
				break;
			default: break;
		}
	}

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

	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		g.setColor(Color.BLACK);
		g.drawRect(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
		
		g.setColor(Color.WHITE);
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2, this.position.getY()-20, 52, 10);
		g.setColor(Color.BLACK);
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2+1, this.position.getY()-19, 50, 8);
		int red = (int)(255 * (1 - this.health / DEFAULT_HEALTH));
		int green = (int)(255 * (this.health / DEFAULT_HEALTH));
		g.setColor(new Color(red, green, 0));
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2+1, this.position.getY()-19, (int)(50 * (this.health / DEFAULT_HEALTH)), 8);
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
		return this.direction;
	}

}
