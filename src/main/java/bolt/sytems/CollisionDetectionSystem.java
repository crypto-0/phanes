package bolt.sytems;
import bolt.components.*;

import java.util.Map;
import java.util.UUID;

import bolt.*;

public class CollisionDetectionSystem extends System{
  public CollisionDetectionSystem(World world){
    super(world);
  }

	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(UUID entityId: entities.keySet()){
      for(UUID entityId2: entities.keySet()){
        if(entityId == entityId2)continue;
        RigidBody rigidBody = world.getComponent(entityId,RigidBody.class);
        RigidBody rigidBody2 = world.getComponent(entityId2,RigidBody.class);
        Transform transform = world.getComponent(entityId,Transform.class);
        Collider collider = world.getComponent(entityId, Collider.class);
        Transform transform2 = world.getComponent(entityId2,Transform.class);
        Collider collider2 = world.getComponent(entityId2, Collider.class);
        double dxEntry,dxExit,dyEntry,dyExit;
        double txEntry,txExit,tyEntry,tyExit;
        if(rigidBody != null && rigidBody2 != null){
          if(rigidBody.velocity.x > 0){
            dxEntry = transform2.position.x -(transform.position.x + collider.width);
            dxExit = (transform2.position.x + collider2.width) -(transform.position.x);
          }
          else{
            dxEntry = (transform2.position.x + collider2.width) - transform.position.x;
            dxExit =transform2.position.x -(transform.position.x + collider.width);
          }
          if(rigidBody.velocity.y > 0){
            dyEntry = transform2.position.y -(transform.position.y + collider.height);
            dyExit = (transform2.position.y + collider2.height) -(transform.position.y);
          }
          else{
            dyEntry = (transform2.position.y + collider2.height) - transform.position.y;
            dyExit =transform2.position.y -(transform.position.y + collider.height);
          }
          
          if(rigidBody.velocity.x == 0){
            txEntry = Double.NEGATIVE_INFINITY;
            txExit = Double.POSITIVE_INFINITY;

          }
          else{
            txEntry = dxEntry/rigidBody.velocity.x;
            txExit = dxExit/rigidBody.velocity.x;
          }
          if(rigidBody.velocity.y == 0){
            tyEntry = Double.NEGATIVE_INFINITY;
            tyExit = Double.POSITIVE_INFINITY;

          }
          else{
            tyEntry = dyEntry/rigidBody.velocity.y;
            tyExit = dyExit/rigidBody.velocity.y;
          }

          double entryTime = txEntry > tyEntry ? txEntry : tyEntry;
          double exitTime  = txExit > tyExit ? tyExit : txExit;
          if(entryTime > exitTime)continue;
          if(txEntry < 0 && tyEntry < 0)continue;
          if(txEntry > 1 || tyEntry > 1)continue;
          if(tyEntry < 0){
            if(transform.position.y + collider.height < transform2.position.y)continue;
            if(transform.position.y > transform2.position.y + collider2.height)continue;
          }
          if(txEntry < 0){
            if(transform.position.x + collider.width < transform2.position.x)continue;
            if(transform.position.x > transform2.position.x + collider2.width)continue;
          }
          byte xnormal,ynormal;
          if(txEntry > tyEntry){
            if(dxEntry < 0){
              xnormal = 1;
              ynormal = 0;
            }
            else{
              xnormal = -1;
              ynormal = 0;
            }
          }
          else{
            if(dyEntry < 0){
              xnormal = 0;
              ynormal = 1;
            }
            else{
              xnormal = 0;
              ynormal = -1;
            }
          }
          Collision collision = new Collision(entityId, entityId2, entryTime, xnormal, ynormal);
          world.addComponent(entityId, collision);
        }
      }
    }
	}
}
