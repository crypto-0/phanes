package bolt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.lang.Integer;
import bolt.components.Component;

public class ComponentManager{
  private Map<Class<?>,Integer> componentTypes ;
  private Map<Integer,Map<UUID,Component>> components;
  public ComponentManager(){
  componentTypes = new HashMap<Class<?>,Integer>();
  components = new HashMap<Integer,Map<UUID,Component>>();
  }
  private Logger logger = Logger.getLogger(ComponentManager.class.getName());
  public <T extends Component> void registerComponent(Class<T> componentClass){
    if(componentTypes.get(componentClass)!= null){
      logger.warning("Registering component type more than once.");
      return;
    }
    componentTypes.put(componentClass, componentTypes.size() + 1);
    components.put(componentTypes.size(), new HashMap<UUID,Component>());
  }

  public < T extends Component> void addComponent(UUID entityId,Component component){
    Integer componentType;
    componentType = componentTypes.get(component.getClass());
    if(componentType == null){
      logger.warning("Attempting to add unregister component");
      return;
    }
    components.get(componentType).put(entityId, component);
  }

  public < T extends Component> void removeComponent(UUID entityId,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to remove unregister component");
      return;
    }
    components.get(componentType).remove(entityId);
  }

  public <T extends Component> T getComponent(UUID entityId,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to get unregister component");
      return null;
    }
    Component component = components.get(componentType).get(entityId);
    return componentClass.cast(component);
  }

  public void entityRemoved(UUID entityId){
    for(Integer componentType: componentTypes.values()){
      components.get(componentType).remove(entityId);
    }
  }

}
