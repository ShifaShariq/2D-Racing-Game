//THIS CLASS MANAGES ALL THE ACTIVITIES OF THE GAME; THE COLLISION DETECTION
//AMONG DIFFERENT OBJECTS AND CARS

package racinggame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Manager { 
    private world world;
    private Car car;
    private ArrayList<Traffic> traffic;
    private ArrayList<Life> life;
    private ArrayList<BlackHole> blackhole;
    private ArrayList<Shield> shield;    
    private ArrayList<ChemicalX> chemical;  
    private long time1=System.nanoTime();
    private long time2=System.nanoTime();
    private long time3=System.nanoTime();
    private long time4=System.nanoTime();
    private long time5=System.nanoTime();    
    private long delay1,delay2,delay3,delay4,delay5;
    private int health;
    private SpeedMeter meter;
    private boolean shielded;
    private int carsDestruction;
    
        
    public Manager(){        
        car=new Car();
        world=new world(car);
        traffic=new ArrayList<Traffic>();
        life=new ArrayList<Life>();
        blackhole=new ArrayList<BlackHole>();
        shield=new ArrayList<Shield>();
        chemical=new ArrayList<ChemicalX>();
        meter=new SpeedMeter(car);
        delay1=1200;
        delay2=6000;
        delay3=8000;
        delay4=16000;
        delay5=45000;
        health=3;
        carsDestruction=0;
    }
    public void init(){        
        Image.init();
        car.init();
    }
    public void tick(){
//////////COLLISON DETECTION FOR TRAFFIC////////////////////////////////////////
        Random rand=new Random();
        int randx1=rand.nextInt(859);
        int randy1=rand.nextInt(Display.height);
        
       while (randx1<260){
            randx1=rand.nextInt(859);
        }
       for (int i=0;i<traffic.size();i++){
           int px=car.getX1();
           int py=car.getY1();
           
           int tx=traffic.get(i).getX();
           int ty=traffic.get(i).getY();
           
           //collision detection formula
           if (px<tx+100 && px+40>tx && py<ty+80 && py+80>ty ){
               traffic.remove(i);
               i--;
               if (health>0 && carsDestruction!=99999){
                   if (shielded==false){
                        car.setSpeed(0);
                        health--;
                        car.setHealth(health);     
                   }
                   else{
                        carsDestruction-=1;
                        if (carsDestruction==0){
                               shielded=false;
                       }
                   }
               }
           }           
       }       
        //TIME FOR NEW CAR
        long elapsed1=(System.nanoTime()-time1)/1000000;
        if (elapsed1 > delay1){
            traffic.add(new Traffic(car,randx1,(-randy1)+ car.getOfset()));
            time1=System.nanoTime();
            delay1-=15;
            //System.out.println(carsDestruction);
        }        
        car.tick();
        for (int i=0;i<traffic.size();i++){
            traffic.get(i).tick();
        }      
        
        
//////////COLLISON DETECTION FOR LIFE POWERUP////////////////////////////////////////
        int randx2=rand.nextInt(859);
        int randy2=rand.nextInt(Display.height);
        
       while (randx2<300){
            randx2=rand.nextInt(859);
        }
       for (int i=0;i<life.size();i++){
           int px=car.getX1();
           int py=car.getY1();
           
           int lx=life.get(i).getX();
           int ly=life.get(i).getY();
           
           //collision detection formula
           if (px<lx+20 && px+150>lx && py<ly+20 && py+80>ly ){
               life.remove(i);
               i--;
               health++;
               car.setHealth(health);                   
               }
           }
                  
        //TIME FOR NEW LIFE
        long elapsed2=(System.nanoTime()-time2)/1000000;
        if (elapsed2 > delay2){
            life.add(new Life(car,randx2,(-randy2)+ car.getOfset()));
            time2=System.nanoTime();
        }
        for (int i=0;i<life.size();i++){
            life.get(i).tick();
        }
        
        
////////////COLLISON DETECTION FOR BLACK HOLE////////////////////////////////////////
        int randx3=rand.nextInt(859);
        int randy3=rand.nextInt(Display.height);
        
       while (randx3<300){
            randx3=rand.nextInt(859);
        }
       for (int i=0;i<blackhole.size();i++){
           int px=car.getX1();
           int py=car.getY1();
           
           int bx=blackhole.get(i).getX();
           int by=blackhole.get(i).getY();
           
           //collision detection formula
           if (px<bx+40 && px+80>bx && py<by+40 && py+80>by ){
               blackhole.remove(i);
               i--;
               health=-100000;
               car.setHealth(health);                   
               }
           }                  
        //TIME FOR NEW BLACKHOLE
        long elapsed3=(System.nanoTime()-time3)/1000000;
        if (elapsed3 > delay3){
            blackhole.add(new BlackHole(car,randx3,(-randy3)+ car.getOfset()));
            time3=System.nanoTime();
        }
        for (int i=0;i<blackhole.size();i++){
            blackhole.get(i).tick();
        }
        
 ////////////COLLISON DETECTION FOR SHIELD POWERUP//////////////////////////////
        int randx4=rand.nextInt(859);
        int randy4=rand.nextInt(Display.height);
        
       while (randx4<300){
            randx4=rand.nextInt(859);
        }
       for (int i=0;i<shield.size();i++){
           int px=car.getX1();
           int py=car.getY1();
           
           int sx=shield.get(i).getX();
           int sy=shield.get(i).getY();
           
           //collision detection formula
            if (px<sx+40 && px+80>sx && py<sy+40 && py+80>sy ){
               shield.remove(i);
               shielded=true;
               if (carsDestruction<4){
                carsDestruction=3;
               }
            }
           }                  
        //TIME FOR NEW SHIELD POWERUP
        long elapsed4=(System.nanoTime()-time4)/1000000;
        if (elapsed4 > delay4){
            shield.add(new Shield(car,randx4,(-randy4)+ car.getOfset()));
            time4=System.nanoTime();
        }
        for (int i=0;i<shield.size();i++){
            shield.get(i).tick();
        }
        if (carsDestruction!=99999){
            car.setShieldLeft(Integer.toString(carsDestruction));       
        }
        
////////////COLLISON DETECTION FOR CHEMICAL X////////////////////////////////////////
        int randx5=rand.nextInt(859);
        int randy5=rand.nextInt(Display.height);
        
       while (randx5<260){
            randx5=rand.nextInt(859);
        }
       for (int i=0;i<chemical.size();i++){
           int px=car.getX1();
           int py=car.getY1();
           
           int cx=chemical.get(i).getX();
           int cy=chemical.get(i).getY();
           
           //collision detection formula
           if (health>0){
               if (px<cx+40 && px+80>cx && py<cy+40 && py+80>cy ){
                   chemical.remove(i);
                   carsDestruction=99999;
                   car.setShieldLeft("Infinty");                   
                   }
               }
            }
        //TIME FOR NEW BLACKHOLE
        long elapsed5=(System.nanoTime()-time5)/1000000;
        if (elapsed5 > delay5){
            chemical.add(new ChemicalX(car,randx5,(-randy5)+ car.getOfset()));
            time5=System.nanoTime();
        }
        for (int i=0;i<chemical.size();i++){
            chemical.get(i).tick();
        }
    }
    
    
    
    
    //FOR DISPLAYING THE TRAFFIC, LIFE, BLACKHOLE, SHIELD, CHEMICAL X
    public void render(Graphics g){
        world.render(g);
        car.render(g);
        meter.render(g);
        for (int i=0;i<traffic.size();i++){
            traffic.get(i).render(g);
        }
        for (int i=0;i<life.size();i++){
            life.get(i).render(g);
        }
        for (int i=0;i<blackhole.size();i++){
            blackhole.get(i).render(g);
        }
        for (int i=0;i<shield.size();i++){
            shield.get(i).render(g);
        }
        for (int i=0;i<chemical.size();i++){
            chemical.get(i).render(g);
        }
    }
}
 