package bolt;
import java.util.Map;
import java.util.UUID;

import bolt.components.Component;

public class World{
  private final ComponentManager componentManager;
  private final EntityManager entityManager;
  private final SystemManager systemManager;
  private final EntityStateMachineManager entityStateMachineManager;
  public World(){
    componentManager = new ComponentManager();
    entityManager = new EntityManager();
    systemManager = new SystemManager(this);
    entityStateMachineManager = new EntityStateMachineManager(this);
  }

  public Entity createEntity(){
    return entityManager.createEntity();
  }


  public void removeEntity(UUID entityId){
    entityManager.removeEntity(entityId);
    componentManager.entityRemoved(entityId);
  }

  public EntityStateMachine createStateMachine(UUID entityId){
    return entityStateMachineManager.createStateMachine(entityId);
  }
  public void removeStateMachine(UUID entityId){
    entityStateMachineManager.removeStateMachine(entityId);
  }
  public EntityStateMachine getStateMachine(UUID entityId){
    return entityStateMachineManager.getStateMachine(entityId);
  }
  public void changeEntityState(UUID entityId,String stateName){
    EntityStateMachine entityStateMachine = entityStateMachineManager.getStateMachine(entityId);
    if(entityStateMachine !=null){
      entityStateMachine.changeEntityState(stateName);
    }
  }

  
  public Map<UUID,Entity> getAllEntity(){
    return entityManager.getAllEntity();
  }

  public <T extends Component> void registerComponent(Class<T> componentClass){
    componentManager.registerComponent(componentClass);
  }

  public void addComponent(UUID entityId,Component component){
    componentManager.addComponent(entityId, component);
  }

  public <T extends Component> void removeComponent(UUID entityId,Class<T> componentClass){
    componentManager.removeComponent(entityId,componentClass);
  }
  public <T extends Component> T getComponent(UUID entityId,Class<T> componentClass){
    return componentManager.getComponent(entityId,componentClass);
  }

  public <T extends bolt.sytems.System> void registerSystem(Class<T> systemClass){
    systemManager.registerSystem(systemClass);
  }

  public <T extends bolt.sytems.System> T getSystem(Class<T> systemClass){
    return systemManager.getSystem(systemClass);
  }

  public void update(long dt){
    systemManager.update(dt);
  }

}
