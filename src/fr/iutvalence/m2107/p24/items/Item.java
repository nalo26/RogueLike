package fr.iutvalence.m2107.p24.items;

import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.ressources.Images;

/**
 * Represent the different characteristics of an item.
 *
 */
public abstract class Item {
	/** The id of the item. */
	protected int id;
	/** The spawn probability of the item. */
	protected int probability;
	/** The position of the item. */
	protected Position position;
	/**
	 * Constructor.
	 * @param type name of the item.
	 * @param id id of the item.
	 * @param prob the probability of spawn of the item.
	 * @param pos the position of the item on a room.
	 */
	public Item(ItemsList type, int id, int prob, Position pos) {
		try {
			Class<?> clazz = Class.forName(type.toString());
			Constructor<?> ctor = clazz.getConstructor(String.class);
			Object object = ctor.newInstance(); //TODO i'm not quite sure about that...
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("No class name '" + type.toString() + "'");
		}
		this.id = id;
		this.probability = prob;
		this.position = pos;
		
	}
	
	public int getProbabilty()	{
		return this.probability;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Position getPos() {
		return this.position;
	}
	
	public abstract Rectangle getBounds();
	
	public abstract void changeImage(Images i);
	
	public void setPosition(int x, int y) {
		this.position = new Position(x,y);
	}
}
