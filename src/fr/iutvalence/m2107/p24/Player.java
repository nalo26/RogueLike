package fr.iutvalence.m2107.p24;

public class Player {
	
	public static final int DEFAULT_HEALTH = 20;
	public static final int DEFAULT_DAMAGE = 2;
	public static final Position DEFAULT_POSITON = new Position(0, 0); // TODO center of the room
	
	private float health;
	private float damage;
	private Position position;
	
	public Player() {
		this.health = DEFAULT_HEALTH;
		this.damage = DEFAULT_DAMAGE;
		this.position = DEFAULT_POSITON;
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

}
