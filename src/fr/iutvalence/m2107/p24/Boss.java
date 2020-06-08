package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.ressources.Images;

public class Boss extends Mob {

	private static final int DEFAULT_LIFE = 20;
	private static final int LIFE_AMOUNT = 3;
	private static final int[] DEFAULT_DAMAGE = {3, 5, 8};
	
	/** The image of the boss. */
	private BufferedImage image;
	/** The real position of the boss. */
	private Position realPosition;
	/** The phase of the fight the boss is (higher = stronger (hello Daft Punk)). */
	private int phase;
	
	private HealthDisplay[] healths;
	
	public Boss() {
		super(null);
		this.healths = new HealthDisplay[LIFE_AMOUNT];
		for(int i = 0; i < this.healths.length; i++) {
			this.healths[i] = new HealthDisplay(DEFAULT_LIFE);
		}
		this.image = Images.BOSS1.getImage();
		this.phase = 1;
		
		this.realPosition = World.updatePosition(this.position);
		while(!MiniMapDisplay.canBeCreatedAt(this.getBounds())) {
			this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			this.realPosition = World.updatePosition(this.position);
		}
	}
	
	@Override
	public Mob tick(Room r, Player p) {
		this.damage = DEFAULT_DAMAGE[this.phase-1];
		
		return null;
	}
	
	@Override
	public void draw(Graphics g) {
		this.image = Images.valueOf("BOSS"+this.phase).getImage();
		this.realPosition = World.updatePosition(this.position);
		
		g.drawImage(this.image, this.realPosition.getX(), this.realPosition.getY(), null);
		
		for(int i = 0; i < this.healths.length; i++) {
			this.healths[i].draw(g, this.realPosition, this.image.getWidth()+10*i);
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
