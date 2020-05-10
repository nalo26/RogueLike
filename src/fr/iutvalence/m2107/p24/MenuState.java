package fr.iutvalence.m2107.p24;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class MenuState extends GameState implements ImageObserver {

    public MenuState(final GameStateManager gsm) {
        super(gsm);
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