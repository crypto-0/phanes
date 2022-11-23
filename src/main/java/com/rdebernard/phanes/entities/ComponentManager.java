package com.rdebernard.phanes.entities;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ComponentManager{
  private Map<Class<? extends Component>,Map<Entity,Component>> components;
  private World world;
  private Logger logger; 
  public ComponentManager(World world){
    components = new HashMap<>();
    this.world = world;
    logger = Logger.getLogger(ComponentManager.class.getName());
  }

  @SafeVarargs
  final public void registerComponents(Class<? extends Component>... componentsType){
    for(Class<? extends Component> componentType: componentsType){
      if(this.components.containsKey(componentType)){
        logger.warning("Registering component type" + componentType.getName() + " more than once.");
        continue;
      }
      components.put(componentType, new HashMap<Entity,Component>());
    }
  }

  public void addComponent(Entity entity,Component component){
    if(!this.components.containsKey(component.getClass())){
      logger.warning("Attempting to add unregister component " + component.getClass().getName());
      return;
    }
    this.components.get(component.getClass()).put(entity,component);
    world.entityManager.componentAdded(entity,component.getClass());
  }

  public void removeComponent(Entity entity,Class< ? extends Component> componentType){
    if(!this.components.containsKey(componentType)){
      logger.warning("Attempting to remove unregister component " + componentType.getName());
      return;
    }
    this.components.get(componentType).remove(entity);
    world.entityManager.componentRemoved(entity,componentType);
  }

  public <T extends Component> T getComponent(Entity entity,Class<T> componentType){
    if(!components.containsKey(componentType)){
      logger.warning("Attempting to get unregister component " + componentType.getName());
      return null;
    }
    return componentType.cast(components.get(componentType).get(entity));
  }

  public void entityRemoved(Entity entity){
    components.keySet().stream().forEach(cp->{
      components.get(cp).remove(entity);
    });
  }

  public void clearComponents(){
    components.clear();
  }
}
