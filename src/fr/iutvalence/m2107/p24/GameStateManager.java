package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager
{
    private Stack<GameState> state;
    
    public GameStateManager() {
        (this.state = new Stack<GameState>()).push(new MenuState(this));
    }
    
    public void tick() {
        this.state.peek().tick();
    }
    
    public void draw(final Graphics g) {
        this.state.peek().draw(g);
    }
    
    public Stack<GameState> getState() {
        return this.state;
    }
    
    public void keyPressed(final int k) {
        this.state.peek().keyPressed(k);
    }
    
    public void keyReleased(final int k) {
        this.state.peek().keyReleased(k);
    }
}
