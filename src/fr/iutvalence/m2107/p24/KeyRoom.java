package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.display.MobDisplay;
import fr.iutvalence.m2107.p24.items.Key;
import fr.iutvalence.m2107.p24.ressources.Images;

public class KeyRoom extends Room {
	
	private BufferedImage image;
	
	private Key[] keys;
	
	public KeyRoom(Direction d) {
		super(computeDirection(d));
		this.decor.clear();
		this.allItems.clear();
		
		this.keys = new Key[4];
		
		this.image = Images.valueOf("KEY_ROOM_CLOSE_" + d).getImage();
	}
	
	private static String computeDirection(Direction d) {
		switch (d) {
			case UP:    return "0101";
			case DOWN:  return "0101";
			case LEFT:  return "1010";
			case RIGHT: return "1010";
			default: return null;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		for(MobDisplay m : this.mobs) {
			m.draw(g);
		}
		
		for(int i = 0; i < this.keys.length; i++) {
			//hello there
		}
	}
	
	public void tick() {
		//hello there
	}
	
}
