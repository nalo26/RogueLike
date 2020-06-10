package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Xp;
/**
 * Display the XP with a colored bar.
 */
public class XpDisplay extends Xp {
	
	/** The width of the xp display for the player. */
	public static final int XP_WIDTH = 500;
	/** The height of the xp display for the player. */
	public static final int XP_HEIGHT = 20;
	
	/**
	 * Call the constructor of Xp for your xp on the display.
	 * @param theXp the current xp of the player.
	 */
	public XpDisplay(float theXp) {
		super(theXp);
	}

	/**
	 * Draw the XP Display of our player.
	 * @param g the draw component.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH/2-XP_WIDTH/2, GamePanel.HEIGHT-XP_HEIGHT-10, XP_WIDTH, XP_HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(GamePanel.WIDTH/2-XP_WIDTH/2+1, GamePanel.HEIGHT-XP_HEIGHT-10 + 1, XP_WIDTH-1, XP_HEIGHT-1);
		g.setColor(Color.GREEN);
		g.fillRect(GamePanel.WIDTH/2-XP_WIDTH/2+1, GamePanel.HEIGHT-XP_HEIGHT-10 + 1, (int) this.xp, XP_HEIGHT-1);
	}
}
