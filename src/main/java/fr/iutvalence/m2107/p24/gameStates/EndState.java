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
 * Represents the end menu when we finish the game.
 */
public class EndState extends GameState implements ImageObserver {
	
	private String[] options;
	private int selectedOption;
	
	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public EndState(GameStateManager gsm) {
		super(gsm);
		this.options = new String[] { "", "Continue", "New Game", "Main Menu" };
		this.selectedOption = 1;
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
		g.drawImage(Images.WIN_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		g.drawImage(Images.WIN.getImage(), GamePanel.WIDTH/2-Images.WIN.getImage().getWidth()/2, 50, this);
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
			if (this.selectedOption >= this.options.length) this.selectedOption = 1;
		} else if (k == 38 || k == 90) {
			this.selectedOption--;
			if (this.selectedOption < 1) this.selectedOption = this.options.length - 1;
		}
		if (k == KeyEvent.VK_ENTER) {
			switch (this.selectedOption) {
				case 1: // Continue
					this.gsm1.getState().pop();
					break;
				case 2: // New Game
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new World(this.gsm1));
					break;
				case 3: // Main Menu
					this.gsm1.getState().clear();
					this.gsm1.getState().push(new MenuState(this.gsm1));
					break;
				default: break;
			}
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void keyReleased(int p0) {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	protected void mousePressed(int button) {
		// Not used here.
	}

	/** {@inheritDoc} */
	@Override
	protected void mouseReleased(int button) {
		// Not used here.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		return false;
	}

}
