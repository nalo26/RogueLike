package fr.iutvalence.m2107.p24;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.display.InventoryDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
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
	/** The default speed of the player (pixels per tick). */
	public static final int DEFAULT_SPEED = 3;
	/** The sprint speed of the player (pixels per tick). */
	public static final int SPRINT_SPEED = 5;
	
	/** The speed of the player (pixels per tick)*/
	protected int speed;
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
	/** Handle the direction the player is moving to. */
	protected boolean right;
	/** Handle the direction the player is moving to. */
	protected boolean down;
	/** Handle the direction the player is moving to. */
	protected boolean left;
	
	/** The timer for the damage animation. */
	protected int dmgTimer;
	/** True if the player is taking damages, false if not. */
	protected boolean takingDmg;
	/** True if the player is fighting, false if not.*/
	protected boolean isFighting;
	
	protected boolean minorAttack;
	
	protected boolean majorAttack;
	
	/** Create a new player. */
	public Player() {
		this.speed = DEFAULT_SPEED;
		this.health = new HealthDisplay(DEFAULT_HEALTH);
		this.damage = DEFAULT_DAMAGE;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.roomPosition = DEFAULT_ROOM_POSITION.copy();
		this.inventory = new InventoryDisplay();
		this.dmgTimer = 0;
		this.takingDmg = false;
		this.isFighting = false;
		this.minorAttack = false;
		this.majorAttack = false;
	}
	
	/** Describe the behavior of the player after a key is pressed. 
	 * @param r the current room the player interacts with. */
	public void tick(RoomDisplay r) {
		if (this.right && !this.getBounds().intersects(r.getWallBoundFromKey(Direction.RIGHT))) this.position.move( this.speed,  0);
		if (this.left  && !this.getBounds().intersects(r.getWallBoundFromKey(Direction.LEFT)))  this.position.move(-this.speed,  0);
		if (this.up    && !this.getBounds().intersects(r.getWallBoundFromKey(Direction.UP)))    this.position.move( 0, -this.speed);
		if (this.down  && !this.getBounds().intersects(r.getWallBoundFromKey(Direction.DOWN)))  this.position.move( 0,  this.speed);
		if (this.takingDmg)  {
			this.dmgTimer = 100;
			this.takingDmg = false;
		}
		this.dmgTimer --;
		if(this.dmgTimer <= 0) 	this.dmgTimer = 0;
		
		if(this.dmgTimer > 0 && this.watchingAt == Direction.LEFT)  this.changeImage(Images.PLAYER_DAMAGE_LEFT);
		else if(this.dmgTimer > 0 && this.watchingAt == Direction.RIGHT) this.changeImage(Images.PLAYER_DAMAGE_RIGHT);
		else if(this.isFighting && this.watchingAt == Direction.LEFT)    this.changeImage(Images.PLAYER_ATTACK_LEFT);
		else if(this.isFighting && this.watchingAt == Direction.RIGHT)   this.changeImage(Images.PLAYER_ATTACK_RIGHT);
		else if(this.watchingAt == Direction.LEFT) this.changeImage(Images.PLAYER_LEFT);
		else this.changeImage(Images.PLAYER_RIGHT);
	}
	
	/** 
	 * Allow to know if the player is fighting or not.
	 * @return the fighting state of the player.
	 */
	public boolean isFighting() {
		return this.isFighting;
	}

	/**
	 * Set it true if the player is taking damages.
	 * @param takingDmg the state of the player.
	 */
	public void setTakingDmg(boolean takingDmg) {
		this.takingDmg = takingDmg;
	}

	/**
	 * Describe what to do when a key is pressed.
	 * @param k the key value pressed.
	 */
	public void keyPressed(int k) {
		if (!this.down && (k == KeyEvent.VK_Z || k == KeyEvent.VK_UP)) {
			this.up = true;
			this.direction = Direction.UP;
		}
		if (!this.up && (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)) {
			this.down = true;
			this.direction = Direction.DOWN;
		}
		if (!this.left && (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)) {
			this.right = true;
			this.direction = Direction.RIGHT;
			this.watchingAt = Direction.RIGHT;
			//this.changeImage(Images.PLAYER_RIGHT);
		}
		if (!this.right && (k == KeyEvent.VK_Q || k == KeyEvent.VK_LEFT)) {
			this.left = true;
			this.direction = Direction.LEFT;
			this.watchingAt = Direction.LEFT;
			//this.changeImage(Images.PLAYER_LEFT);
		}
		if(k == KeyEvent.VK_CONTROL || k == KeyEvent.VK_SHIFT) this.speed = SPRINT_SPEED;
	}
	
	/**
	 * Describe what to do when a key is released.
	 * @param k the key value released.
	 */
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_Z || k == KeyEvent.VK_UP)    this.up = false;
		if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)  this.down = false;
		if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) this.right = false;
		if (k == KeyEvent.VK_Q || k == KeyEvent.VK_LEFT)  this.left = false;
		if (k == KeyEvent.VK_CONTROL || k == KeyEvent.VK_SHIFT) this.speed = DEFAULT_SPEED;
	}
	
	/**
	 * Describe what to do when a button of the mouse is pressed.
	 * @param button the click button value pressed.
	 */
	public void mousePressed(int button) {
		if(button == MouseEvent.BUTTON1) {
			this.isFighting = true;
			this.minorAttack = true;
		}
		if(button == MouseEvent.BUTTON3) {
			this.isFighting = true;
			this.majorAttack = true;
		}
		
	}
	
	/**
	 * Describe what to do when a button of the mouse is released.
	 * @param button the click button value released.
	 */
	public void mouseReleased(int button) {
		if(button == MouseEvent.BUTTON1) {
			this.isFighting = false;
			this.minorAttack = false;
		}
		if(button == MouseEvent.BUTTON3) {
			this.isFighting = false;
			this.majorAttack = false;
		}
		
	}
	
	/**
	 * Change the image of the player.
	 * @param img the Image to be change for.
	 */
	protected void changeImage(Images img) {
		// This method is override by PlayerDisplay, which handle images.  
	}
	
	protected Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
	}
	
	/**
	 * Reject the player if he touches the specified mob.
	 * @param m the mob with who the player interact.
	 */
	public void collision(Mob m) {
		if (m.wantToMove) collision(m.getWatching());
		else collision(this.direction);
	}
	
	/**
	 * Reject the player when he touches something depending on the direction.
	 * @param side the direction where the player touches something.
	 */
	public void collision(Direction side) {
		this.up = false;
		this.right = false;
		this.down = false;
		this.left = false;
		switch(side) {
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
	/**
	 * Load a save from a Json file.
	 * @param save the file that you want to load.
	 */
	public void load(JSONObject save) {
		JSONObject pos;
		
		this.direction = Direction.valueOf((String) save.get("direction"));
		this.watchingAt = Direction.valueOf((String) save.get("watchingAt"));
		this.health.setHealth(((Double) save.get("health")).floatValue());
		this.damage = ((Double) save.get("damage")).floatValue();
		pos = (JSONObject) save.get("position");
		this.position = new Position(((Long) pos.get("x")).intValue(), ((Long) pos.get("y")).intValue());
		pos = (JSONObject) save.get("roomPosition");
		this.roomPosition = new Position(((Long) pos.get("x")).intValue(), ((Long) pos.get("y")).intValue());
		
		this.inventory.load((JSONObject) save.get("inventory"));
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

	/**
	 * Get the inventory of the player.
	 * @return the inventory of the player.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Get the speed of the player.
	 * @return the speed of the player.
	 */
	public int getSpeed()
	{
		return this.speed;
	}

	/**
	 * Set the speed of the player.
	 * @param speed the wanted speed.
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	
}
