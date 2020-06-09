package fr.iutvalence.m2107.p24;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import fr.iutvalence.m2107.p24.gameStates.GameStateManager;
/**
 * Represents the panel of the game, which represents the game window and what we're going to display on it..
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {	
	
    private static final long serialVersionUID = 1L;
    
    /** Get the size of the Screen. */
    //public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension screenSize = RogueLikeLauncher.frame.getSize();
    
    /** Set the Width at screenSize. */
    //public static int WIDTH = screenSize.width;
    public static final int DEFAULT_WIDTH = 1280;
    public static int WIDTH = DEFAULT_WIDTH;
    
    /** Set the Height at screenSize. */
    //public static int HEIGHT = screenSize.height;
    public static final int DEFAULT_HEIGHT = 720;
    public static int HEIGHT = DEFAULT_HEIGHT;
    
    @SuppressWarnings("unused")
	private Thread thread;
    
    /** Boolean for say if the game is running. */
    private boolean isRunning;
    
    /** The FramePerSecond of the player. */
    private int FPS;
    
    /** The travel time of your player  */
    private long travelTime;

    private GameStateManager gsm;
    
    
    /**
     * Constructor of the Panel.
     * Set not running the game, FPS at 60, the travelTime (1000 / the FPS), set the Size we prefer with new Dimensions, 
     * get the key we pressed, set the clickable part of the window, start the game
     */
    public GamePanel() {
        this.isRunning = false;
        this.FPS = 60;
        this.travelTime = 1000 / this.FPS;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.start();
    }
    
    /** When the game Start, set that the game is running. */
    private void start() {
        this.isRunning = true;
        (this.thread = new Thread(this)).start();
    }
    
    /** Triggered when a key is pressed. */
    @Override
    public void keyPressed(final KeyEvent e) {
        this.gsm.keyPressed(e.getKeyCode());
    }
    
    /** Triggered when a key is released. */
    @Override
    public void keyReleased(final KeyEvent e) {
        this.gsm.keyReleased(e.getKeyCode());
    }
    
    /** Triggered when a key is typed. */
    @Override
    public void keyTyped(final KeyEvent e) {
    	// Not used anywhere.
    }
    
    /**  Override method it's the implementation of runnable. */
    @Override
    public void run() {
        this.gsm = new GameStateManager();
        while (this.isRunning) {
            final long start = System.nanoTime();
            this.tick();
            this.repaint();
            final long elapsed = System.nanoTime() - start;
            long wait = this.travelTime - elapsed / 1000000L;
            if (wait <= 0L) {
                wait = 5L;
            }
            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /** That's what is refresh every tick. */
    public void tick() {
    	screenSize = RogueLikeLauncher.frame.getSize();
    	WIDTH = screenSize.width - 16;
    	HEIGHT = screenSize.height - 39;
    	this.gsm.tick();
    }
    
    /** This component allow to draw on the panel. */
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, 1280, 720);
        this.gsm.draw(g);
    }

 
	@Override
	public void mouseClicked(MouseEvent e) {
		//never used
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		//never used
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		//never used
		
	}
	 /** Triggered when the mouse is clicked. */
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.gsm.mousePressed(e.getButton());
		
	}
	 /** Triggered when the click is released. */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.gsm.mouseReleased(e.getButton());
		
	}
}