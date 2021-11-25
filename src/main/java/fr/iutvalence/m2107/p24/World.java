package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.PlayerDisplay;
import fr.iutvalence.m2107.p24.display.XpDisplay;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.gameStates.DeathState;
import fr.iutvalence.m2107.p24.gameStates.EndState;
import fr.iutvalence.m2107.p24.gameStates.FullMapState;
import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.gameStates.PauseState;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;
import fr.iutvalence.m2107.p24.rooms.BossRoom;
import fr.iutvalence.m2107.p24.rooms.Room;

/**
 * Create the whole map.
 */
public class World extends GameState {

	/** The Player in the World. */
	private PlayerDisplay player;
	/** The minimap in the world. */
	private MiniMapDisplay map;
	
	private static final String[] KONAMIS = {"KILL", "LVLUP", "BOSS"};
	
	private String konamiInput = "";
	
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
		Room currentRoom = this.map.getRoomAt(this.player.getRoomPosition());
		
		this.player.tick(currentRoom);
		boolean isBossDead = this.map.tick(currentRoom, this.player);
		
		if(this.player.getHealth().getLife() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));
		if(isBossDead) this.gsm1.getState().push(new EndState(this.gsm1));
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		this.map.getRoomAt(this.player.getRoomPosition()).draw(g);
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

		this.konamiInput += (char) k;
		this.checkKonami();
	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(int k) {
		this.player.keyReleased(k);
	}

	private void checkKonami() {
		for(String kona : KONAMIS) {
			for(int i = 0; i < kona.length(); i++) {
				if(this.konamiInput.length() < kona.length()) break;
				if(this.konamiInput.charAt(this.konamiInput.length() - (kona.length() - i)) != kona.charAt(i)) break;

				if(kona == KONAMIS[0]) { // Kill all mobs
					this.map.getRoomAt(this.player.getRoomPosition()).getMobs().clear();
					this.konamiInput = "";
					return;
				}
				if(kona == KONAMIS[1]) { // Level up player
					this.player.addXp(XpDisplay.XP_WIDTH - this.player.getXp());
					this.konamiInput = "";
					return;
				}
				if(kona == KONAMIS[2]) { // TP to boss room
					for(Room r : this.map.getRooms()) {
						if(r instanceof BossRoom) {
							this.player.getRoomPosition().move(this.player.getRoomPosition().getX()+r.getPosition().getX(), this.player.getRoomPosition().getY()+r.getPosition().getY());
							break;
						}
					}
					this.konamiInput = "";
					return;
				}
			}
		}
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
		HashMap<String, Integer> inventory = new HashMap<String, Integer>();
		for(Slot s : this.player.getInventory().getItems()) {
			if(s.getItem() != null)	inventory.put(s.getItem().getImage().toString(), s.getQuantity());
			else inventory.put("", 0);
		}
		player.put("inventory", inventory);
		save.put("player", player);

		HashMap<String, Object> rooms = new HashMap<String, Object>();
		
		int i = 0;
		for(Room r: this.map.getRooms()) {
			Position p = r.getPosition();
			HashMap<String, Object> room = new HashMap<String, Object>();
			
			room.put("connections", r.getDoorsString());
			room.put("visited", r.isVisited());
			HashMap<String, Integer> roomPosition = new HashMap<String, Integer>();
			roomPosition.put("x", p.getX());
			roomPosition.put("y", p.getY());
			room.put("position", roomPosition);
			HashMap<String, Object> mobs = new HashMap<String, Object>();
			int j = 0;
			for(Mob m : r.getMobs()) {
				HashMap<String, Object> mob = new HashMap<String, Object>();
				mob.put("type", (m.getType() != null ? m.getType().toString() : "BOSS"));
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
			
			HashMap<String, Object> items = new HashMap<String, Object>();
			j = 0;
			for(Item it : r.getItems()) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("item", it.getImage().toString());
				HashMap<String, Integer> itemPos = new HashMap<String, Integer>();
				itemPos.put("x", it.getPos().getX());
				itemPos.put("y", it.getPos().getY());
				item.put("position", itemPos);
				
				items.put(""+j, item);
				j++;
			}
			room.put("items", items);
			
			HashMap<String, Object> decors = new HashMap<String, Object>();
			j = 0;
			for(HashMap.Entry<Position, Images> dec : r.getDecoration().entrySet()) {
				HashMap<String, Object> decor = new HashMap<String, Object>();
				decor.put("decor", dec.getValue().toString());
				HashMap<String, Integer> decorPos = new HashMap<String, Integer>();
				decorPos.put("x", dec.getKey().getX());
				decorPos.put("y", dec.getKey().getY());
				decor.put("position", decorPos);
				
				decors.put(""+j, decor);
				j++;
			}
			room.put("decors", decors);
			
			rooms.put(""+i, room);
			i++;
		}
		save.put("rooms", rooms);
		
		JSONObject saveJSON = new JSONObject(save);
		
		File file = new File("saves/save.json");
		String data = saveJSON.toString();
		
		try(BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
			for(Byte b : data.getBytes()) { //writing byte per byte, the String is to big to be write at once.
				writer.write(b);
				writer.flush();
			}
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
