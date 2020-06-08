package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
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
		MiniMapDisplay.updateBounds();
		this.player = new PlayerDisplay();
		this.map = new MiniMapDisplay();
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		RoomDisplay currentRoom = this.map.getRooms().get(this.player.getRoomPosition());
		
		this.player.tick(currentRoom);
		this.map.tick(currentRoom, this.player);
		
		if(this.player.getHealth().getLife() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		this.map.getRooms().get(this.player.getRoomPosition()).draw(g);
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
	 * Update the position of our object with the width and height of your game.
	 * @param p the position of our object.
	 * @return a new position(x and y)
	 */
	public static Position updatePosition(Position p) {
		int x = Math.round((float)GamePanel.WIDTH/(float)GamePanel.DEFAULT_WIDTH * p.getX());
		int y = Math.round((float)GamePanel.HEIGHT/(float)GamePanel.DEFAULT_HEIGHT * p.getY());
		return new Position(x, y);
	}

	/**
	 * Save all the things need to be saved, so we can later play at the same status we were at when we backed up.
	 */
	public void save() {
		HashMap<String, HashMap<String, Object>> save = new HashMap<String, HashMap<String, Object>>();
		
		HashMap<String, Object> player = new HashMap<String, Object>();
		
		player.put("direction", this.player.getDirection().toString());
		player.put("watchingAt", this.player.getWatching().toString());
		player.put("health", this.player.getHealth().getLife());
		player.put("damage", this.player.getDamage());
		HashMap<String, Integer> playerPosition = new HashMap<String, Integer>();
		playerPosition.put("x", this.player.getPosition().getX());
		playerPosition.put("y", this.player.getPosition().getY());
		player.put("position", playerPosition);
		HashMap<String, Integer> playerRoomPosition = new HashMap<String, Integer>();
		playerRoomPosition.put("x", this.player.getRoomPosition().getX());
		playerRoomPosition.put("y", this.player.getRoomPosition().getY());
		player.put("roomPosition", playerRoomPosition);
		player.put("inventory", this.player.getInventory().getItems());
		save.put("player", player);

		HashMap<String, Object> rooms = new HashMap<String, Object>();
		
		int i = 0;
		for(HashMap.Entry<Position, RoomDisplay> entry : this.map.getRooms().entrySet()) {
			Position p = entry.getKey();
			RoomDisplay r = entry.getValue();
			HashMap<String, Object> room = new HashMap<String, Object>();
			
			room.put("connections", r.getDoorsString());
			HashMap<String, Integer> roomPosition = new HashMap<String, Integer>();
			roomPosition.put("x", p.getX());
			roomPosition.put("y", p.getY());
			room.put("position", roomPosition);
			HashMap<String, Object> mobs = new HashMap<String, Object>();
			int j = 0;
			for(Mob m : r.getMobs()) {
				HashMap<String, Object> mob = new HashMap<String, Object>();
				mob.put("type", m.getType().toString());
				mob.put("health", m.getHealth().getLife());
				mob.put("damage", m.getDamage());
				mob.put("direction", m.getWatching().toString());
				HashMap<String, Integer> mobPosition = new HashMap<String, Integer>();
				mobPosition.put("x", m.getPosition().getX());
				mobPosition.put("y", m.getPosition().getY());
				mob.put("position", mobPosition);
				
				mobs.put(""+j, mob);
				j++;
			}
			room.put("mobs", mobs);
			
			rooms.put(""+i, room);
			i++;
		}
		save.put("rooms", rooms);
		
		JSONObject saveJSON = new JSONObject(save);
		FileWriter file;
		try {
			file = new FileWriter("saves/save.json");
			file.write(saveJSON.toString());
			file.flush();
			saveJSON.writeJSONString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * Calls all functions that have been created to load the saved progress.
 */
	public void load() {
		JSONParser parser = new JSONParser();
		
		try {
			FileReader file = new FileReader("saves/save.json");
			Object obj = parser.parse(file);
			JSONObject save = (JSONObject) obj;
			
			this.player.load((JSONObject) save.get("player"));
			this.map.load((JSONObject) save.get("rooms"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	  /** Triggered when the mouse is clicked. */
	@Override
	protected void mousePressed(int button)	{
		this.player.mousePressed(button);
	}
	 /** Triggered when the click is released. */
	@Override
	protected void mouseReleased(int button) {
		this.player.mouseReleased(button);
	}
}
