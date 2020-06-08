package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import org.json.simple.JSONObject;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;

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
	/** The current state of the mob, Normal or taking Damage. */
	protected State state;
	/** The time the mob is taking damages, and invincible. */
	protected int damageCooldown;
	/** */
	protected float slimeHeight;
	protected int slimeSpeedHeight;
	
	/**
	 * Create a new mob, with its specific attributes.
	 * @param theType of the mob
	 */
	public Mob(MobType theType) {
		this.health = new HealthDisplay(DEFAULT_HEALTH);
		this.damage = DEFAULT_DAMAGE;
		this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		this.type = theType;
		this.direction = Direction.RIGHT;
		this.watchingAt = Direction.RIGHT;
		this.wantToMove = false;
		this.lengthOfMove = 0;
		this.state = State.NORMAL;
		this.damageCooldown = 0;
		if(this.type == MobType.SLIME) {
			this.slimeHeight = (float)new Random().nextInt(50);
			this.slimeSpeedHeight = new Random().nextInt(100)+50;
		}
	}
	
	/** Describe the behavior of a mob every tick. 
	 * @param room The room the mob is on.
	 * @param p The player of the room.
	 * @return a Mob that have been killed if any.
	 */
	public Mob tick(Room room, Player p) {
		if (this.wantToMove) {
			if (this.direction == Direction.RIGHT && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.RIGHT))) this.position.move(1, 0);
			if (this.direction == Direction.LEFT  && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.LEFT)))  this.position.move(-1, 0);
			if (this.direction == Direction.DOWN  && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.DOWN)))  this.position.move(0, 1);
			if (this.direction == Direction.UP    && !this.getBounds().intersects(MiniMapDisplay.getWallBoundFromKey(Direction.UP)))    this.position.move(0, -1);
			
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
		
		if(this.type == MobType.SLIME) {
			this.slimeHeight += (float)this.slimeSpeedHeight/100;
			if(this.slimeHeight >= 50) this.slimeHeight = 0;
		}
		
		if(this.damageCooldown > 0) this.damageCooldown--;
		else this.state = State.NORMAL;

		if (this.getBounds().intersects(p.getBounds())) {
			p.collision(this);
			if(p.getState() == State.ATTACK) {
				this.health.removeLife(p.getDamage());
				this.takeDamage();
			} else {
				p.takeDamage(this.damage);
			}
		}
		
		updateImage();

		if(this.getHealth().getLife() <= 0) return this;
		return null;
	}
	
	/** Draw the mob.
	 * @param g the draw component
	 */
	public void draw(Graphics g) {
		// Override later.
	}
	
	public void takeDamage() {
		this.state = State.DAMAGE;
		this.damageCooldown = 50;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 0, 0);
		//this method is override by PlayerDisplay, which handle bounds.
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

	public void load(JSONObject save) {
		this.health.setHealth(((Double) save.get("health")).floatValue());
		this.damage = ((Double) save.get("damage")).floatValue();
		this.direction = Direction.valueOf((String) save.get("direction"));
		JSONObject pos = (JSONObject) save.get("position");
		this.position = new Position(((Long) pos.get("x")).intValue(), ((Long) pos.get("y")).intValue());
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
