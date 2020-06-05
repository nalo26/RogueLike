package fr.iutvalence.m2107.p24.items;

import java.util.Random;

import fr.iutvalence.m2107.p24.Position;

public enum ItemsList {
	
	POTION_HEALTH(HealthPotion.PROBABILITY),
	POTION_SPEED(SpeedPotion.PROBABILITY);

	public float probability;
	
	ItemsList(float prob) {
		this.probability = prob;
	}

	public static Item randomItem() {
		ItemsList[] allItems = ItemsList.values();
		Random random = new Random();
		switch(allItems[random.nextInt(allItems.length)]) {
			case POTION_HEALTH: return (random.nextFloat() >= HealthPotion.PROBABILITY ? new HealthPotion(new Position(0,0)) : null);
			case POTION_SPEED: return (random.nextFloat() >= SpeedPotion.PROBABILITY ? new SpeedPotion(new Position(0,0)) : null);
			default: return null;
		}
	}
}
