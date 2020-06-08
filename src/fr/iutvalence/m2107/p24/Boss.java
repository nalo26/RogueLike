package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.ressources.Images;

public class Boss extends Mob {
	
	/** The different life bar the boss have. */
	private static final int PHASE_AMOUNT = 3;
	/** The amount of life of the boss PER PHASE/BAR. */
	private static final int DEFAULT_LIFE = 20;
	/** The different damage the boss deal PER PHASE. */
	private static final int[] DEFAULT_DAMAGE = {3, 5, 8}; // one per phase of fight.
	
	/** The image of the boss. */
	private BufferedImage image;
	/** The real position of the boss. */
	private Position realPosition;
	/** The phase of the fight the boss is (higher = stronger (hello Daft Punk)). */
	private int phase;
	/** The different health of the boss. */
	private HealthDisplay[] healths;
	
	public Boss() {
		super(null);
		this.healths = new HealthDisplay[PHASE_AMOUNT];
		for(int i = 0; i < this.healths.length; i++) {
			this.healths[i] = new HealthDisplay(DEFAULT_LIFE);
		}
		this.image = Images.BOSS1_NORMAL.getImage();
		this.phase = 1;
		
		this.position = new Position(GamePanel.WIDTH/2-this.image.getWidth()/2, GamePanel.HEIGHT/2-this.image.getHeight()/2);
		this.realPosition = World.updatePosition(this.position);
	}
	
	@Override
	public Mob tick(Room r, Player p) {
		this.phase = this.getLastLifeBar()+1;
		this.damage = DEFAULT_DAMAGE[this.phase-1];
		
		if(this.damageCooldown > 0) this.damageCooldown--;
		else this.state = State.NORMAL;
		
		if (this.getBounds().intersects(p.getBounds())) {
			p.collision(this);
			if(p.getState() == State.ATTACK) {
				this.healths[this.getLastLifeBar()].removeLife(p.getDamage());
				this.takeDamage();
			} //else p.takeDamage(this.damage);
		}
		
		if(this.healths[0].getLife() <= 0) return this;
		return null;
	}
	
	@Override
	public void draw(Graphics g) {
		this.image = Images.valueOf("BOSS" + this.phase + "_" + this.state).getImage();
		this.realPosition = World.updatePosition(this.position);
		
		g.drawImage(this.image, this.realPosition.getX(), this.realPosition.getY(), null);
		
		for(int i = 0; i < this.healths.length; i++) {
			Position pos = this.realPosition.copy();
			pos.move(0, -(HealthDisplay.HEALTH_HEIGHT+4)*i);
			this.healths[i].draw(g, pos, this.image.getWidth(), HealthDisplay.BOSS_STYLE);
		}
	}
	
	
	private int getLastLifeBar() {
		for(int i = this.healths.length-1; i >= 0; i --) {
			if(this.healths[i].getLife() > 0) return i;
		}
		return this.healths.length-1;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
