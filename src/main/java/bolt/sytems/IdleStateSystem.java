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
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      IdleState idleState = world.getComponent(entity.id,IdleState.class);
      if(idleState != null){
        Input input = world.getComponent(entity.id,Input.class);
        if(input != null){
          if(input.D == 1){
            world.changeEntityState(entity.id,"run");
          }

        }
      }
    }
		
	}
}
