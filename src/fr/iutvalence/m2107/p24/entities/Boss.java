package fr.iutvalence.m2107.p24.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.display.HealthDisplay;
import fr.iutvalence.m2107.p24.ressources.Images;
import fr.iutvalence.m2107.p24.rooms.Room;
/**
 * Represent the boss of the game with all of his characteristics (health, damage, type, direction, ...).
 */
public class Boss extends Mob {
	
	/** The different life bar the boss have. */
	private static final int PHASE_AMOUNT = 3;
	/** The amount of life of the boss PER PHASE/BAR. */
	private static final int DEFAULT_LIFE = 100;
	/** The different damage the boss deal PER PHASE. */
	private static final int[] DEFAULT_DAMAGE = {10, 20, 50}; // one per phase of fight.
	
	/** The image of the boss. */
	private BufferedImage image;
	/** The phase of the fight the boss is (higher = stronger (hello Daft Punk)). */
	private int phase;
	/** The different health of the boss. */
	private HealthDisplay[] healths;
	
	/**
	 * Create a new boss, with its specific attributes.
	 * Unlike a classic boss, it has more life, and deals a lot more damage,
	 * according to its phase of combat.
	 * Its picture is different in each phase.
	 */
	public Boss() {
		super(null);
		this.healths = new HealthDisplay[PHASE_AMOUNT];
		for(int i = 0; i < this.healths.length; i++) {
			this.healths[i] = new HealthDisplay(DEFAULT_LIFE);
		}
		this.image = Images.BOSS1_NORMAL.getImage();
		this.phase = 1;
		
		this.position = new Position(GamePanel.WIDTH/2-this.image.getWidth()/2, GamePanel.HEIGHT/2-this.image.getHeight()/2);
	}
	
	/** {@inheritDoc} */
	@Override
	public Mob tick(Room r, Player p) {
		this.phase = PHASE_AMOUNT - this.getLastLifeBar();
		this.damage = DEFAULT_DAMAGE[this.phase-1];
		
		if(this.damageCooldown > 0) this.damageCooldown--;
		else this.state = State.NORMAL;
		
		if (this.getBounds().intersects(p.getBounds())) {
			p.collision(this);
			if(p.getState() == State.ATTACK) {
				this.healths[this.getLastLifeBar()].removeLife(p.getDamage());
				this.takeDamage();
			} else p.takeDamage(this.damage);
		}
		
		if(this.healths[0].getLife() <= 0) return this;
		return null;
	}
	
	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		this.image = Images.valueOf("BOSS" + this.phase + "_" + this.state).getImage();
		
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		
		for(int i = 0; i < this.healths.length; i++) {
			Position pos = this.position.copy();
			pos.move(0, -(HealthDisplay.HEALTH_HEIGHT+4)*i);
			this.healths[i].draw(g, pos, this.image.getWidth(), HealthDisplay.BOSS_STYLE);
		}
	}
	
	/**
	 * Get the last life bar of the boss which is not empty.
	 * @return the index of the last life bar.
	 */
	private int getLastLifeBar() {
		for(int i = this.healths.length-1; i >= 0; i --) {
			if(this.healths[i].getLife() > 0) return i;
		}
		return this.healths.length-1;
	}
	
	/** {@inheritDoc} */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
