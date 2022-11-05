package com.rdebernard.theses.components;
import com.rdebernard.theses.SpriteSheet;

public class Animation implements Component{
  public float timeElapse;
  public float interval ;
  public int currentFrame;
  public int frames;
  public boolean finished ;
  public boolean loop;
  public SpriteSheet spriteSheet;
  public Animation(){
  }
  public Animation(SpriteSheet spriteSheet,long interval){
    this.spriteSheet = spriteSheet;
    this.currentFrame = 0;
    this.interval = interval;
    this.frames = spriteSheet.sprites.size();
    this.loop = true;
  }
}
