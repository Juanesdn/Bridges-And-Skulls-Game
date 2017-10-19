package Source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.Timer;


public class Renderer extends Canvas implements ActionListener, KeyListener {
    
    Timer timer = new Timer(20, this);
    Random rnd = new Random();
    
    
    int height = Frame.HEIGHT-200, width = 100 + rnd.nextInt(300); // Tamaño del terreno 200 
    int bridgeSize = 0, scalingSpeed = 0, scalingHeight = 0;
    
    public Renderer(){
        setBackground(Color.white);
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); // Shift y tab no seran usadas
        
        timer.start();
        
        
    }
    
    

    @Override
    public void paint(Graphics g) {
        
        // Terreno
        g.setColor(Color.black);
        g.fillRect(0, height, width, height);
        
        // Puente
        g.setColor(Color.green.darker());
        g.fillRect(width - 10 , height + scalingHeight, 10, bridgeSize);
          
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       bridgeSize += scalingSpeed;
       scalingHeight -= scalingSpeed;
       repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        
        if (keyPressed == KeyEvent.VK_SPACE){
            if (bridgeSize <= height-20){
                scalingSpeed = 2;
            }else{
                scalingSpeed = 0;
            }
            
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        
        if (keyPressed == KeyEvent.VK_SPACE){
            scalingSpeed = 0;
        }
    }
    
    
    
    
}
