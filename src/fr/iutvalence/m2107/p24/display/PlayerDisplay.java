package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

public class PlayerDisplay extends Player {

	public static final BufferedImage DEFAULT_IMAGE = Images.PLAYER_RIGHT.getImage();
	
	/** The default position of the player. */
	public static final Position DEFAULT_POSITON = new Position(GamePanel.WIDTH/2 - (DEFAULT_IMAGE.getWidth()/2), GamePanel.HEIGHT/2 - (DEFAULT_IMAGE.getHeight()/2));
	
	/** The image of the player. */
	private BufferedImage image;
	
	public PlayerDisplay() {
		super();
		this.position = DEFAULT_POSITON;
		this.image = DEFAULT_IMAGE;
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
		this.inventory.draw(g);
	}
	
	@Override
	protected void changeImage(Images img) {
		this.image = img.getImage();
	}

}
