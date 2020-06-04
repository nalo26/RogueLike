package fr.iutvalence.m2107.p24.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.items.ItemsList;
import fr.iutvalence.m2107.p24.ressources.Images;

public class ItemDisplay extends Item {
	
	public ItemDisplay(ItemsList type, int id, int prob, Position pos) {
		super(type, id, prob, pos);
	}

	private BufferedImage image;
	
	public void draw(Graphics g) {
		System.out.println("draw");
		g.drawImage(this.image, this.position.getX(), this.position.getY(), null);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.position.getX(), this.position.getY(), this.image.getWidth(), this.image.getHeight());
	}

	@Override
	public void changeImage(Images i) {
		System.out.println("changed image");
		this.image = i.getImage();
	}

}
