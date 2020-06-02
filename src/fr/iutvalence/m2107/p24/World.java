package fr.iutvalence.m2107.p24;

import org.json.simple.JSONObject;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.MobDisplay;
import fr.iutvalence.m2107.p24.display.PlayerDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.gameStates.DeathState;
import fr.iutvalence.m2107.p24.gameStates.FullMapState;
import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.gameStates.PauseState;

/**
 * Create the whole map.
 */
public class World extends GameState {

	/** The Player in the World. */
	private PlayerDisplay player;
	/** The minimap in the world. */
	private MiniMapDisplay map;
	
	

	/**
	 * Constructor of the World.
	 * @param gsm State of the game.
	 */
	public World(GameStateManager gsm) {
		super(gsm);
		this.player = new PlayerDisplay();
		this.map = new MiniMapDisplay();
	
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		RoomDisplay currentRoom = this.map.getRoom(this.player.getRoomPosition());
		
		this.player.tick(currentRoom);
		this.map.tick(this.player);

		for (MobDisplay m : currentRoom.getMobs()) {
			if (m.getBounds().intersects(this.player.getBounds())) {
				this.player.getHealth().setHealth(-1);
				this.player.setTakingDmg(true);
				this.player.collision(m);
				
			}
		} 
		if(this.player.getHealth().getLife() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));

	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		this.map.getRoom(this.player.getRoomPosition()).draw(g);
		this.player.draw(g);
		this.map.draw(g, this.player);
	
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(int k) {
		this.player.keyPressed(k);
		if (k == KeyEvent.VK_ESCAPE)
			this.gsm1.getState().push(new PauseState(this.gsm1));
		if (k == KeyEvent.VK_M)
			this.gsm1.getState().push(new FullMapState(this.gsm1, this.map, this.player));
	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(int k) {
		this.player.keyReleased(k);
	}

	/**
	 * Allow to know the current player.
	 * @return the current player.
	 */
	public PlayerDisplay getPlayer() {
		return this.player;
	}
	
	public static Position updatePosition(Position p) {
		int x = Math.round((float)GamePanel.WIDTH/(float)GamePanel.DEFAULT_WIDTH * p.getX());
		int y = Math.round((float)GamePanel.HEIGHT/(float)GamePanel.DEFAULT_HEIGHT * p.getY());
		return new Position(x, y);
	}

	public static void save() {
		JSONObject save = new JSONObject();
		
	}
}
