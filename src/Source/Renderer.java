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
    
    Terrain terrain;
    Terrain2 terrain2;
    Character character;
    Bridge bridge;
    
    Timer timer = new Timer(20, this);
    Random rnd = new Random();
    
    int height, frame_width, width, width2, bridgeSize, scalingSpeed, scalingHeight;
    int characterPosition, distance, angle, scalingDrop, fase;
    boolean drop, moveCharacter, moving;
    
    
    public Renderer(int _width, int _height, int _fase){
        
        fase = _fase;
        height = _height-200; // altura
        frame_width = _width; // Anchura del frame
        width = 50 + rnd.nextInt(100); // Tamaño del terreno 1
        width2 = 50 + rnd.nextInt(100); // Tamaño del terreno2
        bridgeSize = 0; // Tamaño del puente
        scalingSpeed = 0; // Velocidad de escalado del puente
        scalingHeight = 0; // Velocidad de escalado de la altura
        characterPosition = width/2 - 30; // Posición del personaje
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
        
        terrain = new Terrain(0, height, width, height);
        terrain2 = new Terrain2((frame_width/2+rnd.nextInt(frame_width-200)), height, width2, height);
        character = new Character(characterPosition, (height - 30) + scalingDrop, 30, 30, moving, false);
        bridge = new Bridge(0, scalingHeight, 10, bridgeSize);
        
        timer.start();
        
        
    }
    
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        
        if (fase == 0){
            
            // Terreno
            g.setColor(Color.black);
            g.fillRect(terrain.x, terrain.y, terrain.width, terrain.height);

            // Terreno 2
            g.setColor(Color.black);
            g.fillRect(terrain2.x, terrain2.y, terrain2.width, terrain2.height);


            // Personaje
            if(moveCharacter && (angle == 90)){ 
                character.move(character.x, bridge.height, terrain.width-characterPosition); 
                character.drop(character.x, character.y, terrain2.x, terrain2.width);
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
            
            // Si el personaje llega se mueve el terreno
            if(character.arrived){
                
                terrain.deleteTerrain();
                terrain2.moveTerrain(frame_width);
                bridge.deleteBridge();
                character.moveCharacter(frame_width);
                
            }
            
        }
        
        
          
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        
        if (keyPressed == KeyEvent.VK_SPACE && drop == false){
            bridge.increaseSize(bridge.y, height-20);
            
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
