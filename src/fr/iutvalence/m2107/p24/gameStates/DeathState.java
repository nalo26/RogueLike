package fr.iutvalence.m2107.p24.gameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.World;

/**
 * Represents the death menu and all of his possible options.
 */
public class DeathState extends GameState
{
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

	/** {@inheritDoc} */
	@Override
	public void tick() {
		// Override later.
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics p) {
		p.setColor(Color.BLACK);
		p.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		int j = 75;
		for (int i = 0; i < this.options.length; ++i) {
			if (i == this.SelectedOption) {
				p.setColor(new Color(0, 255, 128));
			} else {
				p.setColor(new Color(64, 64, 64));
			}

			p.setFont(new Font("Montserrat", 0, 72));
			p.drawString(this.options[i], GamePanel.WIDTH / 2 - 150 + j, 250 + i * 150);
			j = 0;
		}

	}

	/** {@inheritDoc} */
	@Override
	public void keyPressed(int k) {
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
				this.gsm1.getState().push(new MenuState(this.gsm1));
			} else if (this.SelectedOption == 2) {
				System.exit(0);
			}
		}

	}

	/** {@inheritDoc} */
	@Override
	public void keyReleased(int p0) {
		// Override later.
	}

}
