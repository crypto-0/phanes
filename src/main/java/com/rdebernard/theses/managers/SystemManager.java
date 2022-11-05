package com.rdebernard.theses.managers;
import com.rdebernard.theses.systems.System;
import com.rdebernard.theses.World;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SystemManager{
  private Map<Class<?>,System> systems = new LinkedHashMap<>();
  private Logger logger = Logger.getLogger(SystemManager.class.getName());
  private World world;
  public SystemManager(World world){
    this.world = world;
  }
  public void addSystem(System system){
    if(systems.containsKey(system.getClass())){
      logger.warning("Registring " + system.getClass().getName() +  "more than once.");
      return;
    }
      //T system = systeClass.getDeclaredConstructor(World.class).newInstance(this.world);
    systems.put(system.getClass(),system);
  }

  public <T extends System> T getSystem(Class<T> className){
    System system = systems.get(className);
    if(system == null){
      logger.warning("Attempting to get unregister system " + className.getName());
      return null;
    }
    return className.cast(system);
  }

  public void update(float dt){
    for(System system : systems.values()){
      system.update(dt);
    }
  }

  public void clearSystems(){
    systems.clear();
  }
  
}
