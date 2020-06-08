package fr.iutvalence.m2107.p24.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.MiniMap;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.rooms.BossRoom;
import fr.iutvalence.m2107.p24.rooms.Room;
/**
 * Display the minimap.
 */
public class MiniMapDisplay extends MiniMap {
	
	/** The size of the minimap. */ 
	private static final int MINIMAP_SIZE = 250;
	/** The offset of the minimap. */
	private static final int MINIMAP_OFFSET = 15;
	/** The size of the room. */
	private static final int ROOM_SIZE = 10;
	/** The width of the corridor. */
	private static final int CORRIDOR_WIDTH = 3;
	/** The height of the corridor. */
	private static final int CORRIDOR_HEIGHT = 6;
	
	/** The width offset of the walls. */
	public static int offsetW = (int) (0.05 * GamePanel.WIDTH);
	/** The height offset of the walls. */
	public static int offsetH = (int) ((96/1088) * GamePanel.HEIGHT);
	/** The width of the door depending on the screen size. */
	public static int doorWidth = GamePanel.WIDTH*64/1920;
	/** The height of the door depending on the screen size. */
	public static int doorHeight = GamePanel.HEIGHT*64/1088 - 64;
	
	/** The bounds of the room depending on the direction. */
	public static HashMap<Direction, Rectangle> walls = new HashMap<Direction, Rectangle>();
	/** The bounds of the doors depending on the direction. */
	private static HashMap<Direction, Rectangle> doors = new HashMap<Direction, Rectangle>();
	/** The bounds of the paths of the map. */
	private static ArrayList<Rectangle> paths = new ArrayList<Rectangle>();
	
	/** The constructor call his super class MiniMap. */
	public MiniMapDisplay() {
		super();
		updateBounds();
	}
	
	/** Draw the minimap. 
	 * @param g the graphics to draw on.
	 * @param p the player of the game.
	 */
	public void draw(Graphics g, PlayerDisplay p) {
		updateBounds();
		
		Position minimapPosition = updatePosition();
		
		if(p.getBounds().intersects(new Rectangle(minimapPosition.getX(), minimapPosition.getY(), MINIMAP_SIZE, MINIMAP_SIZE))) {
			// if the player is under the map.
	        g.setColor(new Color((float)0, (float)0, (float)0, (float)0.5));
		} else g.setColor(Color.BLACK);
		
		g.fillRect(minimapPosition.getX(), minimapPosition.getY(), MINIMAP_SIZE, MINIMAP_SIZE);
		
		Room query = null;
		for(HashMap.Entry<Position, Room> entry : this.rooms.entrySet()) {
			Position pos = entry.getKey();
			Room room = entry.getValue();
			g.setColor(Color.WHITE);
			
			int roomOffsetX = pos.getX() - p.getRoomPosition().getX();
			int roomOffsetY = pos.getY() - p.getRoomPosition().getY();

			//      (            centering on the mini map            ) - (offset room) + (offset room coordinates) + (  gap for corridors if any  )
			int x = (GamePanel.WIDTH - MINIMAP_OFFSET - MINIMAP_SIZE/2) - (ROOM_SIZE/2) + (ROOM_SIZE * roomOffsetX) + (roomOffsetX * CORRIDOR_WIDTH);
			int y = (                  MINIMAP_OFFSET + MINIMAP_SIZE/2) - (ROOM_SIZE/2) + (ROOM_SIZE * roomOffsetY) + (roomOffsetY * CORRIDOR_WIDTH);
			
			query = this.getRooms().get(new Position(pos.getX()-1, pos.getY()));
			if (query != null && query.isOpen(Direction.RIGHT)) g.fillRect(x-CORRIDOR_WIDTH, y+ROOM_SIZE/2-CORRIDOR_HEIGHT/2, CORRIDOR_WIDTH, CORRIDOR_HEIGHT);
			query = this.getRooms().get(new Position(pos.getX(), pos.getY()-1));
			if (query != null && query.isOpen(Direction.DOWN))  g.fillRect(x+ROOM_SIZE/2-CORRIDOR_HEIGHT/2, y-CORRIDOR_WIDTH, CORRIDOR_HEIGHT, CORRIDOR_WIDTH);
			
			if(pos.equals(Player.DEFAULT_ROOM_POSITION)) g.setColor(Color.GREEN);
			if(pos.equals(p.getRoomPosition())) g.setColor(Color.YELLOW);
			if(room instanceof BossRoom) g.setColor(Color.MAGENTA);
			
			g.fillRect(x, y, ROOM_SIZE, ROOM_SIZE);
		}
	}


	/**
	 * Update the bounds every tick, depending on the screen size.
	 */
	public static void updateBounds() {
		//walls
		offsetW = (int) (0.05 * GamePanel.WIDTH);
		offsetH = (int) (96/(float)1088 * GamePanel.HEIGHT);
		walls.put(Direction.UP, new Rectangle(0, 0, GamePanel.WIDTH, 1));
		walls.put(Direction.LEFT, new Rectangle(0, 0, offsetW, GamePanel.HEIGHT));
		walls.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH - offsetW, 0, offsetW, GamePanel.HEIGHT));
		walls.put(Direction.DOWN, new Rectangle(0, GamePanel.HEIGHT - offsetH, GamePanel.WIDTH, offsetH));
		
		//doors
		doorWidth = GamePanel.WIDTH*64/1920;
		doorHeight = GamePanel.HEIGHT*64/1088;
		doors.put(Direction.UP, new Rectangle(GamePanel.WIDTH/2 - doorWidth/2, 0, doorWidth, offsetH));
		doors.put(Direction.LEFT, new Rectangle(0, GamePanel.HEIGHT/2 - doorHeight, offsetW, doorHeight));
		doors.put(Direction.RIGHT, new Rectangle(GamePanel.WIDTH - offsetW, GamePanel.HEIGHT/2 - doorHeight, offsetW, doorHeight));
		doors.put(Direction.DOWN, new Rectangle(GamePanel.WIDTH/2 - doorWidth/2, GamePanel.HEIGHT - offsetH, doorWidth, offsetH));
		
		//paths
		paths.add(new Rectangle(GamePanel.WIDTH/2 - doorWidth/2, 0, doorWidth, GamePanel.HEIGHT));
		paths.add(new Rectangle(0, GamePanel.HEIGHT/2 - doorHeight, GamePanel.WIDTH, doorHeight));
		
	}
	/**
	 * Update the position of the mini-map compare to the game size
	 * @return a new position.
	 */
	private static Position updatePosition() {
		return new Position(GamePanel.WIDTH - MINIMAP_OFFSET - MINIMAP_SIZE, MINIMAP_OFFSET);
	}
	/**
	 * @param rect the rectangle who represent the mini-map.
	 * @return if the mini-map can be created.
	 */
	public static boolean canBeCreatedAt(Rectangle rect) {
		return !(rect.intersects(getWallBoundFromKey(Direction.UP)) || 
		  rect.intersects(getWallBoundFromKey(Direction.RIGHT)) ||
		  rect.intersects(getWallBoundFromKey(Direction.DOWN)) ||
		  rect.intersects(getWallBoundFromKey(Direction.LEFT)) ||
		  rect.intersects(paths.get(0)) || rect.intersects(paths.get(1)));
	}
	
	/**
	 * Give a bound of a wall depending on a given direction.
	 * @param key the direction on what you want to know the bound.
	 * @return the bound corresponding to the given direction.
	 */
	public static Rectangle getWallBoundFromKey(Direction key) {
		return walls.get(key);
	}
	
	/**
	 * Give a bound of a door depending on a given direction.
	 * @param key the on what you want to know the bound.
	 * @return the bound corresponding to the given direction.
	 */
	public static Rectangle getDoorBoundFromKey(Direction key)	{
		return doors.get(key);
	}

}
