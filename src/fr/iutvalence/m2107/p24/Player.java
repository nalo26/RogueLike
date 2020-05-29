package fr.iutvalence.m2107.p24;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.display.InventoryDisplay;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the player and all of his possible actions (moves, directions, ...).
 */
public class Player {

	/** The default life of the player. */
	public static final int DEFAULT_HEALTH = 20;
	/** The default damage of the player. */
	public static final int DEFAULT_DAMAGE = 2;
	/** The default room coordinates the player is in. */
	public static final Position DEFAULT_ROOM_POSITION = new Position(0, 0);
	/** The direction of the player. */
	protected Direction direction;
	/** The direction the player is watching at. */
	protected Direction watchingAt;
	/** The health of the player. */
	protected HealthDisplay health;
	/** The damage the player deals. */
	protected float damage;
	/** The position of the player. */
	protected Position position;
	/** The room coordinates the player is in. */
	protected Position roomPosition;
	/** The inventory of the player*/
	protected InventoryDisplay inventory;
	/** Handle the direction the player is moving to. */
	protected boolean up;
	protected boolean right;
	protected boolean down;
	protected boolean left;
	
	/** Create a new player. */
	public Player() {
		this.health = new HealthDisplay(DEFAULT_HEALTH);
		this.damage = DEFAULT_DAMAGE;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.roomPosition = DEFAULT_ROOM_POSITION.copy();
		this.inventory = new InventoryDisplay();
	}
	
	/** Describe the behavior of the player after a key is pressed. */
	public void tick() {
		if (this.right) this.position.move(3, 0);
		if (this.left) this.position.move(-3, 0);
		if (this.up) this.position.move(0, -3);
		if (this.down) this.position.move(0, 3);
	}
	
	/**
	 * Describe what to do when a key is pressed.
	 * @param k the key value pressed.
	 */
	public void keyPressed(int k) {
		if (!this.down && (k == 90 || k == 38)) {
			this.up = true;
			this.direction = Direction.UP;
		}
		if (!this.up && (k == 83 || k == 40)) {
			this.down = true;
			this.direction = Direction.DOWN;
		}
		if (!this.left && (k == 68 || k == 39)) {
			this.right = true;
			this.direction = Direction.RIGHT;
			this.watchingAt = Direction.RIGHT;
			this.changeImage(Images.PLAYER_RIGHT);
		}
		if (!this.right && (k == 81 || k == 37)) {
			this.left = true;
			this.direction = Direction.LEFT;
			this.watchingAt = Direction.LEFT;
			this.changeImage(Images.PLAYER_LEFT);
		}
	}
	
	/**
	 * Change the image of the player.
	 * @param img the Image to be change for.
	 */
	protected void changeImage(Images img) {
		// This method is override by PlayerDisplay, which handle images.  
	}
	
	public void collision(Mob m) {
		this.up = false;
		this.right = false;
		this.down = false;
		this.left = false;
		if (m.wantToMove) {
			switch(m.getWatching()) {
				case UP:
					this.position.move(0, -10);
					break;
				case RIGHT:
					this.position.move(10, 0);
					break;
				case DOWN:
					this.position.move(0, 10);
					break;
				case LEFT:
					this.position.move(-10, 0);
					break;
				default: break;
			}
		} else {
			switch(this.direction) {
				case UP:
					this.position.move(0, 10);
					break;
				case RIGHT:
					this.position.move(-10, 0);
					break;
				case DOWN:
					this.position.move(0, -10);
					break;
				case LEFT:
					this.position.move(10, 0);
					break;
				default: break;
			}
		}
	}
	
	/**
	 * Describe what to do when a key is released.
	 * @param k the key value released.
	 */
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
	public HealthDisplay getHealth() {
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
	 * @return the direction the player is watching at (Getter).
	 */
	public Direction getWatching() {
		return this.watchingAt;
	}

	/**
	 * Get the position of the room the player is actually in.
	 * @return the position of the room of the player (Getter).
	 */
	public Position getRoomPosition() {
		return this.roomPosition;
	}

}
