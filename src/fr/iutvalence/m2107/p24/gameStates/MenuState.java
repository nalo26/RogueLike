package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.World;

public class MenuState extends GameState implements ImageObserver {

    public MenuState(final GameStateManager gsm) {
        super(gsm);
        this.gsm1.getState().push(new World(this.gsm1));
    }
    
    @Override
    public void init() {
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void draw(final Graphics g) {
    }
    
    @Override
    public void keyPressed(final int k) {
    }
    
    @Override
    public void keyReleased(final int k) {
    }
    
    @Override
    public boolean imageUpdate(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5) {
        return false;
    }
    
}