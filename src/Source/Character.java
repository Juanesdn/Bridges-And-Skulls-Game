/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

/**
 *
 * @author Juanes
 */
public class Character {
    
    int x, y, width, height;
    boolean moving;
    
    public Character(int _x, int _y, int _width, int _height, boolean _moving){
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        moving = _moving;
    }
    
    void move(int characterPosition, int bridgeSize, int distance){
        /**
         * Mueve al personaje.
         */
        if (characterPosition <= distance+bridgeSize){
                x += 2;
                moving = true;

        }else{
                x += 0;
                moving = false;
        }
        
    }
    
    void drop(int characterPosition, int characterHeight, int frame_width){
        /**
         * Si el personaje no llega a un terreno se cae.
         */
        
        if(isOnTerrain(characterPosition, frame_width) && moving == false){
                
            }else if (isOnTerrain(characterPosition, frame_width) == false && moving == false){
                y += 2;
            }
    }
    
    boolean isOnTerrain(int characterPosition, int frame_width){
        /**
         * Retorna si el personaje está en un terreno o no.
         */
        // TODO: calcular la posición del segundo terreno
        if(characterPosition >= (frame_width)){ 
            return true;
        }else{
            return false;
        }     
    }
    
    
}
