package bolt.components;
import java.awt.image.BufferedImage;

public class Sprite implements Component{
  public BufferedImage sprite;
  public Vec2d tile_offset = new Vec2d();
  public int frame;
  public Sprite(BufferedImage sprite){
    this.sprite = sprite;
  }
  class Vec2d{
    public float x=0;
    public float y=0;
  }
}
