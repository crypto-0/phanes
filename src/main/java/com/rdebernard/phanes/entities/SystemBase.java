package com.rdebernard.phanes.entities;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class  SystemBase{
  protected World world;
  public SystemBase(World world){
    this.world = world;
  }
  public abstract void update(float dt);

  protected final class EntityQuery{
    private HashSet<Entity> matchedEntities;
    private ArrayList<Class<? extends Component>> all;
    private ArrayList<Class<? extends Component>> any;
    private ArrayList<Class<? extends Component>> none;
    public EntityQuery(){
      all = new ArrayList<>();
      any = new ArrayList<>();
      none = new ArrayList<>();
      matchedEntities = new HashSet<>();
    }
    @SafeVarargs
    final public void withAll(Class<? extends Component>... componentTypes){
      all.clear();
      for(Class<? extends Component> componentType: componentTypes){
        all.add(componentType);
      }
    }
    @SafeVarargs
    final public  void  withAny(Class<? extends Component>... componentTypes){
      any.clear();
      for(Class<? extends Component> componentType: componentTypes){
        any.add(componentType);
      }
    }
    @SafeVarargs
    final public  void  withNone(Class<? extends Component>... componentTypes){
      none.clear();
      for(Class<? extends Component> componentType: componentTypes){
        none.add(componentType);
      }
    }
    final public void resetFilter(){
      all.clear();
      any.clear();
      none.clear();
    }
    final public void query(){
      matchedEntities.clear();
      world.entityManager.getEntityArchetypes().stream().filter(entityArchetype->{
        return entityArchetype.containAll(all);
      }).filter(entityArchetype->{
        return entityArchetype.containAny(any);
      }).filter(entityArchetype->{
        return entityArchetype.containNone(none);
      }).forEach(entityArchetype->{
        matchedEntities.addAll(world.entityManager.getEntities(entityArchetype));
      });
      //System.out.println(matchedEntities.size() + ": sizes");
    }
    final public ArrayList<Entity> getEntities(){
      return new ArrayList<>(matchedEntities);
    }
    final public <T extends Component> ArrayList<T> getComponents(Class<T> componentType){
      ArrayList<T> components = new ArrayList<>();
      matchedEntities.forEach(e->{
        components.add(world.componentManager.getComponent(e, componentType));
      });
      return components;
    }
  }
}
