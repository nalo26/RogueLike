package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.MiniMap;
import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Room;

public class MiniMapDisplay extends MiniMap {
	
	public MiniMapDisplay() {
		super();
	}
	
	public void draw(Graphics g, Player p) {
        this.getRoom(p.getRoomPosition()).draw(g);

        g.setColor(Color.BLACK);
		g.fillRect(GamePanel.WIDTH-15-250, 15, 250, 250);
		
		Room query = null;
		int size = 40;
		for(Room r : this.rooms) {
			g.setColor(Color.WHITE);
			
			int x = GamePanel.WIDTH / 2 + r.getPosition().getX() * size - size / 2 + 10 * r.getPosition().getX();
			int y = GamePanel.HEIGHT/ 2 - r.getPosition().getY() * size - size / 2 - 10 * r.getPosition().getX();
			
			query = this.getRoom(new Position(r.getPosition().getX()-1, r.getPosition().getY()));
			if (query != null && query.isOpen(Direction.RIGHT)) g.fillRect(x-10, y+11, 10, 18);
			query = this.getRoom(new Position(r.getPosition().getX(), r.getPosition().getY()+1));
			if (query != null && query.isOpen(Direction.DOWN)) g.fillRect(x+11, y-10, 18, 10);
			
			if(r.getPosition().equals(new Position(0, 0))) g.setColor(Color.GREEN);
			if(r.getPosition().equals(p.getRoomPosition())) g.setColor(Color.YELLOW);
			g.fillRect(x, y, size, size);
		}
	}

}
