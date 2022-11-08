package com.rdebernard.phanes;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
  public ArrayList<Sprite> sprites;
  public SpriteSheet(BufferedImage spriteSheet,int numSprites,int xOffset){
    sprites = new ArrayList<>();
    for(int a=0; a< numSprites; a++){
      Sprite sprite = new Sprite(spriteSheet.getSubimage(a * xOffset, 0, xOffset, spriteSheet.getHeight()));
      sprites.add(a,sprite);
    }
  }
}
