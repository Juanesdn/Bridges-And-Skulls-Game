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
    
    Character character;
    Bridge bridge;
    
    Timer timer = new Timer(20, this);
    Random rnd = new Random();
    
    int height, frame_width, width, width2, bridgeSize, scalingSpeed, scalingHeight;
    int characterPosition, distance, angle, scalingDrop;
    boolean drop, moveCharacter, moving;
    
    
    public Renderer(int _width, int _height){
        
        height = _height-200; // altura
        frame_width = _width; // Anchura del frame
        width = 50 + rnd.nextInt(100); // Tamaño del terreno 1
        width2 = 50 + rnd.nextInt(100); // Tamaño del terreno2
        bridgeSize = 0; // Tamaño del puente
        scalingSpeed = 0; // Velocidad de escalado del puente
        scalingHeight = 0; // Velocidad de escalado de la altura
        characterPosition = 10 + rnd.nextInt(width-50); // Posición del personaje
        distance = (width-characterPosition); // distancia por recorrer del personaje
        angle = 0; // angulo de rotacion
        scalingDrop = 0; // Velocidad de la caida
        drop = false; // Si el objeto debe caer
        moveCharacter = false; // Si el personaje se debe mover
        moving = false; // Si el personaje se esta moviendo
        
        setBackground(Color.white);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); // Shift y tab no seran usadas
        
        character = new Character(characterPosition, (height - 30) + scalingDrop, 30, 30, moving);
        bridge = new Bridge(0, scalingHeight, 10, bridgeSize);
        
        timer.start();
        
        
    }
    
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        
        // Terreno
        g.setColor(Color.black);
        g.fillRect(0, height, width, height);
        
        // Terreno 2
        g.setColor(Color.black);
        g.fillRect(frame_width - 200, height, width2, height);
        

        // Personaje
        if(moveCharacter && (angle == 90)){ 
            
            character.move(character.x, bridge.height, distance); 
            character.drop(character.x, character.y, frame_width-200);
        }
          
        g.setColor(Color.RED);
        g.fillOval(character.x, character.y, character.width, character.height);
        
        
        // Puente
  
        g2d.setColor(Color.green.darker());   
        g2d.translate(width-10, height);
        if (drop && angle < 90){
            angle++;
        }
        g2d.rotate(Math.toRadians(angle));    
        g2d.fillRect(bridge.x , bridge.y, bridge.width, bridge.height);
        
          
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        
        if (keyPressed == KeyEvent.VK_SPACE){
            bridge.increaseSize(bridgeSize, height-20);
            
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        
        if (keyPressed == KeyEvent.VK_SPACE){
            drop = true;
            moveCharacter = true;
            
        }
    }
    
    
    
    
    
}
