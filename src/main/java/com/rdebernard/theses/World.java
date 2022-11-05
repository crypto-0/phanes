package com.rdebernard.theses;
import java.util.Map;
import java.util.UUID;
import com.rdebernard.theses.components.Component;
import com.rdebernard.theses.scenes.*;

public class World{
  private final ComponentManager componentManager;
  private final EntityManager entityManager;
  private final SystemManager systemManager;
  private final EntityStateMachineManager entityStateMachineManager;
  private final SceneManager sceneManager;
  public final Camera camera;
  public World(){
    componentManager = new ComponentManager();
    entityManager = new EntityManager();
    systemManager = new SystemManager(this);
    entityStateMachineManager = new EntityStateMachineManager(this);
    sceneManager = new SceneManager(this);
    camera = new Camera();
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

  public void addSystem(com.rdebernard.theses.sytems.System sytem){
    systemManager.addSystem(sytem);
  }

  public <T extends com.rdebernard.theses.sytems.System> T getSystem(Class<T> systemClass){
    return systemManager.getSystem(systemClass);
  }

  public  void addScene(String sceneName,Scene scene){
    sceneManager.addScene(sceneName,scene);
  }
  public void loadScene(String sceneName){
    sceneManager.loadScene(sceneName);
  }


  public void update(float dt){
    systemManager.update(dt);
  }

  public void clear(){
    systemManager.clearSystems();
    entityManager.clearEntities();
    componentManager.clearComponents();
    entityStateMachineManager.clearStateMachines();
  }

}
