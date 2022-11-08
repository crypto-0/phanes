package com.rdebernard.phanes.systems;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import com.rdebernard.phanes.Entity;
import com.rdebernard.phanes.World;
import com.rdebernard.phanes.components.Children;
import com.rdebernard.phanes.components.Parent;
import com.rdebernard.phanes.components.Transform;

public class WorldTransformSystem extends System{
  public WorldTransformSystem(World world){
    super(world);
  }
	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    ArrayList<UUID> rootEntities = new ArrayList<>();
    for(UUID entityId: entities.keySet()){
      Parent parent = world.getComponent(entityId,Parent.class);
      if(parent == null){
        rootEntities.add(entityId);
      }
    }
    for(UUID entityId: rootEntities){
      calculateWordTransform(entityId);
    }
	}

  private void calculateWordTransform(UUID entityId){
    Transform transform = world.getComponent(entityId,Transform.class);
    Parent parent = world.getComponent(entityId,Parent.class);
    Children children = world.getComponent(entityId,Children.class);
    if(parent != null && parent.parent !=null){
      Transform parentTransform = world.getComponent(parent.parent, Transform.class);
      if(parentTransform !=null && transform !=null){
        transform.position.x = parentTransform.position.x + transform.localPosition.x;
        transform.position.y = parentTransform.position.y + transform.localPosition.y;
        transform.position.z = parentTransform.position.z + transform.localPosition.z;

        transform.rotation.x = parentTransform.rotation.x;
        transform.rotation.y = parentTransform.rotation.y ;
        transform.rotation.z = parentTransform.rotation.z ;

        transform.scale.x = parentTransform.scale.x;
        transform.scale.y = parentTransform.scale.y ;
        transform.scale.z = parentTransform.scale.z ;
      }
    }
    if(children !=null && children.children !=null){
      for(UUID childrenId: children.children){
        calculateWordTransform(childrenId);
      }
    }
  }
}

