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
    private 
    EntityQuery(){
      all = new ArrayList<>();
      any = new ArrayList<>();
      none = new ArrayList<>();
      matchedEntities = new HashSet<>();
    }
    @SafeVarargs
    final public <T extends Component> void  withAll(Class<T>... componentTypes){
      all.clear();
      for(Class<T> componentType: componentTypes){
        all.add(componentType);
      }
    }
    @SafeVarargs
    final public <T extends Component> void  withAny(Class<T>... componentTypes){
      any.clear();
      for(Class<T> componentType: componentTypes){
        any.add(componentType);
      }
    }
    @SafeVarargs
    final public <T extends Component> void  withNone(Class<T>... componentTypes){
      none.clear();
      for(Class<T> componentType: componentTypes){
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
