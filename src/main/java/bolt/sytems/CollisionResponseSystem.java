package bolt.sytems;
import bolt.*;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import bolt.components.*;

public class CollisionResponseSystem extends System{
  public CollisionResponseSystem(World world){
    super(world);
  }

	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    Comparator<Collision> comparator = (Collision collision,Collision collision2) -> {
      return collision.collisionTime < collision2.collisionTime ? 1:-1;
    };
    PriorityQueue<Collision> collisions = new PriorityQueue<>(entities.size(),comparator);
    for(UUID entityId: entities.keySet()){
      Collision collision = world.getComponent(entityId,Collision.class);
      if(collision !=null) collisions.add(collision);
    }
    while(!collisions.isEmpty()){
      Collision collision = collisions.poll();
      RigidBody rigidBody = world.getComponent(collision.entityId,RigidBody.class);
      Transform transform = world.getComponent(collision.entityId,Transform.class);
      transform.position.x += rigidBody.velocity.x * collision.collisionTime;
      transform.position.y += rigidBody.velocity.y * collision.collisionTime;
      //rigidBody.velocity.x = (float)(rigidBody.velocity.x * collision.collisionTime);
      //rigidBody.velocity.y = (float)(rigidBody.velocity.y * collision.collisionTime);
      double remainingTime = 1 - collision.collisionTime;
      double speed = (rigidBody.velocity.x * collision.ynormal + rigidBody.velocity.y * collision.xnormal) * remainingTime;
      //java.lang.System.out.println("Speed: " + speed);
      rigidBody.velocity.x = (float)speed * collision.ynormal;
      rigidBody.velocity.y = (float)speed * collision.xnormal;
      //java.lang.System.out.println("got collisions: " + rigidBody.velocity.y);
      //world.removeComponent(collision.entityId,Collision.class);
    }
	}
}


