package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;

import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;

public class SpeedPotion extends Item
{

	public SpeedPotion(String theName, int theId, int theprobability, Player thePlayer, Position thePos)
	{
		super(theName, theId, theprobability, thePlayer, thePos);
		
	}
	
	public void addSpeed()
	{
			super.player.setSpeed(5);		
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(super.pos.getX(), super.pos.getY(), 124, 140);
	}

}
