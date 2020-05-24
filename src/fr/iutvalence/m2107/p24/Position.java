package fr.iutvalence.m2107.p24;

import java.util.Random;

public class Position {

	/** The x position in the screen. */
	private int x;
	
	/** The y position in the screen. */
	private int y;	
	
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
	 * Add the parameters to the current position.
	 * @param x the X to add.
	 * @param y the Y to add.
	 */
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Get the x value of the position.
	 * @return X position (Getter).
	 */
	public int getX() {
		return  this.x;
	}
	
	/**
	 * Get the y value of the position.
	 * @return Y position (Getter).
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Randomly choose a position :
	 * (X between <tt>minX</tt> and <tt>maxX</tt>),
	 * (Y between <tt>minY</tt> and <tt>maxY</tt>),
	 * @param minX the minimum X value.
	 * @param maxX the maximum X value.
	 * @param minY the minimum Y value.
	 * @param maxY the maximum Y value.
	 * @return a new random position on the screen.
	 */
	public static Position randomPosition(int minX, int maxX, int minY, int maxY) {
		Random random = new Random();
		return new Position(random.nextInt(maxX - minX) + minX, random.nextInt(maxY - minY) + minY);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Position other = (Position) obj;
		if (this.x != other.x) return false;
		if (this.y != other.y) return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
}
