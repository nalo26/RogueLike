package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represents the death menu and all of his possible options.
 */
public class DeathState extends GameState implements ImageObserver {
	
	/** This array represent all of the possible options for the death menu. */
	private String[] options;
	/** Represent the current selected option. */
	private int SelectedOption;

	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public DeathState(GameStateManager gsm) {
		super(gsm);
		this.options = new String[] { "Retry", "Main menu", "Quit" };
		this.SelectedOption = 0;
	}

	@Override
	public void tick() {
		// not used here.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		g.drawImage(Images.DEATH_MENU_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.setFont(new Font("Montserrat", 0, 72));
		FontMetrics metrics = g.getFontMetrics();
		for (int i = 0; i < this.options.length; ++i) {
			if (i == this.SelectedOption) g.setColor(new Color(51, 204, 255));
			else g.setColor(Color.WHITE);
			int x = (GamePanel.WIDTH - metrics.stringWidth(this.options[i]))/2;
			int y = i*(GamePanel.HEIGHT/this.options.length-1)+(GamePanel.HEIGHT/this.options.length)/2+metrics.getHeight()/2;
			g.drawString(this.options[i], x, y);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(int k) {
		if (k == 40 || k == 83) {
			this.SelectedOption++;
			if (this.SelectedOption >= this.options.length) this.SelectedOption = 0;
		} else if (k == 38 || k == 90) {
			this.SelectedOption--;
			if (this.SelectedOption < 0) this.SelectedOption = this.options.length - 1;
		}
		if (k == KeyEvent.VK_ENTER) {
			switch (this.SelectedOption) {
				case 0: // Retry
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new World(this.gsm1));
					break;
				case 1: // Main Menu
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new MenuState(this.gsm1));
					break;
				case 2: // Exit
					System.exit(0);
				default: break;
			}
		}

	}

	@Override
	public void keyReleased(int p0) {
		// not used here.
	}


	@Override
	protected void mousePressed(int button){
		// not used here.
	}

	@Override
	protected void mouseReleased(int button) {
		// not used here.
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// not used here.
		return false;
	}

}
