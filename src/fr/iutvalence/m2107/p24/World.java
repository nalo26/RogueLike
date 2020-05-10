package fr.iutvalence.m2107.p24;

import java.awt.Graphics;

public class World extends GameState
{
	
	public Room[] rooms;
	public Mob[] mobs;
	public Player player;

	public World(GameStateManager gsm)
	{
		super(gsm);
		
	}

	@Override
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
