package fr.iutvalence.m2107.p24;

public class Xp
{
	protected float xp;
	
	public Xp(float theXp) {
		this.xp = theXp;
	}

	public void addXp(float xp) {
		this.xp += xp;
	}
	public float getXp()
	{
		return this.xp;
	}
	
	
}
