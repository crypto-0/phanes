package bolt.sytems;
import java.util.Map;
import java.util.UUID;

import bolt.*;
import bolt.components.RigidBody;
import bolt.components.Transform;

public class PhysicSystem extends System{

  public PhysicSystem(World world){
    super(world);
  }
	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      RigidBody rigidBody = world.getComponent(entity.id,RigidBody.class);
      Transform transform = world.getComponent(entity.id,Transform.class);
      if(rigidBody !=null && transform != null){
        transform.position.x +=rigidBody.velocity.x;
        transform.position.y +=rigidBody.velocity.y;
      }
    }
		
	}
}
