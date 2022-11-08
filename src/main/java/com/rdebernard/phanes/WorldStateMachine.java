package com.rdebernard.phanes;
import java.util.HashMap;
import java.util.Map;
import com.rdebernard.phanes.systems.System;

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
        Map<Class<? extends System>,System> systems = currentState.getAllSystems();
        for(Class<? extends com.rdebernard.phanes.systems.System> systemClass: systems.keySet()){
          world.removeSystem(systemClass);
        }
      }
      for(System system: newState.getAllSystems().values()){
        world.addSystem(system);
      }
      currentState = newState;
    }
  }

}
