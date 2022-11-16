package com.rdebernard.phanes.entities;
import java.util.HashMap;
import java.util.Map;

public class EntityStateMachine{
  private Map<String,EntityState> states;
  private World world;
  private Entity entity;
  private EntityState currentState ;
  public EntityStateMachine(World world,Entity entity){
    this.states = new HashMap<String,EntityState>();
    this.world = world;
    this.entity = entity;
  }

  public EntityState createEntityState(String name){
    EntityState entityState = new EntityState();
    states.put(name, entityState);
    return entityState;
  }
  public void addEntityState(String name,EntityState state){
    states.put(name, state);
  }
  public void removeEntityState(String name){
    states.remove(name);
  }
  public void changeEntityState(String name){
    EntityState newState = states.get(name);
    if(newState != null){
      if(newState == currentState)return;
      if(currentState !=null){
        currentState.getAllComponent().stream().forEach(c->{
          world.componentManager.removeComponent(this.entity,c.getClass());
        });
      }
      newState.getAllComponent().stream().forEach(c->{
        world.componentManager.addComponent(entity,c);
      });
      currentState = newState;
    }
  }

}
