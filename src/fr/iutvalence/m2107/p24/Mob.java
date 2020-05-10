package fr.iutvalence.m2107.p24;

public class Mob {
	
	public static final int DEFAULT_HEALTH = 20;
	public static final int DEFAULT_DAMAGE = 2;
	public static final Position DEFAULT_POSITON = new Position(0, 0); // TODO random Position in the room
	
	private float health;
	private float damage;
	private Position position;
	private MobType type;
	
	public Mob(MobType theType) {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
		this.type = theType;
	}
	
	public float getHealth() {
		return this.health;
	}
	
	public float getDamage() {
		return this.damage;
	}
	
	public Position getPosition() {
		return this.position;
	}

	public MobType getType() {
		return this.type;
	}

}
