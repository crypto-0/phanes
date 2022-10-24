
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
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      RunState runState = world.getComponent(entity.id,RunState.class);
      if(runState != null){
        Input input = world.getComponent(entity.id,Input.class);
        if(input != null){
          if(input.A == 1){
            world.changeEntityState(entity.id,"idle");
          }

        }
      }
    }
		
	}
}
