package bolt.components;

class Transform implements Component{
  public Vec2d position =new Vec2d();
  public Vec2d rotation = new Vec2d();
  public Vec2d scale = new Vec2d();

  class Vec2d{
    public float x;
    public float y;
  }
}
