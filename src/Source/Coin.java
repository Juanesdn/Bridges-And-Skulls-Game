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
    boolean available = true;
    
    public Coin(int _x, int _y, int _width, int _height){
        
        x = _x;
        y = _y;
        width = _width;
        height = _height;
    }
    
    void generateCoin(){
        /**
         * Genera el coin aumentando su tamaño.
         */
        if (available == true){
            System.out.println(available);
            width = 50;
            height = 50;
        }else{
            width = 0;
            height = 0;
        }
    }
    
    void deleteCoin(int CharacterPosition){
        
       if (CharacterPosition > y - 10 && CharacterPosition < y + 10){
           System.out.println("entro");
            available = false;
       }
    }    
    
}
