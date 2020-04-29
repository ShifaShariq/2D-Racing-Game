//MANAGES THE TILES BY LOADING THE PATTERN FROM A FILE AND CREATES THE WORLD

package racinggame;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class world {
    private int width,height;
    private int[][] tile;
    private Car car;
    
    public world(Car car){
        loadWorld("res/world.txt");
        this.car=car;
    }
    
    private int parseInt(String number){
        return Integer.parseInt(number);
    }
    
    private void loadWorld(String path){
        try {
            String file=loadFile(path);
            String[] space=file.split("\\s+");
            
            width=parseInt(space[0]);
            height=parseInt(space[1]);
            
            tile =new int[width][height];
            
            for(int x=0; x<width; x++){
                for (int y=0; y<height; y++){
                    tile[x][y]= parseInt(space[(x+y *width)+2]);
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //PATTERN READING
    public String loadFile(String path) throws IOException{
        StringBuilder sr=new StringBuilder();
        FileReader f;
        try {
            f = new FileReader(path);
            BufferedReader reader=new BufferedReader(f);
            
            String line;
            while ((line=reader.readLine())!=null){
                sr.append(line + "\n");
            }
            
        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return sr.toString();
        
    }
    
    //FOR CREATING THE WORLD
    public void render(Graphics g){
        int start=Math.max(0,car.getOfset()/Tile.tileHeight);
        int end=Math.min(height, (car.getOfset()+Display.height+250)/Tile.tileHeight);
        for (int i=0; i<=width-1; i++){
            for (int j=start; j<=end-1; j++){
                Tile t=Tile.tiles[tile[i][j]];
                t.render(g, (i*Tile.tileWidth), (j*Tile.tileHeight)-car.getOfset());
            }
        }
    }
}
