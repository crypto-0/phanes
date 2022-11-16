package com.rdebernard.phanes.entities;
import com.rdebernard.phanes.scenes.*;

public class World{
  public final ComponentManager componentManager;
  public final EntityManager entityManager;
  public final SystemManager systemManager;
  public final EntityStateMachineManager entityStateMachineManager;
  private final WorldStateMachine worldStateMachine;
  public static final SceneManager sceneManager = new SceneManager();
  public World(){
    componentManager = new ComponentManager(this);
    entityManager = new EntityManager(this);
    systemManager = new SystemManager(this);
    entityStateMachineManager = new EntityStateMachineManager(this);
    worldStateMachine = new WorldStateMachine(this);
  }
}
