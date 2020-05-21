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
	 * Randomly choose a position (random X & Y).
	 * @return a new random position on the screen.
	 */
	public static Position randomPosition() {
		Random random = new Random();
		int width = GamePanel.WIDTH;
		int height = GamePanel.HEIGHT;
		return new Position(random.nextInt(width) - width/2, random.nextInt(height) - height/2);
	}
	
}
