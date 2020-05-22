package fr.iutvalence.m2107.p24.ressources;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public enum Images {
	playerRight("player_right.png"),
	playerDamageRight("player_damage_right.png"),
	playerAttackRight("player_attack_right.png"),
	playerLeft("player_left.png"),
	playerDamageLeft("player_damage_left.png"),
	playerAttackLeft("player_attack_left.png"),
	
	slimeGreenDown("blob_green_down.png"),
	slimeGreenUp("blob_green_up.png"),
	slimeRedDown("blob_red_down.png"),
	slimeRedUp("blob_red_up.png"),
	slimeBlueDown("blob_blue_down.png"),
	slimeBlueUp("blob_blue_up.png"),
	
	skeletonStayRight("skeleton_stay_right.png"),
	skeletonStayLeft("skeleton_stay_left.png"),
	skeletonDamageRight("skeleton_damage_right.png"),
	skeletonDamageLeft("skeleton_damage_left.png"),
	skeletonWalkRight("skeleton_walk_right.png"),
	skeletonWalkLeft("skeleton_walk_left.png"),
	
	zombieStayRight("zombie_stay_right.png"), 
	zombieStayLeft("zombie_stay_left.png"),
	zombieDamageRight("zombie_damage_right.png"),
	zombieDamageLeft("zombie_damage_left.png"),
	zombieWalkRight("zombie_walk_right.png"),
	zombieWalkLeft("zombie_walk_left.png"),
	
	coin("coin.png"), 
	mana("mana.png"),
	potion("potion.png"),
	button("button.jpg"),
	
	room0("room_0001.jpg"), //binary order, will be useful.
	room1("room_0010.jpg"),
	room2("room_0011.jpg"),
	room3("room_0100.jpg"),
	room4("room_0101.jpg"),
	room5("room_0110.jpg"),
	room6("room_0111.jpg"),
	room7("room_1000.jpg"),
	room8("room_1001.jpg"),
	room9("room_1010.jpg"),
	room10("room_1011.jpg"),
	room11("room_1100.jpg"),
	room12("room_1101.jpg"),
	room13("room_1110.jpg"),
	room14("room_1111.jpg");

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