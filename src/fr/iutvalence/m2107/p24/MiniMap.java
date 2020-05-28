package fr.iutvalence.m2107.p24;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.iutvalence.m2107.p24.display.RoomDisplay;

public class MiniMap {

	/** Rooms of the World. */
	protected List<RoomDisplay> rooms;
	
	public MiniMap() {
		this.rooms = new ArrayList<RoomDisplay>();
		this.rooms.add(new RoomDisplay(new Position(0, 0), "1111"));
	}
	
	public void tick(Player p) {
		// Changing of room
		// TODO TP the player to the other side of the room
		// TODO better positions
		if (p.getPosition().getX() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.LEFT)) {
			p.getRoomPosition().move(-1, 0);
			p.getPosition().move(GamePanel.WIDTH/2, 0);
		}
		if (p.getPosition().getX() >= GamePanel.WIDTH && this.getRoom(p.getRoomPosition()).isOpen(Direction.RIGHT)) {
			p.getRoomPosition().move(1, 0);
			p.getPosition().move(-GamePanel.WIDTH/2, 0);
		}
		if (p.getPosition().getY() <= 0 && this.getRoom(p.getRoomPosition()).isOpen(Direction.UP)) {
			p.getRoomPosition().move(0, -1);
			p.getPosition().move(0, GamePanel.HEIGHT/2);
		}
		if (p.getPosition().getY() >= GamePanel.HEIGHT && this.getRoom(p.getRoomPosition()).isOpen(Direction.DOWN)) {
			p.getRoomPosition().move(0, 1);
			p.getPosition().move(0, -GamePanel.HEIGHT/2);
		}
		
		if(this.getRoom(p.getRoomPosition()) == null) {
			this.rooms.add(this.randomRoom(p.getRoomPosition()));
		}
        this.getRoom(p.getRoomPosition()).tick();
	}
	
	protected RoomDisplay getRoom(Position pos) {
		for(RoomDisplay r : this.rooms) {
			if(r.getPosition().equals(pos)) return r;
		}
		return null;
	}
	
	private RoomDisplay randomRoom(Position pos) {
		Random rdm = new Random();
		String doors = "";
		Room query = null;
		
		query = this.getRoom(new Position(pos.getX()+1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.LEFT)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()+1));
		if(query != null) {
			if(query.isOpen(Direction.DOWN)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX()-1, pos.getY()));
		if(query != null) {
			if(query.isOpen(Direction.RIGHT)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		query = this.getRoom(new Position(pos.getX(), pos.getY()-1));
		if(query != null) {
			if(query.isOpen(Direction.UP)) doors += "1";
			else doors += "0";
		} else doors += ""+rdm.nextInt(2);
		
		return new RoomDisplay(new Position(pos.getX(), pos.getY()), doors);
	}
	
}
