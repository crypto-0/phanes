package bolt.components;
import java.awt.image.BufferedImage;
import bolt.Vec2d;

public class Sprite implements Component{
  public BufferedImage sprite;
  public boolean isVisible;
  public Sprite(BufferedImage sprite){
    this.sprite = sprite;
    isVisible = true;
  }
}
