///THIS CLASS MANAGES THE AUDIO FILES

package racinggame;    

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private Mixer mixer;
    public Clip clip;
    
    public Sound(String path){
        Mixer.Info[]mixInfos=AudioSystem.getMixerInfo();
        mixer=AudioSystem.getMixer(mixInfos[0]);
        DataLine.Info dataInfo=new DataLine.Info(Clip.class,null);
        try{
            clip =(Clip)mixer.getLine(dataInfo);
        }catch(LineUnavailableException e){
            e.printStackTrace();
        }
        try{
            File file=new File(path);
            AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);
            clip.open(audioStream);
        }catch(LineUnavailableException e){
            e.printStackTrace();
        }catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        clip.start();
    }
}
