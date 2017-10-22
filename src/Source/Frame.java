package Source;

import javax.swing.JFrame;

public class Frame extends JFrame{
    
    
    public Frame() {
        setTitle("Cross And Trap");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(new Renderer(getWidth(), getHeight()));
        setVisible(true);
        
    }
}
