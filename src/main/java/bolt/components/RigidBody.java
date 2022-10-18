package bolt.components;

public class RigidBody implements Component{
  public Vec2d velocity = new Vec2d();
  public float mass =0;

  class Vec2d{
    public float x;
    public float y;
  }
}
