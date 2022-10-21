package bolt.components;
import bolt.Vec2d;

public class RigidBody implements Component{
  public Vec2d velocity = new Vec2d();
  public Vec2d force = new Vec2d();
  public float mass =0;

}
