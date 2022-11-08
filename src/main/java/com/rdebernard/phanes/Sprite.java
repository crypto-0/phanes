package com.rdebernard.phanes;
import java.awt.image.BufferedImage;

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
}
