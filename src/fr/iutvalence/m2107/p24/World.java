package fr.iutvalence.m2107.p24;

import java.awt.Graphics;

public class World extends GameState
{
	
	/**
	 * Rooms of the World.
	 * We make a one-dimensional table because the world have many Rooms.
	 */
	public Room[] rooms;
	
	/**
	 * Mobs of the World.
	 * We make a one-dimensional table because the world have many Mobs.
	 */
	public Mob[] mobs;
	
	/**
	 * The Player in the World.
	 */
	public Player player;

	/**
	 * Constructor of the World. 
	 * @param gsm State of the game.
	 */
	public World(GameStateManager gsm)
	{
		super(gsm);
		
	}

	@Override
	/**
	 * Initialization of your game.
	 * We set the player and then we create all the rooms.
	 */
	public void init()
	{
		 this.player = new Player();
		 for(int i = 0; i < this.rooms.length; i++)
		 {
			 this.rooms[i] = new Room();
		 }
		
	}

	@Override
	public void tick()
	{
		 
		
	}

	@Override
	public void draw(Graphics p0)
	{
		 
		
	}

	@Override
	public void keyPressed(int p0)
	{
		 
		
	}

	@Override
	public void keyReleased(int p0)
	{
		 
		
	}

}
