package fr.iutvalence.m2107.p24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.display.MiniMapDisplay;
import fr.iutvalence.m2107.p24.display.PlayerDisplay;
import fr.iutvalence.m2107.p24.display.RoomDisplay;
import fr.iutvalence.m2107.p24.gameStates.DeathState;
import fr.iutvalence.m2107.p24.gameStates.GameState;
import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
import fr.iutvalence.m2107.p24.gameStates.PauseState;

public class World extends GameState {

	/** The Player in the World. */
	private PlayerDisplay player;
	
	private MiniMapDisplay map;


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
		this.map = new MiniMapDisplay();
	}

	@Override
	public void tick() {
		this.player.tick();
		if (this.player.getHealth() <= 0) this.gsm1.getState().push(new DeathState(this.gsm1));
		this.map.tick(this.player);
	}

	@Override
	public void draw(Graphics g) {
		this.map.getRoom(this.player.getRoomPosition()).draw(g);
        g.setColor(Color.RED);
        g.drawString(this.player.getRoomPosition().toString(), 0, 100);
		this.player.draw(g);
		this.map.draw(g, this.player);
	}

	@Override
	public void keyPressed(int k) {
		this.player.keyPressed(k);
		if(k == KeyEvent.VK_ESCAPE) this.gsm1.getState().push(new PauseState(this.gsm1));
	}

	@Override
	public void keyReleased(int k) {
		this.player.keyReleased(k);
	}
	
	public PlayerDisplay getPlayer() {
		return this.player;
	}

}
