package bolt;
import bolt.sytems.System;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SystemManager{
  private Map<Class<?>,System> systems = new HashMap<Class<?>,System>();
  private Logger logger = Logger.getAnonymousLogger(SystemManager.class.getName());
  public <T extends System> void registerSystem(Class<T> systeClass){
    if(systems.containsKey(systeClass)){
      logger.warning("Registring system more than once.");
      return;
    }
    try{
    systems.put(systeClass,systeClass.getDeclaredConstructor().newInstance());
    }
    catch(Exception e){
      logger.warning("Failed to register the system");
    }
  }

  public <T extends System> T getSystem(Class<T> className){
    System system = systems.get(className);
    if(system == null){
      logger.warning("Attempting to get unregister system");
      return null;
    }
    return className.cast(system);
  }
  
}
