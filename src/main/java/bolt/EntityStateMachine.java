package bolt;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import bolt.components.Component;

public class EntityStateMachine{
  private Map<String,EntityState> states;
  private World world;
  private UUID entityId;
  private EntityState currentState ;
  public EntityStateMachine(World world,UUID entityId){
    this.states = new HashMap<String,EntityState>();
    this.world = world;
    this.entityId = entityId;
  }

  public void addState(String name,EntityState state){
    states.put(name, state);
  }
  public void removeState(String name){
    states.remove(name);
  }
  public void changeState(String name){
    EntityState newState = states.get(name);
    if(newState != null){
      if(newState == currentState)return;
      if(currentState !=null){
        Map<Class<? extends Component>,Component> components = currentState.getAllComponent();
        for(Class<? extends Component> componentClass: components.keySet()){
          world.removeComponent(entityId,componentClass);
        }
      }
      for(Component component: newState.getAllComponent().values()){
        world.addComponent(entityId, component);
      }
      currentState = newState;
    }
  }

}
