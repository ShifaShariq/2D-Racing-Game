//THIS CLASS CREATES THE CHEMICAL X POWERUP

package racinggame;

import java.awt.Graphics;

public class ChemicalX {
    
    private int x;
    private int y;
    private Car car;
    
    public ChemicalX(Car car,int x,int y){
        this.car=car;
        this.x=x;
        this.y=y;
    }    
    
    public void tick(){
        y+=1;
    }
    
    public void render(Graphics g){
        g.drawImage(Image.chemical, x, y-car.getOfset(), 40, 41,null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }


}
