package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Mob;
import fr.iutvalence.m2107.p24.MobType;
import fr.iutvalence.m2107.p24.ressources.Images;
/** Display a mob depending on his type. */
public class MobDisplay extends Mob {
	
	/** The image of the mob. */
	private BufferedImage image;
	/**
	 * Constructor : call his super class Mob.
	 * @param theType Type of the mob.
	 */
	public MobDisplay(MobType theType) {
		super(theType);
		updateImage();
	}

/** {@inheritDoc} */
	@Override
	protected void updateImage() {
		switch(this.type) {
			case SLIME:
				this.image = Images.SLIME_GREEN_DOWN.getImage();
				break;
			case ZOMBIE:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_STAY_LEFT.getImage();
				}
				break;
			case SKELETON:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_STAY_LEFT.getImage();
				}
				break;
			default: break;
		}
	}

	/** Draw the mob.
	 * @param g the draw component
	 */
	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		g.setColor(Color.BLACK);
		g.drawRect(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
		
		this.getHealth().draw(g, this.position, this.image.getWidth());
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
