package fr.iutvalence.m2107.p24.ressources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
    public static BufferedImage[] player;
    
    public Images() {
        Images.player = new BufferedImage[1];
        
        try {
            Images.player[0] = ImageIO.read(this.getClass().getResourceAsStream("img/player_right.png"));
            // TODO: more images to come later..
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}