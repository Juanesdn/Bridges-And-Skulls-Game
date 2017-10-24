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
public class Terrain2 {
    
    int x, y, width, height;
    
    public Terrain2 (int _x, int _y, int _width, int _height){
        
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        
    }
    
    void moveTerrain(int frameWidth){
        if (x >= frameWidth/2){
            x--;
        }
    }
    
}
