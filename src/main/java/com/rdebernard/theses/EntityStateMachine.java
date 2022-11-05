package com.rdebernard.theses;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.rdebernard.theses.components.Component;

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

  public EntityState createEntityState(String name){
    EntityState entityState = new EntityState();
    states.put(name, entityState);
    return entityState;
  }
  public void addEntityState(String name,EntityState state){
    states.put(name, state);
  }
  public void removeEntityState(String name){
    states.remove(name);
  }
  public void changeEntityState(String name){
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
