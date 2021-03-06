package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Colors;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.entities.MobType;
import fr.iutvalence.m2107.p24.entities.State;
import fr.iutvalence.m2107.p24.ressources.Images;
/** Display a mob depending on its type. */
public class MobDisplay extends Mob {
	/** The color of the mob. */
	private Colors color;
	/** The image of the mob. */
	private BufferedImage image;
	
	/**
	 * Constructor : call his super class Mob.
	 * @param type Type of the mob.
	 */
	public MobDisplay(MobType type) {
		super(type);
		this.color = Colors.randomColor();
		updateImage();
		while(!MiniMapDisplay.canBeCreatedAt(this.getBounds())) {
			this.position = Position.randomPosition(0, GamePanel.WIDTH, 0, GamePanel.HEIGHT);
		}
	}

/** {@inheritDoc} */
	@Override
	protected void updateImage() {
		if(this.type == MobType.SLIME) {
			if(this.slimeHeight < 25) this.image = Images.valueOf("SLIME_"+this.color+"_DOWN").getImage();
			else this.image = Images.valueOf("SLIME_"+this.color+"_UP").getImage();
		} else {
			if(this.state == State.NORMAL) {
				if(this.wantToMove) this.image = Images.valueOf(this.type + "_WALK_" + this.watchingAt).getImage();
				else this.image = Images.valueOf(this.type + "_STAY_" + this.watchingAt).getImage();
			} else this.image = Images.valueOf(this.type + "_DAMAGE_" + this.watchingAt).getImage();
		}
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
		
		this.health.draw(g, this.position, this.image.getWidth(), HealthDisplay.NORMAL_STYLE);
	}
	
	/**
	 * Give the bounds of a mob.
	 * @return a rectangle depending on the position of the entity and his size.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
