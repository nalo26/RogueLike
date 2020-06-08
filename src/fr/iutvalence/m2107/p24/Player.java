package fr.iutvalence.m2107.p24;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.display.InventoryDisplay;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the player and all of his possible actions (moves, directions, ...).
 */
public class Player {

	/** The default life of the player. */
	public static final int DEFAULT_HEALTH = 20;
	/** The default damage of the player. */
	public static final int DEFAULT_DAMAGE = 1;
	/** The damage to add to the player's damage. */
	public static final int DAMAGE_BOOST = +2;
	/** The default cooldown between two critical attacks. */
	public static final int DEFAULT_ATTACK_COOLDOWN = 100;
	/** The default time the player is taking damage (sprite animation). */
	public static final int DEFAULT_DAMAGE_COOLDOWN = 100;
	/** The default room coordinates the player is in. */
	public static final Position DEFAULT_ROOM_POSITION = new Position(0, 0);
	/** The default speed of the player (pixels per tick). */
	public static final int DEFAULT_SPEED = 3;
	/** The speed to add to the player's speed (pixels per tick). */
	public static final int SPRINT_SPEED = +2;
	
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
	protected boolean right;
	protected boolean down;
	protected boolean left;
	/** The cooldown between two critical attacks. */
	protected int attackCooldown;
	/** The time the player is taking damage. */
	protected int damageCooldown;
	
	protected State state;

	
	
	/** Create a new player, with all its caracteristics (health, speed, etc)  */
	public Player() {
		this.speed = DEFAULT_SPEED;
		this.health = new HealthDisplay(DEFAULT_HEALTH);
		this.damage = DEFAULT_DAMAGE;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.roomPosition = DEFAULT_ROOM_POSITION.copy();
		this.inventory = new InventoryDisplay();
		this.attackCooldown = 0;
		this.damageCooldown = 0;
		this.state = State.NORMAL;
	}
	
	/** Describe the behavior of the player after a key is pressed. 
	 * @param currentRoom the current room the player interacts with. */
	public void tick(Room currentRoom) {
		if (this.right && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.RIGHT))) this.position.move( this.speed,  0);
		if (this.left  && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.LEFT)))  this.position.move(-this.speed,  0);
		if (this.up    && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.UP)))    this.position.move( 0, -this.speed);
		if (this.down  && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.DOWN)))  this.position.move( 0,  this.speed);
				
		if(this.attackCooldown > 0) this.attackCooldown --;
		if(this.damageCooldown > 0) this.damageCooldown --;
		if(this.damageCooldown == 0 && this.state != State.ATTACK) this.state = State.NORMAL;
		
		if (    this.state == State.DAMAGE && this.watchingAt == Direction.LEFT)  this.changeImage(Images.PLAYER_DAMAGE_LEFT);
		else if(this.state == State.DAMAGE && this.watchingAt == Direction.RIGHT) this.changeImage(Images.PLAYER_DAMAGE_RIGHT);
		else if(this.state == State.ATTACK && this.watchingAt == Direction.LEFT)  this.changeImage(Images.PLAYER_ATTACK_LEFT);
		else if(this.state == State.ATTACK && this.watchingAt == Direction.RIGHT) this.changeImage(Images.PLAYER_ATTACK_RIGHT);
		else if(this.state == State.NORMAL && this.watchingAt == Direction.LEFT)  this.changeImage(Images.PLAYER_LEFT);
		else if(this.state == State.NORMAL && this.watchingAt == Direction.RIGHT) this.changeImage(Images.PLAYER_RIGHT);
		
		this.updateItems(currentRoom);
		
		
	}

	/**
	 * Update all items in the given room.
	 * @param currentRoom the room where it has to update items.
	 */
	public void updateItems(Room currentRoom) {
		Item itemToRemove = null;
		for(Item i : currentRoom.getAllItems()) {
			if(this.getBounds().intersects(i.getBounds())) {
				itemToRemove = i;
				this.inventory.addItem(i);
			}
		}
		currentRoom.removeItem(itemToRemove);
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
		}
		if (!this.right && (k == KeyEvent.VK_Q || k == KeyEvent.VK_LEFT)) {
			this.left = true;
			this.direction = Direction.LEFT;
			this.watchingAt = Direction.LEFT;
		}
		if(k == KeyEvent.VK_CONTROL || k == KeyEvent.VK_SHIFT) this.speed = DEFAULT_SPEED + SPRINT_SPEED;
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
			this.damage = DEFAULT_DAMAGE;
			this.state = State.ATTACK;
		}
		if(button == MouseEvent.BUTTON3) {
			if(this.attackCooldown <= 0) {
				System.out.println("CRITICAL");
				this.damage = DEFAULT_DAMAGE + DAMAGE_BOOST;
				this.attackCooldown = DEFAULT_ATTACK_COOLDOWN;
				this.state = State.ATTACK;
			}
		}	
	}
	
	/**
	 * Describe what to do when a button of the mouse is released.
	 * @param button the click button value released.
	 */
	public void mouseReleased(int button) {
		if(button == MouseEvent.BUTTON1 || button == MouseEvent.BUTTON3) {
			this.state = State.NORMAL;
		}
	}
	/**
	 * Describe what to do when the player take damage/get hitten by a mob.
	 * @param dmg the damage taken by the player
	 */
	public void takeDamage(float dmg) {
		if(this.canTakeDamage()) {
			this.health.removeLife(dmg);
			this.damageCooldown = DEFAULT_DAMAGE_COOLDOWN;
			this.state = State.DAMAGE;
		}
	}
	
	/**
	 * Change the image of the player.
	 * @param img the Image to be change for.
	 */
	protected void changeImage(Images img) {
		// This method is override by PlayerDisplay, which handle images.  
	}
	
	public void updateRealPosition() {
		// Override later.
	}
	
	/**
	 * Get the bounds of the player, according to his position,
	 * and his dimensions.
	 * @return the bounds of the player.
	 */
	protected Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
		// This method is override by PlayerDisplay, which handle Bounds.  
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
	 * Indicates if the player can take damage.
	 * @return <tt>true</tt> if he can, <tt>false</tt> else.
	 */
	public boolean canTakeDamage() {
		return (this.damageCooldown == 0);
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
	 * Get the current state of the player.
	 * @return the state of the player.
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Get the speed of the player.
	 * @return the speed of the player.
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Set the speed of the player.
	 * @param speed the wanted speed.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
}
