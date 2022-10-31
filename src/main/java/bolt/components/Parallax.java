package bolt.components;
import bolt.Vec2d;

public class Parallax implements Component{
  public Vec2d lastCameraPosition;
  public float effectMultiplier;
  public Parallax(){
    lastCameraPosition = new Vec2d();
    effectMultiplier =1f;
  }
  public Parallax(float effectMultiplier){
    lastCameraPosition = new Vec2d();
    this.effectMultiplier =effectMultiplier;
  }
}
