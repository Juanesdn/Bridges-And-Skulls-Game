package Source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.util.Random;

import javax.swing.Timer;

 
public class Renderer extends Canvas implements ActionListener, KeyListener {
    
    // Objetos
    Terrain terrain;
    Terrain2 terrain2;
    Character character;
    Bridge bridge;
    Coin coin;
    SpriteSheet sheet;
    
    // Animadores de sprites
    Animator characterAnimator;
    Animator enemyAnimator;
    Animator coinAnimator;
    
    // Librerias
    Timer timer = new Timer(20, this);
    Random rnd = new Random();
    BufferedImage img;
    BufferedImage static_char;
    
    // Listas
    ArrayList<BufferedImage> characterSprites = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> enemySprites = new ArrayList<BufferedImage>();
    ArrayList<BufferedImage> coinSprites = new ArrayList<BufferedImage>();
    
    
    // Variables
    int height, frame_width, width, width2, bridgeSize, scalingSpeed, scalingHeight;
    int characterPosition, distance, angle, scalingDrop;
    public static boolean drop, moveCharacter, moving, cambiandoFase;
    public static int fase;
    
    
    public Renderer(int _width, int _height){
        
        fase = 0;
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
        cambiandoFase = false;
        
        setBackground(Color.white);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); // Shift y tab no seran usadas
        
        
        // Objetos del juego
        terrain = new Terrain(0, height, width, height);
        terrain2 = new Terrain2(400, height, width2, height);
        character = new Character(characterPosition, (height - 50) + scalingDrop, 30, 30, moving, false);
        bridge = new Bridge(0, scalingHeight, 10, bridgeSize);
        // PENDING: ajustar la posicion en x para que quede arriba del personaje
        // TODO: Crear un metodo que mueva a la moneda junto con el personaje
        coin = new Coin(character.x,100 + rnd.nextInt(_height/2) , 0, 0);
        
        
        // Sprite handler
        img = ImageLoader.loadImage("/Sprites/characterMoving.png");
        static_char = ImageLoader.loadImage("/Sprites/char_not_moving.png");
        sheet = new SpriteSheet(img);
        
        // Character sprites handler
        characterSprites.add(sheet.grabSprite(0, 0, 32, 32));
        characterSprites.add(sheet.grabSprite(32, 0, 32, 32));
        characterSprites.add(sheet.grabSprite(0, 32, 32, 32));
        characterAnimator = new Animator(characterSprites);
        characterAnimator.setSpeed(100);
        characterAnimator.start();
        
        // Enemy sprites handler
        img = ImageLoader.loadImage("/Sprites/enemy.png");
        sheet = new SpriteSheet(img);
        
        enemySprites.add(sheet.grabSprite(0,0, 32, 32));
        enemySprites.add(sheet.grabSprite(0, 32, 32, 32));
        enemyAnimator = new Animator(enemySprites);
        enemyAnimator.setSpeed(200);
        enemyAnimator.start();
        
        // Coin sprites handler
        img = ImageLoader.loadImage("/Sprites/coinSpin.png");
        sheet = new SpriteSheet(img);
        
        coinSprites.add(sheet.grabSprite(0, 0, 32, 32));
        coinSprites.add(sheet.grabSprite(32, 0, 32, 32));
        coinSprites.add(sheet.grabSprite(0, 32, 32, 32));
        coinSprites.add(sheet.grabSprite(32, 32, 32, 32));
        coinSprites.add(sheet.grabSprite(0, 64 , 32, 32));
        coinSprites.add(sheet.grabSprite(32, 64, 32, 32));
        coinAnimator = new Animator(coinSprites);
        coinAnimator.setSpeed(100);
        coinAnimator.start();
        
        
        
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

            if (character.moving){
                
                if (characterAnimator != null){
                    
                    characterAnimator.update(System.currentTimeMillis());
                    g.drawImage(characterAnimator.sprite, character.x, character.y, 50, 50, null);
                }
            }else{
                g.drawImage(static_char, character.x, character.y, 50, 50, null);
            }
            
            // Enemy
            
            if (enemyAnimator != null){
                
                enemyAnimator.update(System.currentTimeMillis());
                g.drawImage(enemyAnimator.sprite, 0, 0, 0, 0, null); // Cambiar el tamaño para la segunda fase
            }
                
            // Coin
            
            if (coinAnimator != null){
                
                coinAnimator.update(System.currentTimeMillis());
                g.drawImage(coinAnimator.sprite, character.x, coin.y, coin.width, coin.height, null); // Cambiar el tamaño para la segunda fase
            }


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
                
                cambiandoFase = true;
                terrain.deleteTerrain();
                terrain2.moveTerrain(frame_width);
                bridge.deleteBridge();
                character.moveCharacter(frame_width);
                
                if (cambiandoFase == false){
                    coin.generateCoin();
                    coin.deleteCoin(character.y);
                    
                }
                
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
        
        if (character.arrived && cambiandoFase == false){
            if (keyPressed == KeyEvent.VK_UP){
                character.moveUp();
            }else if (keyPressed == KeyEvent.VK_DOWN){
                // PENDING: Agregar un limite para que no baje mas del terreno.
                character.moveDown(terrain2.y);
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
            drop = true;
            moveCharacter = true;
            
        }
    }
    
    
    
    
    
}
