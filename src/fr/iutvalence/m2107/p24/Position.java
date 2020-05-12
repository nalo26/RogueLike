package fr.iutvalence.m2107.p24;

public class Position {

	/**
	 * The x position in the map
	 */
	private final int x;
	
	/**
	 * The y position in the map
	 */
	private final int y;	
	
	/**
	 * Constructor of Position, set X and Y position.
	 * @param theX position in the map
	 * @param theY position in the map
	 */
	public Position(int theX, int theY) {
		this.x = theX;
		this.y = theY;
	}
	
	/**
	 * @return X position (Getter)
	 */
	public int getX() {
		return  this.x;
	}
	
	/**
	 * @return Y position (Getter)
	 */
	public int getY() {
		return this.y;
	}
	
}
