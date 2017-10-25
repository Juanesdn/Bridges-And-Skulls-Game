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
public class Bridge {
    
    int x, y, height, width;
    
    public Bridge(int _x, int _y, int _width, int _height){
        
        x = _x;
        y = _y;
        width = _width;
        height = _height;
    
    }
    
    void increaseSize(int bridgeSize, int maxHeight){
        /**
         * Aumenta el tamaño del puente.
         */
        if (height <= maxHeight){
            height += 5;
            y -= 5;
        }
    }
    
    void deleteBridge(){
        /**
         * Borra el puente.
         */
        if (height >= 0){
            height = 0;
        }
    }
    
}
