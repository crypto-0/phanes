package bolt;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
  public ArrayList<Sprite> sprites;
  public SpriteSheet(BufferedImage spriteSheet,int numSprites,int xOffset){
    sprites = new ArrayList<>();
    for(int a=0; a< numSprites; a++){
      Sprite sprite = new Sprite(spriteSheet.getSubimage(a * xOffset, 0, xOffset, spriteSheet.getHeight()));
      sprites.add(a,sprite);
    }
  }
  public SpriteSheet(String resourceName,int numSprites,int xOffset){
    try{
      String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
      BufferedImage spriteSheet = ImageIO.read(new File(path)) ;
      for(int a=0; a< numSprites; a++){
        Sprite sprite = new Sprite(spriteSheet.getSubimage(a * xOffset, 0, xOffset, spriteSheet.getHeight()));
        sprites.add(sprite);
      }
    }
    catch(IOException e){
      System.out.println("Coult not load spriteSheet: " + resourceName);
    }
  }
}
