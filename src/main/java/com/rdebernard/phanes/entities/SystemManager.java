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
      //T system = systeClass.getDeclaredConstructor(World.class).newInstance(this.world);
    systems.put(system.getClass(),system);
  }

  public <T extends SystemBase> T getSystem(Class<T> className){
    SystemBase system = systems.get(className);
    if(system == null){
      logger.warning("Attempting to get unregister system " + className.getName());
      return null;
    }
    return className.cast(system);
  }
  public <T extends SystemBase> void removeSystem(Class<T> className){
    systems.remove(className);
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
