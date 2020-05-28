package fr.iutvalence.m2107.p24;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
/**
 * The main class to launch the game.
 */
public class RogueLikeLauncher {

	/**
	 * Create a frame and launch the game on it.
	 * @param args console arguments
	 */
    public static void main(final String[] args) {
        final GamePanel g = new GamePanel();
        final JFrame frame = new JFrame("RogueLike");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.add(g, "Center");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setExtendedState(Frame.NORMAL);
    }

}
