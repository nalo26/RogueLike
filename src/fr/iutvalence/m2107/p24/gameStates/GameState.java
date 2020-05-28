package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Graphics;

/**
 * this class is the model to create class who can draw on the panel, take care of which keys is pressed / released on the keyboard, describe the behavior in game
 *
 */
public abstract class GameState {
	
	/**
	 * manage the different states of the game
	 */
    protected GameStateManager gsm1;
    
    /**
     * initialize the gameStateManager and call the init method to initialize extra fields
     * @param gsm the manager wanted
     */
    public GameState(final GameStateManager gsm) {
        this.gsm1 = gsm;
        this.init();
    }
    
    /**
     * initialize some extra fields (rarely used but can be useful sometimes)
     */
    public abstract void init();
    
    /**
     * instructions what are executed every tick
     */
    public abstract void tick();
    
    /**
     * describe how to draw the current state
     * @param p0 the object who allow to draw
     */
    public abstract void draw(final Graphics p0);
    
    /**
     * describe how to manage the current keys pressed
     * @param p0 the key value
     */
    public abstract void keyPressed(final int p0);
    
    /**
     * describe how to manage the current keys released
     * @param p0 the key value
     */
    public abstract void keyReleased(final int p0);
    
}