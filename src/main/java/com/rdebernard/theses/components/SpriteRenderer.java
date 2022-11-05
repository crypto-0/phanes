package com.rdebernard.theses.components;
import com.rdebernard.theses.Sprite;

public class SpriteRenderer implements Component{
  public Sprite sprite;
  public int layerOrder; 
  public boolean isVisible;
  public SpriteRenderer(){
    this.sprite = null;
    this.layerOrder = 0;
    this.isVisible = false;
  }
  public SpriteRenderer(Sprite sprite,int layerOrder){
    this.sprite = sprite;
    this.layerOrder = layerOrder;
    isVisible = true;
  }
}
