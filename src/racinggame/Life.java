//THIS CLASS CREATES THE LIFE POWERUP

package racinggame;

import java.awt.Graphics;

public class Life {
    
    private int x;
    private int y;
    private Car car;
    
    public Life(Car car,int x,int y){
        this.car=car;
        this.x=x;
        this.y=y;
    }    
    
    public void tick(){
        y+=1;
    }
    
    public void render(Graphics g){
        g.drawImage(Image.life, x, y-car.getOfset(), 20, 14,null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

}
