package com.rdebernard.phanes.entities;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityStateMachineManager{
  private Map<UUID,EntityStateMachine> entityStateMachines;
  private World world;

  public EntityStateMachineManager(World world){
    this.world = world;
    this.entityStateMachines = new HashMap<>();
  }

  public EntityStateMachine createStateMachine(Entity entity){
    EntityStateMachine entityStateMachine = new EntityStateMachine(world, entity);
    entityStateMachines.put(entity.id,entityStateMachine) ;
    return entityStateMachine;
  }

  public void removeStateMachine(UUID entityId){
   entityStateMachines.remove(entityId); 
  }

  public EntityStateMachine getStateMachine(Entity entity){
    return entityStateMachines.get(entity.id);
  }

  public void entityRemoved(Entity entity){
    entityStateMachines.remove(entity.id);
  }

  public void clearStateMachines(){
    entityStateMachines.clear();
  }
}
