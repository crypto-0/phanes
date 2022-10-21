package bolt;
import java.util.HashMap;
import java.util.Map;
import bolt.components.Component;
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
  public Map<Class<? extends Component>,Component> getAllComponent(){
    return this.components;
  }
}