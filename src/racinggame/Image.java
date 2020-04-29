//THIS CLASS IS USED TO ALL THE IMAGES NEEDED AND ALSO DOES THE CROPPING

package racinggame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
    public static BufferedImage car1,car2,car3,image,black,skull,red,road1,
            road2,road3,road4,finish,life,blackhole,shield,chemical,button,meter;
    
    public static void init(){
        image = imageLoader("/z1.png");
        car1=imageLoader("/car1.png");
        car2=imageLoader("/car2.jpg");
        car3=imageLoader("/car3.png");
        finish=imageLoader("/finish.png");
        life=imageLoader("/life.png");
        blackhole=imageLoader("/blackhole.jpg");
        shield=imageLoader("/shield.png");
        chemical=imageLoader("/chemical.jpg");
        meter=imageLoader("/meter.png");
        crop();
    }
    
    public static BufferedImage imageLoader(String path){
        try{
            return ImageIO.read(Image.class.getResource(path));
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    //FOR CROPPING
    public static void crop(){
        black=image.getSubimage(0, 0, 112, 243);
        skull=image.getSubimage(52, 0, 111, 243);
        red=image.getSubimage(163, 0, 101, 243);
        road1=image.getSubimage(264, 0, 112, 243);
        road2=image.getSubimage(376, 0, 112, 243);
        road3=image.getSubimage(488, 0, 112, 243);
        road4=image.getSubimage(600, 0, 112, 243);
        
    }
    
}
