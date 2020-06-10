package fr.iutvalence.m2107.p24.display;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.entities.State;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Display the player.
 */
public class PlayerDisplay extends Player {

	/** The default image of the player.*/
	public static final BufferedImage DEFAULT_IMAGE = Images.PLAYER_RIGHT.getImage();
	/** The default position of the player. */
	public static final Position DEFAULT_POSITON = new Position(GamePanel.WIDTH/2 - (DEFAULT_IMAGE.getWidth()/2), GamePanel.HEIGHT/2 - (DEFAULT_IMAGE.getHeight()/2));
	/** The image of the player. */
	private BufferedImage image;
	
	/**
	 * Constructor : initialize fields with default values.
	 */
	public PlayerDisplay() {
		super();
		this.position = DEFAULT_POSITON.copy();
		this.image = DEFAULT_IMAGE;
		
	}
	
	/** Draw the player.
	 * @param g the graphic component to paint on.
	 */
	public void draw(Graphics g) {
		Position pos;
		if(this.state == State.ATTACK && this.watchingAt == Direction.LEFT) {
			pos = new Position(this.position.getX() - (this.image.getWidth()-DEFAULT_IMAGE.getWidth()), this.position.getY()); 
		} else pos = this.position;

		g.drawImage(this.image, pos.getX(), pos.getY(), null);
		
		this.health.draw(g, this.position, DEFAULT_IMAGE.getWidth(), HealthDisplay.NORMAL_STYLE);
		this.inventory.draw(g, this);
		this.xp.draw(g);
		g.setFont(new Font("Montserrat", 0, 30));
		g.drawString(String.valueOf(this.lvl), GamePanel.WIDTH/2, GamePanel.HEIGHT - 40);
	}

	/** {@inheritDoc} */
	@Override
	protected void changeImage(Images img) {
		this.image = img.getImage();
	}
	
	/** {@inheritDoc} */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), DEFAULT_IMAGE.getWidth(), DEFAULT_IMAGE.getHeight());
	}
}
