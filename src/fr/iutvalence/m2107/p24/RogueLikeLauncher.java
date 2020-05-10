package fr.iutvalence.m2107.p24;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;

public class RogueLikeLauncher {

    public static void main(final String[] args) {
        final GamePanel g = new GamePanel();
        final JFrame frame = new JFrame("Platformer");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.add(g, "Center");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

}
