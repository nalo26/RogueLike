package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.ressources.Images;

/** Represent the main menu and all of his options. */
public class MenuState extends GameState implements ImageObserver {

	/** This array represent all of the possible options for the main menu. */
	private String[] options;
	/** The current selected option. */
	private int SelectedOption;
	
	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public MenuState(final GameStateManager gsm) {
		super(gsm);
		this.options = new String[] { "New Game", "Load Save", "Help", "Quit" };
		this.SelectedOption = 0;
	}

	@Override
	public void tick() {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(final Graphics g) {
		g.drawImage(Images.MAIN_MENU_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.setFont(new Font("Montserrat", 0, 72));
		FontMetrics metrics = g.getFontMetrics();
		for (int i = 0; i < this.options.length; ++i) {
			if (i == this.SelectedOption) g.setColor(new Color(51, 204, 255));
			else g.setColor(Color.WHITE);
			int x = (GamePanel.WIDTH - metrics.stringWidth(this.options[i]))/2;
			int y = i*(GamePanel.HEIGHT/this.options.length-1)+(GamePanel.HEIGHT/this.options.length)/2;
			g.drawString(this.options[i], x, y);
		}
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Draw the String
	    g.drawString(text, x, y); 
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(final int k) {
		if (k == 40 || k == 83) {
			this.SelectedOption++;
			if (this.SelectedOption >= this.options.length) this.SelectedOption = 0;
		} else if (k == 38 || k == 90) {
			this.SelectedOption--;
			if (this.SelectedOption < 0) this.SelectedOption = this.options.length - 1;
		}
		if (k == KeyEvent.VK_ENTER) {
			switch (this.SelectedOption) {
				case 0: // New Game
					this.gsm1.getState().push(new World(this.gsm1));
					break;
				case 1: // Load Save
					World world = new World(this.gsm1);
					world.load();
					this.gsm1.getState().push(world);
					break;
				case 2: // Help
					this.gsm1.getState().push(new HelpState(this.gsm1));
					break;
				case 3: // Quit
					System.exit(0);
				default: break;
			}
		}
		
	}

	@Override
	public void keyReleased(final int k) {
		// Not used here.
	}

	@Override
	protected void mousePressed(int button) {
		// Not used here.
	}

	@Override
	protected void mouseReleased(int button) {
		// Not used here.
	}

	@Override
	public boolean imageUpdate(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5) {
		return false;
	}
}