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
 * Represent the pause menu and his possible options.
 */
public class PauseState extends GameState implements ImageObserver {

	/** This array represent all of the possible options for the pause menu. */
	private String[] options;
	
	/** This array represent all of the possible options for the death menu. */
	private int selectedOption;

	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public PauseState(GameStateManager gsm) {
		super(gsm);
		this.options = new String[] { "Resume", "Retry", "Save Game", "Help", "Main Menu" };
		this.selectedOption = 0;
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		g.drawImage(Images.PAUSE_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.setFont(new Font("Montserrat", 0, 72));
		FontMetrics metrics = g.getFontMetrics();
		for (int i = 0; i < this.options.length; ++i) {
			if (i == this.selectedOption) g.setColor(new Color(51, 204, 255));
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
			this.selectedOption++;
			if (this.selectedOption >= this.options.length) this.selectedOption = 0;
		} else if (k == 38 || k == 90) {
			this.selectedOption--;
			if (this.selectedOption < 0) this.selectedOption = this.options.length - 1;
		}
		if (k == KeyEvent.VK_ENTER) {
			switch (this.selectedOption) {
				case 0: // Continue
					this.gsm1.getState().pop();
					break;
				case 1: // Retry
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new World(this.gsm1));
					break;
				case 2: // Save
					this.gsm1.getState().pop();
					((World) this.gsm1.getState().peek()).save();
					break;
				case 3: // Help
					this.gsm1.getState().push(new HelpState(this.gsm1));
					break;
				case 4: // Main Menu
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new MenuState(this.gsm1));
					break;
				default: break;
			}
		}

	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(int p) {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	protected void mousePressed(int button){
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	protected void mouseReleased(int button){
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}
