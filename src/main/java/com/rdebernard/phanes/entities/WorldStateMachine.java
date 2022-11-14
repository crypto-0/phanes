package com.rdebernard.phanes.entities;
import java.util.HashMap;
import java.util.Map;

public class WorldStateMachine{
  private Map<String,WorldState> states;
  private World world;
  private WorldState currentState ;
  public WorldStateMachine(World world){
    this.states = new HashMap<String,WorldState>();
    this.world = world;
  }

  public WorldState createworldState(String name){
    WorldState worldState = new WorldState();
    states.put(name, worldState);
    return worldState;
  }
  public void addWorldState(String name,WorldState state){
    states.put(name, state);
  }
  public void removeWorldState(String name){
    states.remove(name);
  }
  public void changeWorldState(String name){
    WorldState newState = states.get(name);
    if(newState != null){
      if(newState == currentState)return;
      if(currentState !=null){
        Map<Class<? extends SystemBase>,SystemBase> systems = currentState.getAllSystems();
        for(Class<? extends SystemBase> systemClass: systems.keySet()){
          world.systemManager.removeSystem(systemClass);
        }
      }
      for(SystemBase system: newState.getAllSystems().values()){
        world.systemManager.addSystem(system);
      }
      currentState = newState;
    }
  }

}
