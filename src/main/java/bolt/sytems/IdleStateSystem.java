package bolt.sytems;
import java.util.Map;
import java.util.UUID;

import bolt.*;
import bolt.components.*;

public class IdleStateSystem extends System{
  public IdleStateSystem(World world){
    super(world);
  }

	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      IdleState idleState = world.getComponent(entity.id,IdleState.class);
      if(idleState != null){
        Input input = world.getComponent(entity.id,Input.class);
        if(input != null){
          if(input.horizontal != 0){
            world.changeEntityState(entity.id,"run");
          }
          else if(input.vertical > 0){
            world.changeEntityState(entity.id,"attack");
          }
          else if(input.vertical < 0){
            world.changeEntityState(entity.id, "roll");
          }
          else{
            RigidBody rigidBody = world.getComponent(entity.id,RigidBody.class);
            rigidBody.velocity.x = 0;
          }
        }
      }
    }
	}
}
