package fr.iutvalence.m2107.p24.ressources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/** The library of images to use everywhere in the game. */
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
	
	BOSS1_NORMAL("boss_1_normal.png"),
	BOSS2_NORMAL("boss_2_normal.png"),
	BOSS3_NORMAL("boss_3_normal.png"),
	BOSS1_DAMAGE("boss_1_damage.png"),
	BOSS2_DAMAGE("boss_2_damage.png"),
	BOSS3_DAMAGE("boss_3_damage.png"),
	
	KEY("key.png"),
	POTION_HEALTH("potion_health.png"),
	POTION_POISON("potion_poison.png"),
	POTION_SPEED("potion_speed.png"),
	
	TREE1("tree_1.png"),
	TREE2("tree_2.png"),
	TREE3("tree_3.png"),
	TREE4("tree_4.png"),
	
	KEY_WALL("key_wall.png"),
	
	ROOM1("room_0001.png"), //binary order, will be useful.
	ROOM2("room_0010.png"),
	ROOM3("room_0011.png"),
	ROOM4("room_0100.png"),
	ROOM5("room_0101.png"),
	ROOM6("room_0110.png"),
	ROOM7("room_0111.png"),
	ROOM8("room_1000.png"),
	ROOM9("room_1001.png"),
	ROOM10("room_1010.png"),
	ROOM11("room_1011.png"),
	ROOM12("room_1100.png"),
	ROOM13("room_1101.png"),
	ROOM14("room_1110.png"),
	ROOM15("room_1111.png"),

	KEY_ROOM_CLOSE_UP("key_room_close_up.png"),
	KEY_ROOM_CLOSE_RIGHT("key_room_close_right.png"),
	KEY_ROOM_CLOSE_DOWN("key_room_close_down.png"),
	KEY_ROOM_CLOSE_LEFT("key_room_close_left.png"),
	KEY_ROOM_OPEN_UP("key_room_open_up.png"),
	KEY_ROOM_OPEN_RIGHT("key_room_open_right.png"),
	KEY_ROOM_OPEN_DOWN("key_room_open_down.png"),
	KEY_ROOM_OPEN_LEFT("key_room_open_left.png"),
	
	BOSS_ROOM_RIGHT("boss_room_right.png"),
	BOSS_ROOM_LEFT("boss_room_left.png"),
	BOSS_ROOM_UP("boss_room_up.png"),
	BOSS_ROOM_DOWN("boss_room_down.png"),

	INVENTORY("inventory.png"),
	INVENTORY_TRANSPARENT("inventory_hover.png"),
	
	PAUSE_BACKGROUND("wallpaper_pause.jpg"),
	MAIN_MENU_BACKGROUND("wallpaper_main_menu.jpg"),
	DEATH_MENU_BACKGROUND("wallpaper_death.jpg"),
	HELP("help.png"),
	WIN("WinTitle.png"),
	WIN_BACKGROUND("WINBG.jpg");
	
	/** The current image. */
	private final BufferedImage image;
	
	/**
	 * Constructor.
	 * @param path the image path on the disk.
	 */
	Images(String path) {
		BufferedImage tempImage = null;
		try {
			tempImage = ImageIO.read(getClass().getResource("/"+path));
		} catch (Exception e) {
			tempImage = null;
			System.err.println("Can't find : " + path);
		}
		this.image = tempImage;
	}
	
	/**
	 * Allow to know the current image.
	 * @return the current image.
	 */
	public BufferedImage getImage() {
		return this.image;
	}
	
}