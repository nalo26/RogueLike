package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Graphics;
import java.util.Stack;

/**
 * this class take care of the current state of the game (menu, pause, dead, in game, ...)
 * 
 */
public class GameStateManager
{
	/**
	 * represent the current state of the game
	 */
    private Stack<GameState> state;
    
    /**
     * initialize the game and push the MenuState class at the top of stack
     */
    public GameStateManager() {
        (this.state = new Stack<GameState>()).push(new MenuState(this));
    }
    
    /**
     * look at which state is at the top of the stack and call his tick method
     */
    public void tick() {
        this.state.peek().tick();
    }
    
    /**
     * look at which state is at the top of the stack and draw it
     * @param g the object who allow to draw
     */
    public void draw(final Graphics g) {
        this.state.peek().draw(g);
    }
    
    /**
     * allow to know what is the current state
     * @return the current state
     */
    public Stack<GameState> getState() {
        return this.state;
    }
    
    /**
     * look at which key is pressed on the current state
     * @param k the key value
     */
    public void keyPressed(final int k) {
        this.state.peek().keyPressed(k);
    }
    
    /**
     * look at which key is released on the current state
     * @param k the key value
     */
    public void keyReleased(final int k) {
        this.state.peek().keyReleased(k);
    }
}
