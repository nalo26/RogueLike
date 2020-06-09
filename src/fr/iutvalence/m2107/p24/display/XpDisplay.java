package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Xp;

public class XpDisplay extends Xp
{

	public static final int XP_WIDTH = 500;
	public static final int XP_HEIGHT = 20;
	
	public XpDisplay(float theXp)
	{
		super(theXp);
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(GamePanel.WIDTH/2-250, GamePanel.HEIGHT - 30, XP_WIDTH, XP_HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(GamePanel.WIDTH/2-249, GamePanel.HEIGHT - 29, XP_WIDTH-1, XP_HEIGHT-1);
		g.setColor(Color.GREEN);
		g.fillRect(GamePanel.WIDTH/2-249, GamePanel.HEIGHT - 29, (int) this.xp, XP_HEIGHT-1);
	}
}
