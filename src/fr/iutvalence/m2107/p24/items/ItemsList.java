package fr.iutvalence.m2107.p24.items;

import java.util.Random;

public enum ItemsList {
	
	POTION_HEALTH(new HealthPotion()),
	POTION_SPEED(new SpeedPotion()),
	POTION_POISON(new PoisonPotion()),
	KEY(new Key());

	public Item item;
	
	ItemsList(Item i) {
		this.item = i;
	}

	public static Item randomItem() {
		ItemsList[] allItems = ItemsList.values();
		Random random = new Random();
		switch(allItems[random.nextInt(allItems.length)]) {
			case POTION_HEALTH: return (random.nextFloat() >= HealthPotion.PROBABILITY ? new HealthPotion() : null);
			case POTION_SPEED: return (random.nextFloat() >= SpeedPotion.PROBABILITY ? new SpeedPotion(): null);
			case POTION_POISON: return (random.nextFloat() >= PoisonPotion.PROBABILITY ? new PoisonPotion() : null);
			default: return null;
		}
	}
}
