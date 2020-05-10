package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	public static final int DEFAULT_SIZE = 20;
	
	private List<Mob> mobs = new ArrayList<Mob>();
	private int[][] roomBoard = new int[DEFAULT_SIZE][DEFAULT_SIZE];
	
	public Room() {
		
	}

	public List<Mob> getMobs() {
		return this.mobs;
	}
	
	public int[][] getBoard() {
		return this.roomBoard;
	}
	
}
