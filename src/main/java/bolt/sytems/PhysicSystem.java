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
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      RigidBody rigidBody = world.getComponent(entity.id,RigidBody.class);
      Transform transform = world.getComponent(entity.id,Transform.class);
      if(rigidBody !=null && transform != null){
        transform.position.x +=rigidBody.velocity.x * dt;
        transform.position.y +=rigidBody.velocity.y * dt;
        rigidBody.force.y += rigidBody.gravity * rigidBody.mass;
        float accelerationx = (rigidBody.force.x / rigidBody.mass)* dt;
        float accelerationy = (rigidBody.force.y / rigidBody.mass)* dt;
        rigidBody.velocity.x +=accelerationx ;
        rigidBody.velocity.y +=accelerationy ;
        rigidBody.force.x =0;
        rigidBody.force.y =0;
      }
    }
		
	}
}
