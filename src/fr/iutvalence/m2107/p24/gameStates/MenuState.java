package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.sun.glass.events.KeyEvent;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.World;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the main menu and all of his options.
 *
 */
public class MenuState extends GameState implements ImageObserver
{

	/** This array represent all of the possible options for the main menu. */
	private String[] options;

	/** This array represent all of the possible options for the death menu. */
	private int SelectedOption;

	private Graphics g;

	private boolean help;
	/**
	 * Initialize the possible options and the selector.
	 * @param gsm the manager wanted.
	 */
	public MenuState(final GameStateManager gsm) {
		super(gsm);
		this.options = new String[] { "New Game", "Load Save", "Help", "Quit" };
		this.SelectedOption = 0;
	}

	/** {@inheritDoc} */
	@Override
	public void tick() {
		// Override later.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(final Graphics g) {
		g.setColor(new Color(0, 0, 0));
		if(this.help) {
			g.drawImage(Images.JEUHELP.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		}
		else {
			g.drawImage(Images.MAIN_MENU_BACKGROUND.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, this);
		for (int i = 0; i < this.options.length; ++i) {
			if (i == this.SelectedOption) {
				g.setColor(new Color(51, 204, 255));
			} else {
				g.setColor(new Color(107, 107, 71));
			}
			g.setFont(new Font("Montserrat", 0, 72));
			g.drawString(this.options[i], GamePanel.WIDTH / 2 - 50, 250 + i * 150);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(final int k) {
		if (k == 40 || k == 83) {
			++this.SelectedOption;
			if (this.SelectedOption >= this.options.length) {
				this.SelectedOption = 0;
			}
		} else if (k == 38 || k == 90) {
			--this.SelectedOption;
			if (this.SelectedOption < 0) {
				this.SelectedOption = this.options.length - 1;
			}
		}
		if (k == 10) {
			if (this.SelectedOption == 0) {
				this.gsm1.getState().push(new World(this.gsm1));
			} else if (this.SelectedOption == 1) {
				World world = new World(this.gsm1);
				world.load();
				this.gsm1.getState().push(world);
			} else if (this.SelectedOption == 2) {
				// TODO help
				this.help = true;
				
		
				
			} else if (this.SelectedOption == 3) {
				System.exit(0);
			}
		}
		if(this.help && k==KeyEvent.VK_ESCAPE) this.help = false;
	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(final int k) {
		// Override later.
	}

	/** {@inheritDoc} */
	@Override
	public boolean imageUpdate(final Image arg0, final int arg1, final int arg2, final int arg3, final int arg4, final int arg5) {
		return false;
	}


	@Override
	protected void mousePressed(int button)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mouseReleased(int button)
	{
		// TODO Auto-generated method stub
		
	}

}