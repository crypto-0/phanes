package bolt.components;

public class PlayerMovement implements Component{
  public int velocity;
  public int maxVelocity;
  public PlayerMovement(int velocity,int maxVelocity){
    this.velocity = velocity;
    this.maxVelocity = maxVelocity;
  }
}
