package com.rdebernard.phanes;
import java.util.Map;
import java.util.UUID;

import com.rdebernard.phanes.components.Children;
import com.rdebernard.phanes.components.Component;
import com.rdebernard.phanes.components.Parent;
import com.rdebernard.phanes.scenes.*;
import com.rdebernard.phanes.managers.*;

public class World{
  private final ComponentManager componentManager;
  private final EntityManager entityManager;
  private final SystemManager systemManager;
  private final EntityStateMachineManager entityStateMachineManager;
  private final WorldStateMachine worldStateMachine;
  static final SceneManager sceneManager = new SceneManager();
  public final Camera camera;
  public World(){
    componentManager = new ComponentManager();
    entityManager = new EntityManager();
    systemManager = new SystemManager(this);
    entityStateMachineManager = new EntityStateMachineManager(this);
    worldStateMachine = new WorldStateMachine(this);
    camera = new Camera();
  }

  public Entity createEntity(){
    return entityManager.createEntity();
  }


  public void removeEntity(UUID entityId){
    Parent parent = componentManager.getComponent(entityId,Parent.class);
    if(parent !=null && parent.parent !=null){
      Children children = componentManager.getComponent(parent.parent,Children.class);
      if(children !=null && children.children !=null){
        children.children.remove(entityId);
      }
    }
    entityManager.removeEntity(entityId);
    componentManager.entityRemoved(entityId);
  }
  public void addChild(UUID parentEntityId,UUID childEntityId){
    Children children = componentManager.getComponent(parentEntityId,Children.class);
    if(children == null){
      children = new Children();
      children.children.add(childEntityId);
      componentManager.addComponent(parentEntityId,children);
    }
    else{
      children.children.add(childEntityId);
    }
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
  public WorldStateMachine getWorldStateMachine(){
    return worldStateMachine;
  }
  public void changeWorldState(String stateName){
    worldStateMachine.changeWorldState(stateName);
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

  public void addSystem(com.rdebernard.phanes.systems.System sytem){
    systemManager.addSystem(sytem);
  }
  public <T extends com.rdebernard.phanes.systems.System> void removeSystem(Class<T> systemClass){
    systemManager.removeSystem(systemClass);
  }

  public <T extends com.rdebernard.phanes.systems.System> T getSystem(Class<T> systemClass){
    return systemManager.getSystem(systemClass);
  }

  public  void addScene(String sceneName,Scene scene){
    sceneManager.addScene(sceneName,scene);
  }
  public void loadScene(String sceneName,Boolean additive){
    sceneManager.loadScene(sceneName,additive);
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
