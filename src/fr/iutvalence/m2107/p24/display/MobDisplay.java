package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Mob;
import fr.iutvalence.m2107.p24.MobType;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.ressources.Images;
/** Display a mob depending on its type. */
public class MobDisplay extends Mob {
	
	/** The image of the mob. */
	private BufferedImage image;
	
	private Position realPosition;
	/**
	 * Constructor : call his super class Mob.
	 * @param type Type of the mob.
	 */
	public MobDisplay(MobType type) {
		super(type);
		updateImage();
		
		this.realPosition = World.updatePosition(this.position);
		while(!MiniMapDisplay.canBeCreatedAt(this.getBounds())) {
			this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
			this.realPosition = World.updatePosition(this.position);
		}
	}

/** {@inheritDoc} */
	@Override
	protected void updateImage() {
		switch(this.type) {
			case SLIME:
				this.image = Images.SLIME_GREEN_DOWN.getImage();
				if(this.dmgTimer > 0) this.image = Images.SLIME_RED_DOWN.getImage();
				break;
			case ZOMBIE:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.ZOMBIE_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.ZOMBIE_STAY_LEFT.getImage();
					
				}
				if(this.watchingAt == Direction.RIGHT && this.dmgTimer > 0) this.image = Images.ZOMBIE_DAMAGE_RIGHT.getImage();
				if(this.watchingAt == Direction.LEFT && this.dmgTimer > 0) this.image = Images.ZOMBIE_DAMAGE_LEFT.getImage();
				break;
			case SKELETON:
				if(this.wantToMove) {
					if (this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_WALK_RIGHT.getImage();
					if (this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_WALK_LEFT.getImage();
				} else {
					if(this.watchingAt == Direction.RIGHT) this.image = Images.SKELETON_STAY_RIGHT.getImage();
					if(this.watchingAt == Direction.LEFT) this.image = Images.SKELETON_STAY_LEFT.getImage();
				}
				if(this.watchingAt == Direction.RIGHT && this.dmgTimer > 0) this.image = Images.SKELETON_DAMAGE_RIGHT.getImage();
				if(this.watchingAt == Direction.LEFT && this.dmgTimer > 0) this.image = Images.SKELETON_DAMAGE_LEFT.getImage();
				break;
			default: break;
		}
	}

	/** Draw the mob.
	 * @param g the draw component
	 */
	public void draw(Graphics g) {
		this.realPosition = World.updatePosition(this.position);
		
		g.drawImage(this.image, this.realPosition.getX(), this.realPosition.getY(), null);
		g.setColor(Color.BLACK);
		g.drawRect(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
		
		this.getHealth().draw(g, this.realPosition, this.image.getWidth());
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
