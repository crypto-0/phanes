package com.rdebernard.phanes;
import java.util.HashMap;
import java.util.Map;
import com.rdebernard.phanes.systems.System;
public class WorldState{
  Map<Class<? extends System>,System> systems;
  public WorldState(){
    this.systems = new HashMap<>();
  }
  public <T extends System> void addSystem(T system){
    systems.put(system.getClass(), system);
  }
  public <T extends System> T getSystem(Class<T> systemClass){
    return systemClass.cast(systems.get(systemClass));
  }
  public <T extends System> void removeSystem(Class<T> systemClass){
    systems.remove(systemClass);
  }
  public Map<Class<? extends System>,System> getAllSystems(){
    return this.systems;
  }
}
