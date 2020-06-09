package fr.iutvalence.m2107.p24;

public class Xp
{
	protected float xp;
	
	protected boolean lvlUp;
	
	public Xp(float theXp) {
		this.xp = theXp;
		this.lvlUp = false;
	}

	public void addXp(float xp) {
		this.xp += xp;
	}
	public float getXp()
	{
		return this.xp;
	}
	

	
	
}
