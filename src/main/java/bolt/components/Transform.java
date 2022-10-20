package bolt.components;
import bolt.Vec2d;

public class Transform implements Component{
  public Vec2d position ;
  public Vec2d rotation ;
  public Vec2d scale ;
  public Transform(){
    position =new Vec2d();
    rotation = new Vec2d();
    scale = new Vec2d();
    scale.x = 1;
    scale.y = 1;

  }
}
