package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.MiniMap;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.rooms.Room;

public class FullMapState extends GameState {
	
	/** The mini-map of the game. */
	private MiniMap map;
	/** The player of the game. */
	private Player player;
	
	/** The width of the room. */
	private static final int ROOM_WIDTH = 80;
	/** The height of the room. */
	private static final int ROOM_HEIGHT = 60;
	/** The width of the corridor. */
	private static final int CORRIDOR_WIDTH = 20;
	/** The height of the corridor. */
	private static final int CORRIDOR_HEIGHT = 36;
	
	/**
	 * Constructor of of the state of the entire map.
	 * @param gsm the manager wanted.
	 * @param map the mini-map of the game.
	 * @param p the player of the game.
	 */
	public FullMapState(GameStateManager gsm, MiniMap map, Player p) {
		super(gsm);
		this.map = map;
		this.player = p;
	}

	@Override
	public void tick() {
		// Not used here.
	}

	/**
	 * Draw the map.
	 * @param g the draw component. 
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color((float)0, (float)0, (float)0, (float)0.5));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(Room r : this.map.getRooms()) {
			if(r.isVisited()) {
				Position pos = r.getPosition();
				g.setColor(Color.WHITE);
				
				//      (centering the map ) - ( offset room ) + (                  offset from room's coordinate                  ) + (                      gap for corridors if any                      )
				int x = (GamePanel.WIDTH /2) - (ROOM_WIDTH /2) + (ROOM_WIDTH  * (pos.getX() - this.player.getRoomPosition().getX())) + ((pos.getX() - this.player.getRoomPosition().getX()) * CORRIDOR_WIDTH);
				int y = (GamePanel.HEIGHT/2) - (ROOM_HEIGHT/2) + (ROOM_HEIGHT * (pos.getY() - this.player.getRoomPosition().getY())) + ((pos.getY() - this.player.getRoomPosition().getY()) * CORRIDOR_WIDTH);
				
				if(r.isOpen(Direction.UP))    g.fillRect(x+ROOM_WIDTH/2-CORRIDOR_HEIGHT/2, y-CORRIDOR_WIDTH, CORRIDOR_HEIGHT, CORRIDOR_WIDTH);				
				if(r.isOpen(Direction.RIGHT)) g.fillRect(x+ROOM_WIDTH, y+ROOM_HEIGHT/2-CORRIDOR_HEIGHT/2, CORRIDOR_WIDTH, CORRIDOR_HEIGHT);				
				if(r.isOpen(Direction.DOWN))  g.fillRect(x+ROOM_WIDTH/2-CORRIDOR_HEIGHT/2, y+ROOM_HEIGHT, CORRIDOR_HEIGHT, CORRIDOR_WIDTH);
				if (r.isOpen(Direction.LEFT)) g.fillRect(x-CORRIDOR_WIDTH, y+ROOM_HEIGHT/2-CORRIDOR_HEIGHT/2, CORRIDOR_WIDTH, CORRIDOR_HEIGHT);
				
				if(pos.equals(Player.DEFAULT_ROOM_POSITION)) g.setColor(Color.GREEN);
				if(pos.equals(this.player.getRoomPosition())) g.setColor(Color.YELLOW);
				g.fillRect(x, y, ROOM_WIDTH, ROOM_HEIGHT);
			}
		}
	}

	@Override
	public void keyPressed(int k) {
		// Leave the map.
		if(k == KeyEvent.VK_M || k == KeyEvent.VK_ESCAPE) this.gsm1.getState().pop();
	}

	@Override
	public void keyReleased(int k) {
		// Not used here.
	}

	@Override
	protected void mousePressed(int button) {
		// Not used here.
	}

	@Override
	protected void mouseReleased(int button) {
		// Not used here.
	}

}
