package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.Health;
import fr.iutvalence.m2107.p24.Position;
/**
 * Display the health with a colored bar.
 */
public class HealthDisplay extends Health {
	/** The width of the health display for normal mobs. */
	public static final int NORMAL_STYLE = 52;
	/** The width of the health display for the boss. */
	public static final int BOSS_STYLE = 102;
	/** The height of the health display. */
	public static final int HEALTH_HEIGHT = 10;
	/** The offset of the health display. */
	private static final int OFFSET = 20;
	
	/**
	 * Call the Builder of Health for your health on the display.
	 * @param defaultHealth the defaultHealth of a mob or of the player.
	 */
	public HealthDisplay(float defaultHealth) {
		super(defaultHealth);
	}
	
	/**
	 * Draw the Health Display of our object (mob or player).
	 * @param g the draw component.
	 * @param p the position of our display
	 * @param size the size of our display
	 * @param width the width of the life bar.
	 */
	public void draw(Graphics g, Position p, int size, int width) {
		g.setColor(Color.WHITE);
		g.fillRect(p.getX() + size/2 - width/2, p.getY() - OFFSET, width, HEALTH_HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(p.getX() + size/2 - width/2 + 1, p.getY() - (OFFSET-1), width-2, HEALTH_HEIGHT-2);
		int red = (int)(255 * (1 - this.life / this.defaultLife));
		int green = (int)(255 * (this.life / this.defaultLife));
		if(green < 0) green = 0;
		if(red > 255) red = 255;
		g.setColor(new Color(red, green, 0));
		g.fillRect(p.getX() + size/2 - width/2 + 1, p.getY() - (OFFSET-1), (int)((width-2) * (this.life / this.defaultLife)), HEALTH_HEIGHT-2);
	}
	
}
