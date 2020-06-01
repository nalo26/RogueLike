package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.World;
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
	
	private Position realPosition;
	
	/**
	 * Constructor : initialize fields with default values.
	 */
	public PlayerDisplay() {
		super();
		this.position = DEFAULT_POSITON;
		this.image = DEFAULT_IMAGE;
		this.realPosition = this.position;
	}
	
	/** Draw the player.
	 * @param g the graphic component to paint on.
	 */
	public void draw(Graphics g) {
		this.realPosition = World.updatePosition(this.position);
		
		g.drawImage(this.image, this.realPosition.getX(), this.realPosition.getY(), null);
		g.setColor(Color.BLACK);
		g.drawRect(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
		
		this.health.draw(g, this.realPosition, this.image.getWidth());
		this.inventory.draw(g, this);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void changeImage(Images img) {
		this.image = img.getImage();
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.realPosition.getX(), this.realPosition.getY(), this.image.getWidth(), this.image.getHeight());
	}

}
