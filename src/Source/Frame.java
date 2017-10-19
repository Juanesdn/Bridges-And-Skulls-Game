/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import javax.swing.JFrame;

/**
 *
 * @author Juanes
 */
public class Frame extends JFrame{
    
    public static int WIDTH = 800, HEIGHT = 600;
    
    public Frame() {
        setTitle("Cross And Trap");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(new Renderer());
        setVisible(true);
        
    }
}
