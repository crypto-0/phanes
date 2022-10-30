package bolt.sytems;
import java.util.Map;
import java.util.UUID;

import bolt.*;
import bolt.components.*;

public class GroundedSystem extends System{
  public GroundedSystem(World world){
    super(world);
  }

@Override
public void update(float dt) {
  Map<UUID,Entity> entities = world.getAllEntity();
  for(UUID entityId: entities.keySet()){
    Grounded grounded = world.getComponent(entityId, Grounded.class);
    if(grounded !=null){
      grounded.grounded = false;
      Collision collision = world.getComponent(entityId,Collision.class);
      if(collision !=null){
        Ground ground = world.getComponent(collision.entityId2,Ground.class);
        if(ground !=null){
          grounded.grounded = true;
        }
      }
    }
  }
	
}
}
