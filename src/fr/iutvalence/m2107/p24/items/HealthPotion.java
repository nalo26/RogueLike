package fr.iutvalence.m2107.p24.items;

import fr.iutvalence.m2107.p24.Player;

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
	public HealthPotion(String theName, int theId, int theprobabilty, Player thePlayer) {
		super(theName, theId, theprobabilty, thePlayer);
	}

	public void heal()
	{
		 super.getPlayer().getHealth().setHealth(5);
	}

}
