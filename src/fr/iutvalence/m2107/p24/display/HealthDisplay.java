package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.Health;
import fr.iutvalence.m2107.p24.Position;

public class HealthDisplay extends Health {

	private static final int HEALTH_WIDTH = 52;
	private static final int HEALTH_HEIGHT = 10;
	private static final int OFFSET = 20;
	
	public HealthDisplay(float defaultHealth) {
		super(defaultHealth);
	}
	
	public void draw(Graphics g, Position p, int size) {
		g.setColor(Color.WHITE);
		g.fillRect(p.getX() + size/2 - HEALTH_WIDTH/2, p.getY() - OFFSET, HEALTH_WIDTH, HEALTH_HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(p.getX() + size/2 - HEALTH_WIDTH/2 + 1, p.getY() - (OFFSET-1), HEALTH_WIDTH-2, HEALTH_HEIGHT-2);
		int red = (int)(255 * (1 - this.life / this.defaultLife));
		int green = (int)(255 * (this.life / this.defaultLife));
		g.setColor(new Color(red, green, 0));
		g.fillRect(p.getX() + size/2 - HEALTH_WIDTH/2 + 1, p.getY() - (OFFSET-1), (int)((HEALTH_WIDTH-2) * (this.life / this.defaultLife)), HEALTH_HEIGHT-2);
	}
	
}
