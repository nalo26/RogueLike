package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.display.PlayerDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.gameStates.DeathState;
import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.gameStates.PauseState;

public class World extends GameState {

	/** Rooms of the World. */
	public List<RoomDisplay> rooms;

	/** The Player in the World. */
	public PlayerDisplay player;


	/**
	 * Constructor of the World.
	 * @param gsm State of the game.
	 */
	public World(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	/**
	 * Initialization of your game. We set the player and then we create a room.
	 */
	public void init() {
		this.player = new PlayerDisplay();
		this.rooms = new ArrayList<RoomDisplay>();
		this.rooms.add(new RoomDisplay(new Position(0, 0), "1111"));
	}

	@Override
	public void tick() {
		this.player.tick();
        this.getRoom(this.player.getRoomPosition()).tick();
		if (this.player.getHealth() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));
		
		// Changing of room
		// TODO TP the player to the other side of the room
		// TODO better positions
		if (this.player.getPosition().getX() <= 0) 				  this.player.getRoomPosition().move(-1, 0);
		if (this.player.getPosition().getX() >= GamePanel.WIDTH)  this.player.getRoomPosition().move(1, 0);
		if (this.player.getPosition().getY() <= 0) 				  this.player.getRoomPosition().move(0, -1);
		if (this.player.getPosition().getY() >= GamePanel.HEIGHT) this.player.getRoomPosition().move(0, 1);
		
		if(this.getRoom(this.player.getRoomPosition()) == null) {
			this.rooms.add(randomRoom(this.player.getRoomPosition()));
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        this.getRoom(this.player.getRoomPosition()).draw(g);
        g.setColor(Color.RED);
        g.drawString(this.player.getRoomPosition().toString(), 0, 100);
		this.player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		this.player.keyPressed(k);
		if(k== KeyEvent.VK_ESCAPE) this.gsm1.getState().push(new PauseState(this.gsm1));
		
	}

	@Override
	public void keyReleased(int k) {
		this.player.keyReleased(k);
	}
	
	private RoomDisplay getRoom(Position pos) {
		for(RoomDisplay r : this.rooms) {
			if(r.getPosition().equals(pos)) return r;
		}
		return null;
	}
	
	private RoomDisplay randomRoom(Position pos) {
		Random rdm = new Random();
		String doors = "";
		Room query = null;
		
		query = this.getRoom(new Position(pos.getX()+1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.LEFT)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()+1));
		if(query != null) {
			if(query.isOpen(Direction.DOWN)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX()-1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.RIGHT)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()-1));
		if(query != null) {
			if(query.isOpen(Direction.UP)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		return new RoomDisplay(new Position(pos.getX(), pos.getY()), doors);
	}

}
