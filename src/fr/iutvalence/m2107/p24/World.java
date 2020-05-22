package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
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
	 * Initialization of your game. We set the player and then we create all the
	 * rooms.
	 */
	public void init() {
		this.player = new Player();
		this.rooms = new ArrayList<Room>();
		this.rooms.add(new Room(new Position(0, 0), "1111"));
	}

	@Override
	public void tick() {
		this.player.tick();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        //this.getRoom(...).draw(g); //TODO current room
        this.rooms.get(0).draw(g);
		this.player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		this.player.keyPressed(k);
	}

	@Override
	public void keyReleased(int k) {
		this.player.keyReleased(k);
	}
	
	public Room getRoom(Position pos) {
		for(Room r : this.rooms) {
			if(r.getPosition() == pos) return r;
		}
		return null;
	}

}
