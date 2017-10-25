package Source;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
    /**
     * Anima el personaje rotando cada imagen del spritesheet
     */
    
    ArrayList<BufferedImage> frames;
    
    public BufferedImage sprite;
    
    boolean running = false;
    
    long beforeTime, previousTime, speed;
    int frameAtPause, currentFrame;
    
    public Animator(ArrayList<BufferedImage> frames){
        this.frames = frames;
    }
    
    void setSpeed(long speed){
        this.speed = speed;
    }
    
    void update(long time){
        if(running){
            if(time - previousTime >= speed){
                // Actualizar animacion
                currentFrame++;
                
                try {
                    sprite = frames.get(currentFrame);
                } catch (IndexOutOfBoundsException e) {
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                }
                
                previousTime = time;
            }
        }
    }
    
    void start(){
        running = true;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    
    void stop(){
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    
    void pause(){
        frameAtPause = currentFrame;
        running = false;
    }
    
    void resume(){
        currentFrame = frameAtPause;
        running = true;
    }
    
}
