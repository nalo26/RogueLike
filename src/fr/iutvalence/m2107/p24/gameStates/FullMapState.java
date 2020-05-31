package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.MiniMap;
import fr.iutvalence.m2107.p24.Player;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Room;

public class FullMapState extends GameState {

	private MiniMap map;
	private Player player;
	
	/** The width of the room. */
	private static final int ROOM_WIDTH = 80;
	/** The height of the room. */
	private static final int ROOM_HEIGHT = 60;
	/** The width of the corridor. */
	private static final int CORRIDOR_WIDTH = 20;
	/** The height of the corridor. */
	private static final int CORRIDOR_HEIGHT = 36;

	public FullMapState(GameStateManager gsm, MiniMap map, Player p) {
		super(gsm);
		this.map = map;
		this.player = p;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color((float)0, (float)0, (float)0, (float)0.5));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		Room query = null;
		for(Room r : this.map.getRooms()) {
			g.setColor(Color.WHITE);
			
			//      (            centering on the mini map            ) - (offset room) + (  offset from room's coordinate   ) + (       gap for corridors if any        )
			int x = (GamePanel.WIDTH /2) - (ROOM_WIDTH /2) + (ROOM_WIDTH  * (r.getPosition().getX() - this.player.getRoomPosition().getX())) + ((r.getPosition().getX() - this.player.getRoomPosition().getX()) * CORRIDOR_WIDTH);
			int y = (GamePanel.HEIGHT/2) - (ROOM_HEIGHT/2) + (ROOM_HEIGHT * (r.getPosition().getY() - this.player.getRoomPosition().getY())) + ((r.getPosition().getY() - this.player.getRoomPosition().getY()) * CORRIDOR_WIDTH);
			
			query = this.map.getRoom(new Position(r.getPosition().getX()-1, r.getPosition().getY()));
			if (query != null && query.isOpen(Direction.RIGHT)) g.fillRect(x-CORRIDOR_WIDTH, y+ROOM_HEIGHT/2-CORRIDOR_HEIGHT/2, CORRIDOR_WIDTH, CORRIDOR_HEIGHT);
			query = this.map.getRoom(new Position(r.getPosition().getX(), r.getPosition().getY()-1));
			if (query != null && query.isOpen(Direction.DOWN))  g.fillRect(x+ROOM_WIDTH/2-CORRIDOR_HEIGHT/2, y-CORRIDOR_WIDTH, CORRIDOR_HEIGHT, CORRIDOR_WIDTH);
			
			if(r.getPosition().equals(Player.DEFAULT_ROOM_POSITION)) g.setColor(Color.GREEN);
			if(r.getPosition().equals(this.player.getRoomPosition())) g.setColor(Color.YELLOW);
			g.fillRect(x, y, ROOM_WIDTH, ROOM_HEIGHT);
		}
	}

	@Override
	public void keyPressed(int k) {
		// leave the map
		if(k == KeyEvent.VK_M || k == KeyEvent.VK_ESCAPE) this.gsm1.getState().pop();
		
	}

	@Override
	public void keyReleased(int k) {
		// not used
	}

}