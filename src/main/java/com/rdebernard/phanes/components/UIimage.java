package com.rdebernard.phanes.components;
import java.awt.image.BufferedImage;

public class UIimage implements Component{
  public int layerOrder; 
  public boolean isVisible;
  public BufferedImage img;
  public int width;
  public int height;
  public UIimage(){
    this.img = null;
    this.layerOrder = 0;
    this.isVisible = false;
    this.height = 0;
    this.width = 0;
  }
  public UIimage(BufferedImage img,int layerOrder){
    this.img = img;
    this.height = img.getHeight();
    this.width = img.getWidth();
    this.layerOrder = layerOrder;
    isVisible = true;

  }
}
