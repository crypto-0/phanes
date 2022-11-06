package com.rdebernard.phanes;
import java.util.HashMap;
import java.util.Map;
import com.rdebernard.phanes.components.Component;
public class EntityState{
  Map<Class<? extends Component>,Component> components;
  public EntityState(){
    this.components = new HashMap<>();
  }
  public <T extends Component> void addComponent(T component){
    components.put(component.getClass(), component);
  }
  public <T extends Component> T getComponent(Class<T> componentClass){
    return componentClass.cast(components.get(componentClass));
  }
  public <T extends Component> void removeComponent(Class<T> componentClass){
    components.remove(componentClass);
  }
  public Map<Class<? extends Component>,Component> getAllComponent(){
    return this.components;
  }
}
