package bolt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.lang.Integer;
import bolt.components.Component;

public class ComponentManager{
  private Map<Class<?>,Integer> componentTypes = new HashMap<Class<?>,Integer>();
  private Map<Integer,Map<UUID,Component>> components = new HashMap<Integer,Map<UUID,Component>>();
  private Logger logger = Logger.getAnonymousLogger(ComponentManager.class.getName());
  public <T extends Component> void registerComponent(Class<T> componentClass){
    if(componentTypes.get(componentClass)== null){
      logger.warning("Registering component type more than once.");
      return;
    }
    componentTypes.put(componentClass, componentTypes.size() + 1);
    components.put(componentTypes.size(), new HashMap<UUID,Component>());
  }

  public < T extends Component> void addComponent(Entity entity,Component component){
    Integer componentType;
    componentType = componentTypes.get(component.getClass());
    if(componentType == null){
      logger.warning("Attempting to add unregister component");
      return;
    }
    components.get(componentType).put(entity.id, component);
  }

  public < T extends Component> void removeComponent(Entity entity,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to remove unregister component");
      return;
    }
    components.get(componentType).remove(entity.id);
  }

  public <T extends Component> T getComponent(Entity entity,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to get unregister component");
      return null;
    }
    Component component = components.get(componentType).get(entity.id);
    return componentClass.cast(component);
  }

  public void entityDestroyed(Entity entity){
    for(Integer componentType: componentTypes.values()){
      components.get(componentType).remove(entity.id);
    }
  }

}
