package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Graphics;

public abstract class GameState
{
    protected GameStateManager gsm1;
    public static double xOffset;
    public static double yOffset;
    
    public GameState(final GameStateManager gsm) {
        this.gsm1 = gsm;
        GameState.xOffset = 0.0;
        GameState.yOffset = 0.0;
        this.init();
    }
    
    public abstract void init();
    
    public abstract void tick();
    
    public abstract void draw(final Graphics p0);
    
    public abstract void keyPressed(final int p0);
    
    public abstract void keyReleased(final int p0);
    
}