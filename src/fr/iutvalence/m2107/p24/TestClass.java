package fr.iutvalence.m2107.p24;

import fr.iutvalence.m2107.p24.rooms.Room;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestClass extends TestCase
{
	public TestClass(String name) {
		super(name);
	}
	
	public void testRoom() {
		Room room1 = new Room(new Position(100, 200), "0110");
		Room room2 = new Room(new Position(101, 200), "0001");
		//assertEquals("La porte de droite existe", room1.isOpen(Direction.RIGHT), true);
		//assertEquals("La porte de gauche existe", room2.isOpen(Direction.LEFT), true);
		assertEquals("Les portes sont reliées", room1.isOpen(Direction.RIGHT), room2.isOpen(Direction.LEFT));
	}
	
	public void testPos() {
		Position pos1 = new Position(0,1);
		pos1.move(2, 0);
		assertEquals("Position égale", pos1, new Position(2,1));
	}
	
	public static Test suite() {
		TestSuite res = new TestSuite();
		res.addTest(new TestClass("testRoom"));
		res.addTest(new TestClass("testPos"));
		return res;
	}
}
