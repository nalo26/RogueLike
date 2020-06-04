package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;

import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;

/**
 * Represent the Health potion.
 */
public class HealthPotion extends Item
{
	
	/**
	 * Constructor.
	 * @param theName name of the item.
	 * @param theId if of the item.
	 */
	public HealthPotion(String theName, int theId, int theprobabilty, Player thePlayer, Position thePos) {
		super(theName, theId, theprobabilty, thePlayer, thePos);
	}

	public void heal()
	{
		 super.getPlayer().getHealth().setHealth(5);
	}

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(super.pos.getX(), super.pos.getY(), 124, 140);
	}

}
