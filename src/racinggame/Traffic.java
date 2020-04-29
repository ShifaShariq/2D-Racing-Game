//THIS CLASS CREATES THE TRAFFIC

package racinggame;

import java.awt.Graphics;

public class Traffic {
    
    private int x;
    private int y;
    private Car car;
    
    public Traffic(Car car,int x,int y){
        this.car=car;
        this.x=x;
        this.y=y;
    }    
    
    public void tick(){
        y+=1;
    }
    
    public void render(Graphics g){
        g.drawImage(Image.car3, x, y-car.getOfset(), 200, 99,null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
