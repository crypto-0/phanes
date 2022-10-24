package bolt.components;
import java.util.UUID;

public class Collision implements Component{
  public  UUID entityId;
  public  UUID entityId2;
  public double collisionTime;
  public byte xnormal,ynormal;
  public Collision(UUID entityId,UUID entityId2,double collisionTime,byte xnormal,byte ynormal){
    this.entityId = entityId;
    this.entityId2 = entityId2;
    this.collisionTime = collisionTime;
    this.xnormal =xnormal;
    this.ynormal = ynormal;
  }
}
