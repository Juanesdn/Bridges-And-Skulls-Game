/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.awt.image.BufferedImage;

/**
 *
 * @author Juanes
 */
public class SpriteSheet {
    /**
     * Permite agarrar la imagen de un spritesheet
     */
    
    BufferedImage spriteSheet;
    
    public SpriteSheet(BufferedImage sheet){
        this.spriteSheet = sheet;
        
    }
    
    public BufferedImage grabSprite(int x, int y, int width, int height){
        
        return spriteSheet.getSubimage(x, y, width, height);
    }
    
}
