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
    boolean moving, arrived;
    
    public Character(int _x, int _y, int _width, int _height, boolean _moving, boolean _arrived){
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        moving = _moving;
        arrived = _arrived;
    }
    
    void move(int characterPosition, int bridgeSize, int distance){
        /** 
         * Mueve al personaje.
         */
        // PENDING: La distancia recorrida por el personaje solo funciona en algunos casos
        if (characterPosition <= (distance+bridgeSize+10)){
                x += 2;
                moving = true;

        }else{
                x += 0;
                moving = false;
        }
        
    }
    
    void drop(int characterPosition, int characterHeight, int terrain_width, int width_position){
        /**
         * Si el personaje no llega a un terreno se cae.
         */
        
        if(isOnTerrain(characterPosition, terrain_width, width_position) && moving == false){
                
            }else if (isOnTerrain(characterPosition, terrain_width, width_position) == false && moving == false){
                y += 2;
            }
    }
    
    void moveCharacter(int frame_width){
        
        if (x >= frame_width/2 ){
            x--;
            
        }
        
    }
    
    boolean isOnTerrain(int characterPosition, int terrain_width, int terrain_position){
        /**
         * Retorna si el personaje está en un terreno o no.
         */
        // TODO: calcular la posición del segundo terreno
        if (characterPosition >= terrain_width){
            arrived = true;
            return true;
        }else{
            return false;
        }
        
        
    }
    
    
}
