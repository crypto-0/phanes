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
	public void update(float dt) {
    sweptAabb(dt);
	}
  private void sweptAabb(float dt){
    Map<UUID,Entity> entities = world.getAllEntity();
    for(UUID entityId: entities.keySet()){
      world.removeComponent(entityId,Collision.class);
      for(UUID entityId2: entities.keySet()){
        if(entityId == entityId2)continue;
        RigidBody rigidBody = world.getComponent(entityId,RigidBody.class);
        Transform transform = world.getComponent(entityId,Transform.class);
        Collider collider = world.getComponent(entityId, Collider.class);
        Transform transform2 = world.getComponent(entityId2,Transform.class);
        Collider collider2 = world.getComponent(entityId2, Collider.class);
        double dxEntry,dxExit,dyEntry,dyExit;
        double txEntry,txExit,tyEntry,tyExit;
        if(rigidBody != null && collider !=null && collider2 !=null){
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
          tyEntry = tyEntry > 1.0f ?Double.NEGATIVE_INFINITY:tyEntry;
          txEntry = txEntry > 1.0f ?Double.NEGATIVE_INFINITY:txEntry;
          double entryTime = txEntry > tyEntry ? txEntry : tyEntry;
          entryTime +=0f;
          double exitTime  = txExit > tyExit ? tyExit : txExit;
          if(entryTime > exitTime){
            continue;
          }
          if(txEntry < -.001 && tyEntry < -.001){
            continue;
          }
          //if(txEntry < 0f && tyEntry < 0f){
            //continue;
          //}
          if(txEntry >= dt || tyEntry >= dt){
            continue;
          }
          
          if(tyEntry < -.01f){
            if(transform.position.y + collider.height < transform2.position.y){
              continue;
            }
            if(transform.position.y > transform2.position.y + collider2.height){
              continue;
            }
          }
          if(txEntry < -.01f){
            if(transform.position.x + collider.width < transform2.position.x){
              continue;
            }
            if(transform.position.x > transform2.position.x + collider2.width){
              continue;
            }
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
          Collision collision = world.getComponent(entityId,Collision.class);
          if(collision !=null){
            if(collision.collisionTime < entryTime)continue;
          }
          collision = new Collision(entityId, entityId2, entryTime, xnormal, ynormal);
          world.addComponent(entityId, collision);
        }
      }
    }
  }
}
