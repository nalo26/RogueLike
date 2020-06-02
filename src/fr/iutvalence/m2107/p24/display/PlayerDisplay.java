package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
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
	
	/** The real position of the player. */
	private Position realPosition;
	
	/** The image of the player when he's watching on the left and taking damages. */
	public static final BufferedImage DMG_LEFT = Images.PLAYER_DAMAGE_LEFT.getImage();
	
	/** The image of the player when he's watching on the right and taking damages. */
	public static final BufferedImage DMG_RIGHT = Images.PLAYER_DAMAGE_RIGHT.getImage();
	
	
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
	if(this.dmgTimer > 0 && this.watchingAt == Direction.LEFT) g.drawImage(DMG_LEFT, this.realPosition.getX(), this.realPosition.getY(), DMG_LEFT.getWidth(), DMG_LEFT.getHeight(), null);
	if(this.dmgTimer > 0 && this.watchingAt == Direction.RIGHT) g.drawImage(DMG_RIGHT, this.realPosition.getX(), this.realPosition.getY(), DMG_RIGHT.getWidth(), DMG_RIGHT.getHeight(), null);
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
