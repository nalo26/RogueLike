package fr.iutvalence.m2107.p24.rooms;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.KeyLockPosition;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.items.Key;
import fr.iutvalence.m2107.p24.ressources.Images;

public class KeyRoom extends Room {
	
	private static int KEY_WIDTH = Math.round((float)32/(float)1920*GamePanel.WIDTH);
	private static final BufferedImage KEY_WALL = Images.KEY_WALL.getImage();
	
	/** The image of the room. */
	private BufferedImage image;
	/** The direction of the room. */
	private Direction direction;
	/** The keys that are on the locks. */
	private Key[] keys;
	
	/** 
	 * Create a new keys room.
	 * This is in this room that the player must put
	 * all 4 keys he will found on the dungeon. 
	 * @param p The position of the room on the map.
	 * @param d The direction of the room.
	 */
	public KeyRoom(Position p, Direction d) {
		super(p, computeDirection(d));
		this.decor.clear();
		this.items.clear();
		this.direction = d;
		this.keys = new Key[4];
		
		this.keys[0] = new Key();
		this.keys[1] = new Key();
		this.keys[2] = new Key();
		this.keys[3] = new Key();
		
		this.image = Images.valueOf("KEY_ROOM_CLOSE_" + this.direction).getImage();
	}
	
	
	/**
	 * Compute the doors of the room according to the direction.
	 * @param d The direction of the room.
	 * @return The String representation of the doors (i.e. "1010").
	 * @see #getDoors()
	 */
	private static String computeDirection(Direction d) {
		switch (d) {
			case UP:    return "1010";
			case RIGHT: return "0101";
			case DOWN:  return "1010";
			case LEFT:  return "0101";
			default: return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		
		for(Mob m : this.mobs) {
			m.draw(g);
		}
		
		for(int i = 0; i < this.keys.length; i++) {
			if(this.keys[i] != null) {
				double rot;
				switch(this.direction) {
					case UP:
						rot = Math.toRadians(0);
						break;
					case RIGHT:
						rot = Math.toRadians(90);
						break;
					case DOWN:
						rot = Math.toRadians(180);
						break;
					case LEFT:
						rot = Math.toRadians(270);
						break;
					default: rot = 0;
				}
				
				Position pos = KeyLockPosition.valueOf(this.direction + "_" + i).getPosition().copy();
				AffineTransform tx = AffineTransform.getRotateInstance(rot, KEY_WALL.getWidth()/2, KEY_WALL.getHeight()/2);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op.filter(KEY_WALL, null), pos.getX(), pos.getY(), KEY_WIDTH, KEY_WIDTH, null);
			}
		}
	}
	
	/** {@inheritDoc} */
	@Override
	protected void update(Player p) {
		if(isOpen()) this.image = Images.valueOf("KEY_ROOM_OPEN_"+ this.direction).getImage();
		
		KEY_WIDTH = Math.round((float)32/(float)1920*GamePanel.WIDTH);
	}
	
	/**
	 * Checks if all the keys are on the locks.
	 * @return <tt>true</tt> if all keys are here, <tt>false</tt> else.
	 */
	private boolean isOpen() {
		for(Key k : this.keys) {
			if (k == null) return false;
		}
		return true;
	}
	
}
