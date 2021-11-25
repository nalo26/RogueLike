package fr.iutvalence.m2107.p24;

import fr.iutvalence.m2107.p24.rooms.Room;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestClass extends TestCase {
	
	public TestClass(String name) {
		super(name);
	}
	
	/**
	 * Create a test on the class Room.
	 * Creating two room next to each other with doors,
	 * testing these doors, and testing if they are connected.
	 * @see Room
	 */
	public void testRoom() {
		Room room1 = new Room(new Position(100, 200), "0110");
		Room room2 = new Room(new Position(101, 200), "0001");
		assertTrue("The right door of room 1 exists.", room1.isOpen(Direction.RIGHT));
		assertTrue("The left door of room 2 exists.", room2.isOpen(Direction.LEFT));
		assertEquals("The two rooms are connected.", room1.isOpen(Direction.RIGHT), room2.isOpen(Direction.LEFT));
	}
	
	/**
	 * Create a test on the class Position.
	 * Creating a position, moving it, and test if it is successfully moved.
	 * @see Position
	 */
	public void testPos() {
		Position pos1 = new Position(0,1);
		pos1.move(2, 0); //x=0 & y=1  +  x=2 & y=0  =  x=2 & y=1
		assertEquals("Positions are equals", pos1, new Position(2,1));
	}
	
	public static Test suite() {
		TestSuite res = new TestSuite();
		res.addTest(new TestClass("testRoom"));
		res.addTest(new TestClass("testPos"));
		return res;
	}
}
