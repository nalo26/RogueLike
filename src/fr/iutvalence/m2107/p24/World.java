package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.gameStates.PauseState;
import fr.iutvalence.m2107.p24.ressources.Images;

public class World extends GameState {

	/** Rooms of the World. */
	public List<Room> rooms;

	/** The Player in the World. */
	public Player player;

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
		this.player = new Player();
		this.rooms = new ArrayList<Room>();
		this.rooms.add(new Room(new Position(0, 0), "1111"));
	}

	@Override
	public void tick() {
		this.player.tick();
        this.getRoom(this.player.getRoomPosition()).tick();
		if (this.player.getHealth() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));
		
		// Changing of room
		// TODO TP the player to the other side of the room
		// TODO better positions
		// TODO optimize getRoom() here (always same)
		if (this.player.getPosition().getX() <= 0) {
			this.player.getRoomPosition().move(-1, 0);
			if(this.getRoom(this.player.getRoomPosition()) == null) {
				this.rooms.add(new Room(this.player.getRoomPosition(), "1111"));
			}
		}
		if (this.player.getPosition().getX() >= GamePanel.WIDTH) {
			this.player.getRoomPosition().move(1, 0);
			if(this.getRoom(this.player.getRoomPosition()) == null) {
				this.rooms.add(new Room(this.player.getRoomPosition(), "1111"));
			}
		}
		if (this.player.getPosition().getY() <= 0) {
			this.player.getRoomPosition().move(0, -1);
			if(this.getRoom(this.player.getRoomPosition()) == null) {
				this.rooms.add(new Room(this.player.getRoomPosition(), "1111"));
			}
		}
		if (this.player.getPosition().getY() >= GamePanel.HEIGHT) {
			this.player.getRoomPosition().move(0, 1);
			if(this.getRoom(this.player.getRoomPosition()) == null) {
				this.rooms.add(new Room(this.player.getRoomPosition(), "1111"));
			}
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
	
	public Room getRoom(Position pos) {
		for(Room r : this.rooms) {
			if(r.getPosition().equals(pos)) return r;
		}
		return null;
	}

}
