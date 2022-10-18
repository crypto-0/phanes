package bolt;

import bolt.components.Component;

public class Engine{
  private final ComponentManager componentManager;
  private final EntityManager entityManager;
  private final SystemManager systemManager;
  public Engine(){
    componentManager = new ComponentManager();
    entityManager = new EntityManager();
    systemManager = new SystemManager();
  }

  public Entity createEntity(){
    return entityManager.createEntity();
  }

  public void DestroyEntity(Entity entity){
    entityManager.destroyEntity(entity);
  }

  public <T extends Component> void registerComponent(Class<T> componentClass){
    componentManager.registerComponent(componentClass);
  }

  public void addComponent(Entity entity,Component component){
    componentManager.addComponent(entity, component);
  }

  public <T extends Component> void removeComponent(Entity entity,Class<T> componentClass){
    componentManager.removeComponent(entity,componentClass);
  }
  public <T extends Component> void getComponent(Entity entity,Class<T> componentClass){
    componentManager.getComponent(entity,componentClass);
  }

  public <T extends bolt.sytems.System> void registerSystem(Class<T> systemClass){
    systemManager.registerSystem(systemClass);
  }

  public <T extends bolt.sytems.System> T getSystem(Class<T> systemClass){
    return systemManager.getSystem(systemClass);
  }

}
