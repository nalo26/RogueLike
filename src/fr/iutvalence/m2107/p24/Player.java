package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.ressources.Images;

public class Player {

	/** The default life of the player. */
	public static final int DEFAULT_HEALTH = 20;
	
	/** The default damage of the player. */
	public static final int DEFAULT_DAMAGE = 2;
	
	public static final BufferedImage DEFAULT_IMAGE = Images.PLAYER_RIGHT.getImage();
	
	/** The default position of the player. */
	public static final Position DEFAULT_POSITON = new Position(GamePanel.WIDTH/2 - (DEFAULT_IMAGE.getWidth()/2), GamePanel.HEIGHT/2 - (DEFAULT_IMAGE.getHeight()/2));
	
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
	
	/** The image of the player. */
	private BufferedImage image;
	
	private boolean up;
	private boolean right;
	private boolean down;
	private boolean left;
	
	/** Create a new player. */
	public Player() {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.image = DEFAULT_IMAGE;
	}
	
	public void tick() {
		if (this.right) this.position.move(3, 0);
		if (this.left) this.position.move(-3, 0);
		if (this.up) this.position.move(0, -3);
		if (this.down) this.position.move(0, 3);
		
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
	
	public void keyPressed(int k) {
		if (k == 90 || k == 38) {
			this.up = true;
			this.direction = Direction.UP;
		}
		if (k == 83 || k == 40) {
			this.down = true;
			this.direction = Direction.DOWN;
		}
		if (k == 68 || k == 39) {
			this.right = true;
			this.direction = Direction.RIGHT;
			this.watchingAt = Direction.RIGHT;
			this.image = Images.PLAYER_RIGHT.getImage();
		}
		if (k == 81 || k == 37) {
			this.left = true;
			this.direction = Direction.LEFT;
			this.watchingAt = Direction.LEFT;
			this.image = Images.PLAYER_LEFT.getImage();
		}
	}
	
	public void keyReleased(int k) {
		if (k == 90 || k == 38) this.up = false;
		if (k == 83 || k == 40) this.down = false;
		if (k == 68 || k == 39) this.right = false;
		if (k == 81 || k == 37) this.left = false;
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
