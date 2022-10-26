package bolt.components;
import bolt.SpriteSheet;

public class Animation implements Component{
  public long timeElapse;
  public long interval ;
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
  }
}
