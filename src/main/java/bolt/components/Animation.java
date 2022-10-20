package bolt.components;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation implements Component{
  public long timeElapse;
  public long interval ;
  public int currentFrame;
  public int frames;
  public int xOffset;
  public boolean finished ;
  public boolean loop;
  public BufferedImage sprites[];
  public Animation(String spriteSheetName,int interval,int frames,int xOffset){
    try{
      String path = this.getClass().getClassLoader().getResource(spriteSheetName).getPath();
      BufferedImage spriteSheet = ImageIO.read(new File(path)) ;
      this.frames = frames;
      this.interval = interval;
      this.xOffset = xOffset;
      this.sprites = new BufferedImage[frames];
      this.loop = true;
      this.finished = false;
      for(int a=0; a< frames; a++){
        sprites[a] = spriteSheet.getSubimage(a * xOffset, 0, xOffset, spriteSheet.getHeight());
      }
    }
    catch(IOException e){

    }

  }
}
