package fr.iutvalence.m2107.p24.rooms;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.GamePanel;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Boss;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.entities.Player;
import fr.iutvalence.m2107.p24.ressources.Images;
/**
 * The room where the boss is.
 */
public class BossRoom extends Room {
	
	/** The image of the room. */
	private BufferedImage image;
	/** The direction of the room. */
	private Direction direction;
	
	/** 
	 * Create a new Boss room.
	 * This is in this room that the player will fight against the boss. 
	 * This Room is always behind a Door Room.
	 * @param p The position of the room on the map.
	 * @param d The direction of the room.
	 */
	public BossRoom(Position p, Direction d) {
		super(p, computeDirection(d));
		this.mobs.clear();
		this.mobs.add(new Boss());
		this.decoration.clear();
		this.items.clear();
		
		this.direction = d;
		
		this.image = Images.valueOf("BOSS_ROOM_" + d).getImage();
	}
	
	/**
	 * Compute the doors of the room according to the direction.
	 * @param d The direction of the room.
	 * @return The String representation of the doors (i.e. "1010").
	 * @see #getDoors()
	 */
	private static String computeDirection(Direction d) {
		switch (d) {
			case UP:    return "1000";
			case RIGHT: return "0100";
			case DOWN:  return "0010";
			case LEFT:  return "0001";
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
	}
	
	@Override
	public void update(Player p) {
		this.setDoor(this.direction, true);
	}
	
	/**
	 * @return The direction of the room.
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
}
