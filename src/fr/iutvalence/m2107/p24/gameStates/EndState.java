package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.ressources.Images;

public class EndState extends GameState implements ImageObserver{

	public EndState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.drawImage(Images.WIN_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.drawImage(Images.WIN.getImage(), 0, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT/2, this);
		
	}

	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyReleased(int p0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mousePressed(int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mouseReleased(int button) {
		// TODO Auto-generated method stub
		
	}

}
