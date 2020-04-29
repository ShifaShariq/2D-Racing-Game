//THIS CLASS MANAGES THE SETUP OF THE GAME

package racinggame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Setup implements Runnable{
    
    private String title;
    private int width, height;
    
    private Manager manager;
    
    private Thread thread;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;
    
    
    public Setup(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
    }
    
    public void init(){
        display=new Display(title,width,height);
        manager=new Manager();
        manager.init();
    }
    
    public synchronized void start(){
        if (thread==null){
            thread=new Thread(this);
            thread.start();
        }
    }
    
    public synchronized void stop(){
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void tick(){
        manager.tick();
    }
    
    public void render(){
        buffer = display.canvas.getBufferStrategy();
        if (buffer==null){
            display.canvas.createBufferStrategy(3);
            return;
        }
        g=buffer.getDrawGraphics();
        g.clearRect(0,0,width,height);
        
        manager.render(g);
        
        buffer.show();
        g.dispose();
    }
    
    public void run(){
        init();
        
        int fps=1000000000; // FOR CHANGING THE SPEED OF RECTANGLE
        double timePerTick=1000000000/fps;
        double delta=0;
        long current = System.nanoTime();
        
        while (true){
            delta = delta +(System.nanoTime()-current) / timePerTick;
            current=System.nanoTime();
            if (delta >= 1){
                tick();
                render();  
                delta--;
            }
        }
    }
}
