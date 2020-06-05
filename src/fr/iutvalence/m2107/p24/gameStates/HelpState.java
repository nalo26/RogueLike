package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.ressources.Images;

/** Represent the main menu and all of his options. */
public class HelpState extends GameState implements ImageObserver {


	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public HelpState(GameStateManager gsm) {
		super(gsm);
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(final Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.drawImage(Images.MAIN_MENU_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.drawImage(Images.JEUHELP.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(final int k) {
		this.gsm1.getState().pop();
	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(final int k) {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	public boolean imageUpdate(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5) {
		return false;
	}


	@Override
	protected void mousePressed(int button) {
		// Not used here.
	}

	@Override
	protected void mouseReleased(int button) {
		// Not used here.
	}

}