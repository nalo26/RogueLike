package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	/**
	 * Default size of the room.
	 */
	public static final int DEFAULT_SIZE = 20;
	
	/**
	 * List of all mobs in this room.
	 */
	private List<Mob> mobs = new ArrayList<Mob>();
	
	/**
	 * The Board of the game.
	 * we make a two-dimensional table because we've got a height and a width so 2 dimension.
	 * TTT board upper-left corner is at [0][0], TTT board lower-left corner is at [DEFAULT_SIZE][DEFAULT_SIZE].
	 */
	private int[][] roomBoard = new int[DEFAULT_SIZE][DEFAULT_SIZE];
	
	/**
	 * Constructor of your Room.
	 */
	public Room() {
		
	}

	/**
	 * @return the list of mobs (Getter)
	 */
	public List<Mob> getMobs() {
		return this.mobs;
	}
	
	/**
	 * 
	 * @return the board of your game (Getter)
	 */
	public int[][] getBoard() {
		return this.roomBoard;
	}
	
}
