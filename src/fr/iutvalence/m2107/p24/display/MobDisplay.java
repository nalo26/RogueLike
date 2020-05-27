package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Mob;
import fr.iutvalence.m2107.p24.MobType;
import fr.iutvalence.m2107.p24.ressources.Images;

public class MobDisplay extends Mob {
	
	/** The image of the mob. */
	private BufferedImage image;
	
	public MobDisplay(MobType theType) {
		super(theType);
		updateImage();
	}


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

	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		g.setColor(Color.BLACK);
		g.drawRect(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
		
		g.setColor(Color.WHITE);
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2, this.position.getY()-20, 52, 10);
		g.setColor(Color.BLACK);
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2+1, this.position.getY()-19, 50, 8);
		int red = (int)(255 * (1 - this.health / DEFAULT_HEALTH));
		int green = (int)(255 * (this.health / DEFAULT_HEALTH));
		g.setColor(new Color(red, green, 0));
		g.fillRect(this.position.getX()+this.image.getWidth()/2-52/2+1, this.position.getY()-19, (int)(50 * (this.health / DEFAULT_HEALTH)), 8);
	}

}
