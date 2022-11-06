package com.rdebernard.phanes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
  public BufferedImage img;
  public int width;
  public int height;
  public Sprite(){
    img = null;
    width = 0;
    height = 0;
  }
  public Sprite(BufferedImage img){
    this.img = img;
    this.height = img.getHeight();
    this.width = img.getWidth();
  }

  public Sprite(String resourceName){
    try{
      String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
      img = ImageIO.read(new File(path)) ;
      width = img.getWidth();
      height = img.getHeight();
    }
    catch(IOException e){
      System.out.println("Coult not load sprite: " + resourceName);
    }

  }
}
