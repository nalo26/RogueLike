package fr.iutvalence.m2107.p24.rooms;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.entities.Boss;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.ressources.Images;

public class BossRoom extends Room {
	
	private BufferedImage image;
	
	public BossRoom(Direction d) {
		super(computeDirection(d));
		this.mobs.clear();
		this.mobs.add(new Boss());
		this.decor.clear();
		this.allItems.clear();
		
		this.image = Images.valueOf("BOSS_ROOM_" + d).getImage();
	}
	
	private static String computeDirection(Direction d) {
		switch (d) {
			case DOWN:  return "0001";
			case LEFT:  return "0010";
			case UP:    return "0100";
			case RIGHT: return "1000";
			default: return null;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		for(Mob m : this.mobs) {
			m.draw(g);
		}
	}
	
	public void tick() {
		//hello there
	}
	
}
