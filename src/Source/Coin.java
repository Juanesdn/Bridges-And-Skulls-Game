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
public class Coin {
    
    int x, y, width, height;
    
    public Coin(int _x, int _y, int _width, int _height){
        
        x = _x;
        y = _y;
        width = _width;
        height = _height;
    }
    
    void generateCoin(boolean aviable){
        /**
         * Genera el coin aumentando su tamaño.
         */
        if (aviable == true){
            System.out.println(aviable);
            width = 50;
            height = 50;
        }
    }
    
    void deleteCoin(int CharacterPosition, boolean aviable){
        
       if (CharacterPosition > y - 10 && CharacterPosition < y + 10){
           System.out.println("entro");
            aviable = false;
       }
    }    
    
}
