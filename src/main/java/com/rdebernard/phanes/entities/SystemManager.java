package com.rdebernard.phanes.entities;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SystemManager{
  private Map<Class<?>,SystemBase> systems = new LinkedHashMap<>();
  private Logger logger = Logger.getLogger(SystemManager.class.getName());
  private World world;
  public SystemManager(World world){
    this.world = world;
  }
  public void addSystem(SystemBase system){
    if(systems.containsKey(system.getClass())){
      logger.warning("Registring " + system.getClass().getName() +  "more than once.");
      return;
    }
    systems.put(system.getClass(),system);
  }

  public <T extends SystemBase> T getSystem(Class<T> systemType){
    SystemBase system = systems.get(systemType);
    if(system == null){
      logger.warning("Attempting to get unregister system " + systemType.getName());
      return null;
    }
    return systemType.cast(system);
  }
  public <T extends SystemBase> void removeSystem(Class<T> systemType){
    systems.remove(systemType);
  }

  public void update(float dt){
    for(SystemBase systemBase : systems.values()){
      systemBase.update(dt);
    }
  }

  public void clearSystems(){
    systems.clear();
  }
  
}
