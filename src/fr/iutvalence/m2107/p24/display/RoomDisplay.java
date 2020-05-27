package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Mob;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Room;
import fr.iutvalence.m2107.p24.ressources.Images;

public class RoomDisplay extends Room {

	private BufferedImage image;
	
	public RoomDisplay(Position pos, boolean[] config, String bin) {
		super(pos, config, bin);
		this.image = Images.valueOf("ROOM"+Integer.parseInt(bin, 2)).getImage();
	}
	
	public RoomDisplay(Position pos, String config) {
		this(pos, Room.computeDoors(config), config);
	}

	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		for(MobDisplay m : this.mobs) {
			m.draw(g);
		}
		g.setColor(Color.RED);
	}
}
