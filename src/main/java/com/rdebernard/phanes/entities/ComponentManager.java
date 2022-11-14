package com.rdebernard.phanes.entities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.lang.Integer;

public class ComponentManager{
  private Map<Class<?>,Integer> componentTypes ;
  private Map<Integer,Map<UUID,Component>> components;
  private World world;
  private Logger logger; 
  public ComponentManager(World world){
  componentTypes = new HashMap<Class<?>,Integer>();
  components = new HashMap<Integer,Map<UUID,Component>>();
  this.world = world;
  logger = Logger.getLogger(ComponentManager.class.getName());
  }
  public <T extends Component> void registerComponent(Class<T> componentClass){
    if(componentTypes.get(componentClass)!= null){
      logger.warning("Registering component type" + componentClass.getName() + " more than once.");
      return;
    }
    componentTypes.put(componentClass, componentTypes.size() + 1);
    components.put(componentTypes.size(), new HashMap<UUID,Component>());
  }

  public < T extends Component> void addComponent(Entity entity,Component component){
    Integer componentType;
    componentType = componentTypes.get(component.getClass());
    if(componentType == null){
      logger.warning("Attempting to add unregister component " + component.getClass().getName());
      return;
    }
    components.get(componentType).put(entity.id, component);
    world.entityManager.componentAdded(entity, component);
  }
  public < T extends Component> void addComponents(Entity entity,ArrayList<Component> componentList){
    Integer componentType;
    for(Component component: componentList){
      componentType = componentTypes.get(component.getClass());
      if(componentType == null){
        logger.warning("Attempting to add unregister component " + component.getClass().getName());
        continue;
      }
      this.components.get(componentType).put(entity.id,component);
    }
    world.entityManager.componentsAdded(entity,componentList);
  }


  public < T extends Component> void removeComponent(Entity entity,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to remove unregister component " + componentClass.getName());
      return;
    }
    components.get(componentType).remove(entity.id);
  }
  public < T extends Component> void removeComponents(Entity entity,ArrayList<Component> componentList){
    Integer componentType;
    for(Component component: componentList){
      componentType = componentTypes.get(component.getClass());
      if(componentType == null){
        logger.warning("Attempting to remove unregister component " + component.getClass().getName());
        continue;
      }
      this.components.get(componentType).remove(entity.id);
    }
    world.entityManager.componentsRemoved(entity,componentList);
  }

  public <T extends Component> T getComponent(Entity entity,Class<T> componentClass){
    Integer componentType;
    componentType = componentTypes.get(componentClass);
    if(componentType == null){
      logger.warning("Attempting to get unregister component " + componentClass.getName());
      return null;
    }
    Component component = components.get(componentType).get(entity.id);
    return componentClass.cast(component);
  }

  public void entityRemoved(Entity entity){
    for(Integer componentType: componentTypes.values()){
      components.get(componentType).remove(entity.id);
    }
  }

  public void clearComponents(){
    componentTypes.clear();
    components.clear();
  }

}
