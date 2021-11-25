package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Graphics;

/**
 * This class is the model to create class who can draw on the panel, take care of which keys is pressed / released on the keyboard, describe the behavior in game.
 *
 */
public abstract class GameState {
	
	/** Manage the different states of the game. */
    protected GameStateManager gsm1;
    
    /**
     * Initialize the gameStateManager and call the init method to initialize extra fields.
     * @param gsm the manager wanted
     */
    public GameState(final GameStateManager gsm) {
        this.gsm1 = gsm;
    }
    
    /** Instructions what are executed every tick. */
    public abstract void tick();
    
    /**
     * Describe how to draw the current state.
     * @param p0 the object who allow to draw.
     */
    public abstract void draw(final Graphics p0);
    
    /**
     * Describe how to manage the current keys pressed.
     * @param p0 the key value.
     */
    public abstract void keyPressed(final int p0);
    
    /**
     * Describe how to manage the current keys released.
     * @param p0 the key value.
     */
    public abstract void keyReleased(final int p0);


    /** Describe how to manage the current button is pressed. 
     * @param button the pressed button.
     */
	protected abstract void mousePressed(int button);
	
	/** Describe how to manage the current button is released. 
     * @param button the released button.
     */
	protected abstract void mouseReleased(int button);
    
}