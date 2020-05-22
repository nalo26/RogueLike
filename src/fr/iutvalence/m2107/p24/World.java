package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.ressources.Images;

public class World extends GameState {

	/**
	 * Rooms of the World. We make a two-dimensional table because the world have
	 * many Rooms.
	 */
	public Room[][] rooms;

	/**
	 * The Player in the World.
	 */
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
		this.rooms = new Room[1][1];
		for (int i = 0; i < this.rooms.length; i++) {
			for (int j = 0; j < this.rooms[i].length; j++) {
				this.rooms[i][j] = new Room();
			}
		}

	}

	@Override
	public void tick() {
		this.player.tick();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawImage(Images.room14.getImage(), (int)GamePanel.WIDTH/2-GamePanel.HEIGHT/2, 0, GamePanel.HEIGHT, GamePanel.HEIGHT, null);
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

}
