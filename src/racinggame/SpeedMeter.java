//THIS CLASS CREATES THE ANALOGUE SPEEDOMETER USING SINE AND COSINE FUNCTIONS

package racinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SpeedMeter {
    private Car car;
    
    public SpeedMeter(Car car){
        this.car=car;
    }
    
    public void render(Graphics g){
       
        g.drawImage(Image.meter, 62, 120, 136, 136,null);
        
        double speed=car.getSpeed()%60.0/6*Math.PI*2;
        
        int cx=50+78;
        int cy=115+78;
        
        int animx=(int) (cx + Math.sin(speed)*50);
        int animy=(int) (cy - Math.cos(speed)*50);
        
        g.setColor(Color.RED);
        g.drawLine(cx, cy, animx, animy);
        
        //FOR MOVING THE LINE IN A CIRCLE
        for (int i=0;i<=5;i++){
            double radian= i%6.0/6 *Math.PI*2;
            int anim1=(int) (cx + Math.sin(radian)*50);
            int anim2=(int) (cy - Math.cos(radian)*50);
            
            
            String j=Integer.toString(i);
            g.setFont(new Font("arial",Font.PLAIN,12));
            g.setColor(Color.WHITE);
            g.drawString(j, anim1, anim2);
        }
    }
}
