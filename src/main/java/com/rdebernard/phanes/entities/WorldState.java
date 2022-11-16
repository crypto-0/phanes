package com.rdebernard.phanes.entities;
import java.util.HashMap;
import java.util.Map;
public class WorldState{
  Map<Class<? extends SystemBase>,SystemBase> systems;
  public WorldState(){
    this.systems = new HashMap<>();
  }
  @SafeVarargs
  final public <T extends SystemBase> void addSystem(T... systems){
    for(T system: systems){
      this.systems.put(system.getClass(),system);
    }
  }
  public <T extends SystemBase> T getSystem(Class<T> systemClass){
    return systemClass.cast(systems.get(systemClass));
  }
  @SafeVarargs
  final public <T extends SystemBase> void removeSystem(Class<T>... systemsClass){
    for(Class<T> systemClass: systemsClass){
      this.systems.remove(systemClass);
    }
  }
  public Map<Class<? extends SystemBase>,SystemBase> getAllSystems(){
    return this.systems;
  }
}
