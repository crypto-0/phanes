package com.rdebernard.phanes.managers;
import com.rdebernard.phanes.*;
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

  public EntityStateMachine createStateMachine(UUID entityId){
    EntityStateMachine entityStateMachine = new EntityStateMachine(world, entityId);
    entityStateMachines.put(entityId,entityStateMachine) ;
    return entityStateMachine;
  }

  public void removeStateMachine(UUID entityId){
   entityStateMachines.remove(entityId); 
  }

  public EntityStateMachine getStateMachine(UUID entityId){
    return entityStateMachines.get(entityId);
  }

  public void entityRemoved(UUID entityId){
    entityStateMachines.remove(entityId);
  }

  public void clearStateMachines(){
    entityStateMachines.clear();
  }
}
