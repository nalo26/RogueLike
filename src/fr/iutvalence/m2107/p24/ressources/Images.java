package fr.iutvalence.m2107.p24.ressources;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public enum Images {
	PLAYER_RIGHT("player_right.png"),
	PLAYER_DAMAGE_RIGHT("player_damage_right.png"),
	PLAYER_ATTACK_RIGHT("player_attack_right.png"),
	PLAYER_LEFT("player_left.png"),
	PLAYER_DAMAGE_LEFT("player_damage_left.png"),
	PLAYER_ATTACK_LEFT("player_attack_left.png"),
	
	SLIME_GREEN_DOWN("blob_green_down.png"),
	SLIME_GREEN_UP("blob_green_up.png"),
	SLIME_RED_DOWN("blob_red_down.png"),
	SLIME_RED_UP("blob_red_up.png"),
	SLIME_BLUE_DOWN("blob_blue_down.png"),
	SLIME_BLUE_UP("blob_blue_up.png"),
	
	SKELETON_STAY_RIGHT("skeleton_stay_right.png"),
	SKELETON_STAY_LEFT("skeleton_stay_left.png"),
	SKELETON_DAMAGE_RIGHT("skeleton_damage_right.png"),
	SKELETON_DAMAGE_LEFT("skeleton_damage_left.png"),
	SKELETON_WALK_RIGHT("skeleton_walk_right.png"),
	SKELETON_WALK_LEFT("skeleton_walk_left.png"),
	
	ZOMBIE_STAY_RIGHT("zombie_stay_right.png"), 
	ZOMBIE_STAY_LEFT("zombie_stay_left.png"),
	ZOMBIE_DAMAGE_RIGHT("zombie_damage_right.png"),
	ZOMBIE_DAMAGE_LEFT("zombie_damage_left.png"),
	ZOMBIE_WALK_RIGHT("zombie_walk_right.png"),
	ZOMBIE_WALK_LEFT("zombie_walk_left.png"),
	
	COIN("coin.png"), 
	MANA("mana.png"),
	POTION("potion.png"),
	BUTTON("button.jpg"),
	
	TREE1("tree_1.png"),
	TREE2("tree_2.png"),
	TREE3("tree_3.png"),
	TREE4("tree_4.png"),
	
	ROOM1("room_0001.jpg"), //binary order, will be useful.
	ROOM2("room_0010.jpg"),
	ROOM3("room_0011.jpg"),
	ROOM4("room_0100.jpg"),
	ROOM5("room_0101.jpg"),
	ROOM6("room_0110.jpg"),
	ROOM7("room_0111.jpg"),
	ROOM8("room_1000.jpg"),
	ROOM9("room_1001.jpg"),
	ROOM10("room_1010.jpg"),
	ROOM11("room_1011.jpg"),
	ROOM12("room_1100.jpg"),
	ROOM13("room_1101.jpg"),
	ROOM14("room_1110.jpg"),
	ROOM15("room_1111.png"),

	Inventory("Inventory.png"),
	
	HealthPotion("healthpotion.png");
	
	private final BufferedImage image;
	
	Images(String path) {
		BufferedImage tempImage = null;
		try {
			tempImage = ImageIO.read(new File("img/"+path));
		} catch (Exception e) {
			tempImage = null;
		}
		this.image = tempImage;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
}