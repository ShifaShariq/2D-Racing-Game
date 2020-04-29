//THIS CLASS SETS IDS TO DIFFERENT TILES OF THE GAME AND MANAGES THEM

package racinggame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static Tile[] tiles=new Tile[8];
    public static Tile skullTile= new skullTile(0);
    public static Tile redTile= new redTile(1);
    public static Tile roadTile= new roadTile(2);
    public static Tile roadTile1= new roadTile1(3);
    public static Tile roadTile2= new roadTile2(4);
    public static Tile roadTile3= new roadTile3(5);
    public static Tile blackTile= new blackTile(6);
    public static Tile finishTile= new finishTile(7);
    
    
    public BufferedImage texture;
    public static final int tileWidth=101, tileHeight=243;
    
    public Tile(BufferedImage texture, int id){
        this.texture=texture;
        tiles[id]=this;
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,null);
    }
}
