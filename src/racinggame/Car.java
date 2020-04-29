//THIS CLASS TAKES CARE OF THE PLAYER CAR, THE SCORE, THE PERCETAGE OF TRACK COMPLETED AND THE SPEED

package racinggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Car implements KeyListener{   
    private int x1,y1;
    private int ofset;
    private double speed;
    private boolean up1,down1,left1,right1;
    private int health;
    private int gear;
    double max;
    double track;
    private Sound sound;
    private int stop;
    private String carsDestruction;
    
    //CAR CONSTRUCTOR
    public Car(){
        x1=Display.width/2-78;
        y1=Tile.tileHeight*119;
        ofset=0;
        speed =0.3f;
        health=3;
        gear=0;
        stop=0;
    }
    //CAR'S INITIALIZE METHOD. ADDS THE OPENING SOUND TOO
    public void init(){
        Display.frame.addKeyListener(this);
        sound=new Sound("res\\opening.wav");
        
    }
    //CAR MOVEMENT
    public void tick(){
        ofset=y1 - (Display.height-150);
        if (health>0){
            if (up1){
                if (y1>3700){
                    speed+=0.03f;
                    if (speed >=6){
                        speed=6;
                    }
                }
                if (y1>3700){
                    y1-=speed;
                }
            }
            if (down1){
                speed -=0.06f;
                if (speed<=0){
                    speed=0;
                }
            }
            if (left1){
                if (speed!=0 && x1>=278){
                    x1-=3;
                }
            }
            if (right1){
                if (speed!=0 & x1<=879){
                    x1+=3;
                }    
            }
            y1-=speed;
        }
    }
    
    //GET AND SET METHODS
    public int getOfset(){
        return ofset;
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public void setSpeed(double speed){
        this.speed=speed;
    }
    
    public void setHealth(int health){
        this.health=health;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    //DRAWING BOARD
    public void board(Graphics g){
        //FOR GEAR CHANGING (VISUAL)
        int speed1 =(int)speed;
        switch(speed1){
            case 0:
                gear=0;
                break;                
            case 1 : 
                gear=1;
                break;                
            case 2 :
                gear=2;
                break;                
            case 3 :
                gear=3;
                break;
            case 4:
                gear=4;
                break;
            case 5:
                gear=5;
                break;
             case 6:
                gear=6;
                break;   
        }        
        
        
       // g.drawImage(Image.button,52,10,148,90,null);
       // g.drawImage(Image.button,1115, 10, 200, 200,null);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.BOLD,24));
        
        //FOR SHIELD POWER
        g.drawString("Shield : "+carsDestruction,1115,160);            
        g.setFont(new Font("arial",Font.BOLD,30));      
        
        String gear1=Integer.toString(gear);   
        
        //FOR GEAR AND HEALTH
        if (health>0){
            g.drawString("Gear: "+gear1, 60, 40);
            g.drawString("Health: "+health, 60, 80);
        }
        else{ 
            g.drawString("Gear: 0", 60, 40);
            g.drawString("Health: 0", 60, 80);
        }    
        //FOR TRACK PERCENTAGE COMPLETED AND POINTS
        g.setFont(new Font("arial",Font.BOLD,24));
        g.drawString("Track Completed",1115,40);
        
        track=((28915-(double)y1)/25523)*100;
        if (track<=100){
            g.drawString(""+track,1115,80);
            g.drawString("Points: "+((28915-y1)/10),1115,120);
            max=(28915-y1)/10;
        }
        else{
            speed=0;
            g.setColor(Color.WHITE);
            g.drawString("100",1115,80);
            g.drawString("Points: "+max,1115,120);
            TrackCompleted(g);
        }
    }
    
    //FOR TRACK COMPLETION SOUND AND DISPLAY
    public void TrackCompleted(Graphics g){
        if (stop==0){
            sound=new Sound("res\\blackhole.wav");
        }   
        g.setColor(Color.BLUE);
        g.setFont(new Font("arial",Font.BOLD,50));
        g.drawString("Well Done",Display.width/3+100,Display.height/2);   
        stop=1;
    }
    
    //FOR HEALTH=0 DISPLAY
    public void GameOver(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("arial",Font.BOLD,50));
        g.drawString("Game Over",Display.width/3+100,Display.height/2);
    }
    
    //FOR COLLISION WITH BLACKHOLE SOUND AND DISPLAY
    public void BlackHole(Graphics g){
        if (stop==0){
            sound=new Sound("res\\blackhole.wav");
        }   
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial",Font.BOLD,80));
        g.drawString("SUCKED INTO THE VOID",Display.width/3-250,Display.height/2);
        g.drawString("OF NOTHINGNESS",Display.width/3-150,Display.height/2+100);
        stop=1;
    }
    
    //FOR COLLISION WITH CHEMICAL X SOUND AND AUDIO
    public void setShieldLeft(String carsDestruction){ 
       this.carsDestruction=carsDestruction;
       if( carsDestruction=="Infinty"){
          sound=new Sound("res\\chemicalx.wav");
       }
    }
    
    //DRAWING THE CAR AND RENDERING ALL THE GRAPHICS 
    public void render(Graphics g){        
        if (health>0){
            g.setColor(Color.red);
            g.drawImage(Image.car1, x1, y1-ofset, 150, 99, null);
        }
        else if (track<100 && health==0){            
            GameOver(g);
        }
        else if (track<100){
            BlackHole(g);
        }
        board(g);
    }

    
    public void keyTyped(KeyEvent e) {
    }

    //KEY PRESSED
    public void keyPressed(KeyEvent e) {
        int source=e.getKeyCode();
        
        if (source==KeyEvent.VK_RIGHT){
            right1=true;
        }
        if (source==KeyEvent.VK_LEFT){
            left1=true;
        }
        if (source==KeyEvent.VK_UP){
            up1=true;
        }
        if (source==KeyEvent.VK_DOWN){
            down1=true;
        }
    }

    //KEY RELEASED
    public void keyReleased(KeyEvent e) {
        int source=e.getKeyCode();
        if (source==KeyEvent.VK_RIGHT){
            right1=false;
        }
        if (source==KeyEvent.VK_LEFT){
            left1=false;
        }
        if (source==KeyEvent.VK_UP){
            up1=false;
        }
        if (source==KeyEvent.VK_DOWN){
            down1=false;
        }
        
    }
}
