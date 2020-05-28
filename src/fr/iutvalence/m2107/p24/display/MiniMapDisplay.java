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
	
	private static final int MINIMAP_SIZE = 250;
	private static final int MINIMAP_OFFSET = 15;
	private static final int ROOM_SIZE = 10;
	private static final int CORRIDOR_WIDTH = 3;
	private static final int CORRIDOR_HEIGHT = 6;
	
	public MiniMapDisplay() {
		super();
	}
	
	public void draw(Graphics g, Player p) {
        g.setColor(Color.BLACK);
		g.fillRect(GamePanel.WIDTH - MINIMAP_OFFSET - MINIMAP_SIZE, MINIMAP_OFFSET, MINIMAP_SIZE, MINIMAP_SIZE);
		
		Room query = null;
		for(Room r : this.rooms) {
			g.setColor(Color.WHITE);
			
			//      (            centering on the mini map            ) - (offset room) + (  offset from room's coordinate   ) + (       gap for corridors if any        )
			int x = (GamePanel.WIDTH - MINIMAP_OFFSET - MINIMAP_SIZE/2) - (ROOM_SIZE/2) + (ROOM_SIZE * r.getPosition().getX()) + (r.getPosition().getX() * CORRIDOR_WIDTH);
			int y = (                  MINIMAP_OFFSET + MINIMAP_SIZE/2) - (ROOM_SIZE/2) + (ROOM_SIZE * r.getPosition().getY()) + (r.getPosition().getY() * CORRIDOR_WIDTH);
			
			query = this.getRoom(new Position(r.getPosition().getX()-1, r.getPosition().getY()));
			if (query != null && query.isOpen(Direction.RIGHT)) g.fillRect(x-CORRIDOR_WIDTH, y+ROOM_SIZE/2-CORRIDOR_HEIGHT/2, CORRIDOR_WIDTH, CORRIDOR_HEIGHT);
			query = this.getRoom(new Position(r.getPosition().getX(), r.getPosition().getY()-1));
			if (query != null && query.isOpen(Direction.DOWN))  g.fillRect(x+ROOM_SIZE/2-CORRIDOR_HEIGHT/2, y-CORRIDOR_WIDTH, CORRIDOR_HEIGHT, CORRIDOR_WIDTH);
			
			if(r.getPosition().equals(Player.DEFAULT_ROOM_POSITION)) g.setColor(Color.GREEN);
			if(r.getPosition().equals(p.getRoomPosition())) g.setColor(Color.YELLOW);
			g.fillRect(x, y, ROOM_SIZE, ROOM_SIZE);
		}
	}

}
