
package bolt.sytems;
import java.util.Map;
import java.util.UUID;

import bolt.*;
import bolt.components.*;

public class RunStateSystem extends System{
  public RunStateSystem(World world){
    super(world);
  }

	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      RunState runState = world.getComponent(entity.id,RunState.class);
      if(runState != null){
        Input input = world.getComponent(entity.id,Input.class);
        Transform transform = world.getComponent(entity.id,Transform.class);
        if(input != null){
          if(input.horizontal !=0){
            RigidBody rigidBody = world.getComponent(entity.id,RigidBody.class);
            if(input.horizontal > 0 && transform.scale.x < 0){
              rigidBody.velocity.x = 0;
              transform.scale.x *=-1;
            }
            else if(input.horizontal < 0 && transform.scale.x> 0){
              rigidBody.velocity.x = 0;
              transform.scale.x *=-1;
            }
            rigidBody.velocity.x = 40  * input.horizontal;
          }
          if(input.vertical > 0){
            world.changeEntityState(entity.id,"attack");
          }
          if(input.vertical < 0){
            world.changeEntityState(entity.id, "roll");
          }
          if(input.horizontal ==0 && input.vertical == 0){
            world.changeEntityState(entity.id,"idle");
          }
        }
      }
    }
		
	}
}
