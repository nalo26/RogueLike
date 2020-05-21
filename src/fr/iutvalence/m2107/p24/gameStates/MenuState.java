package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.World;

public class MenuState extends GameState implements ImageObserver {

	  private String[] options;
	  private int SelectedOption;
	
    public MenuState(final GameStateManager gsm) {
        super(gsm);
        this.options = new String[] { "Start", "Help", "Quit" };
        this.SelectedOption = 0;
    }
    
    @Override
    public void init() {
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public void draw(final Graphics g) {
    	 g.setColor(new Color(255, 0, 102));
         g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
         for (int i = 0; i < this.options.length; ++i) {
             if (i == this.SelectedOption) {
                 g.setColor(new Color(51, 204, 255));
             }
             else {
                 g.setColor(new Color(107, 107, 71));
             }
             g.setFont(new Font("Montserrat", 0, 72));
             g.drawString(this.options[i], GamePanel.WIDTH/ 2 - 50, 250 + i * 150);
         }
    }
    
    
    @Override
    public void keyPressed(final int k) {
    	 if (k == 40 || k == 83) {
             ++this.SelectedOption;
             if (this.SelectedOption >= this.options.length) {
                 this.SelectedOption = 0;
             }
         }
         else if (k == 38 || k == 90) {
             --this.SelectedOption;
             if (this.SelectedOption < 0) {
                 this.SelectedOption = this.options.length - 1;
             }
         }
         if (k == 10) {
             if (this.SelectedOption == 0) {
                 this.gsm1.getState().push(new World(this.gsm1));
             }
             else if(this.SelectedOption == 1){
             	//help
             }
             else if (this.SelectedOption == 2 ) {
                 System.exit(0);
             }
         }
    }
    
    @Override
    public void keyReleased(final int k) {
    }
    
    @Override
    public boolean imageUpdate(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5) {
        return false;
    }
    
}