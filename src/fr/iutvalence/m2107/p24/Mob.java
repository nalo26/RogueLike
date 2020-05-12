package fr.iutvalence.m2107.p24;

public class Mob {
	
	/**
	 * The default life of the mob
	 */
	public static final int DEFAULT_HEALTH = 20;
	
	/**
	 * the default damage of the mob 
	 */
	public static final int DEFAULT_DAMAGE = 2;
	
	/**
	 * the default position of the mob
	 */
	public static final Position DEFAULT_POSITON = new Position(0, 0); // TODO random Position in the room
	
	/**
	 * the health of the mob
	 */
	private float health;
	
	/**
	 * the damage of the mob
	 */
	private float damage;
	
	/**
	 * the position of the mob
	 */
	private Position position;
	
	/**
	 * the type of the mob
	 */
	private MobType type;
	
	
/**
 * Constructor of the mob, set the health, the damage, the position, and the type.
 * @param theType of the mob
 */
	public Mob(MobType theType) {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
		this.type = theType;
	}
	
	
	/**
	 * @return the health of the mob (Getter) 
	 */
	public float getHealth() {
		return this.health;
	}
	
	/**
	 * @return the damage of the mob (Getter) 
	 */
	public float getDamage() {
		return this.damage;
	}
	
	/**
	 * @return the position of the mob (Getter) 
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * @return the type of the mob (Getter) 
	 */
	public MobType getType() {
		return this.type;
	}

}
