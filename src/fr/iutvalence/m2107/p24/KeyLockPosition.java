package fr.iutvalence.m2107.p24;
/**
 * Represent all of the possible Key Lock Position.
 */
public enum KeyLockPosition {

	UP_0(new Position(491, 21)),
	UP_1(new Position(555, 21)),
	UP_2(new Position(747, 21)),
	UP_3(new Position(811, 21)),
	
	DOWN_0(new Position(491, 678)),
	DOWN_1(new Position(555, 678)),
	DOWN_2(new Position(747, 678)),
	DOWN_3(new Position(811, 678)),
	
	RIGHT_0(new Position(1237, 169)),
	RIGHT_1(new Position(1237, 233)),
	RIGHT_2(new Position(1237, 424)),
	RIGHT_3(new Position(1237, 487)),
	
	LEFT_0(new Position(21, 487)),
	LEFT_1(new Position(21, 424)),
	LEFT_2(new Position(21, 233)),
	LEFT_3(new Position(21, 169));
	
	private Position position;
	
	KeyLockPosition(Position pos) {
		this.setPosition(World.updatePosition(pos));
	}

	public Position getPosition() {
		this.setPosition(World.updatePosition(this.position));
		return this.position;
	}

	public void setPosition(Position pos) {
		this.position = pos;
	}
	
}
