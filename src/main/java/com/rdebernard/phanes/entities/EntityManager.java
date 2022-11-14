package com.rdebernard.phanes.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EntityManager{
  private final Map<EntityArchetype,HashSet<Entity>> entities;
  private final HashSet<EntityArchetype> entityArchetypes;
  private final World world;

  public EntityManager(World world){
    entities = new HashMap<EntityArchetype,HashSet<Entity>>();
    entityArchetypes = new HashSet<>();
    EntityArchetype entityArchetype = new EntityArchetype();
    entityArchetypes.add(entityArchetype);
    entities.put(entityArchetype,new HashSet<Entity>());
    this.world = world;
  }
  public Entity createEntity(){
    Entity entity = new Entity();
    EntityArchetype entityArchetype = new EntityArchetype();
    entities.get(entityArchetype).add(entity);
    return entity;
  }
  public Entity createEntity(EntityArchetype entityArchetype){
    Entity entity = new Entity();
    if(!entityArchetypes.contains(entityArchetype)){
      entityArchetypes.add(entityArchetype);
    }
    entities.get(entityArchetype).add(entity);
    ArrayList<Component> components = new ArrayList<>();
    for(Class<? extends Component> componentType: entityArchetype.getComponentTypes()){
      try{
        components.add(componentType.getConstructor().newInstance());
      }
      catch(Exception e){
        System.out.println("Failed to create component of " +componentType.getTypeName());
      }
    }
    world.componentManager.addComponents(entity,components);
    return entity;

  }
  @SafeVarargs
  final public EntityArchetype createArchetype(Class<? extends Component>... componentTypes){
    EntityArchetype entityArchetype =  new EntityArchetype(componentTypes);
    if(!entityArchetypes.contains(entityArchetype)){
      entityArchetypes.add(entityArchetype);
    }
    return entityArchetype;
  }
  final public EntityArchetype createArchetype(ArrayList<Class<? extends Component>> componentTypes){
    EntityArchetype entityArchetype =  new EntityArchetype(componentTypes);
    if(!entityArchetypes.contains(entityArchetype)){
      entityArchetypes.add(entityArchetype);
    }
    return entityArchetype;
  }

  public void removeEntity(Entity entity){
    for(EntityArchetype archetype: entities.keySet()){
      if(entities.get(archetype).contains(entity)){
        entities.get(archetype).remove(entity);
        return;
      }
    }
  }
  public ArrayList<EntityArchetype> getEntityArchetypes(){
    ArrayList<EntityArchetype> archetypes = new ArrayList<>();
    for(EntityArchetype archetype: entityArchetypes){
      archetypes.add(archetype);
    }
    return archetypes;
  }
  public ArrayList<Entity> getEntities(){
    ArrayList<Entity> allEntities = new ArrayList<>();
    for(EntityArchetype archetype: entityArchetypes){
      for(Entity entity: entities.get(archetype)){
        allEntities.add(entity);
      }
    }
    return allEntities;
  }
  public ArrayList<Entity> getEntities(EntityArchetype entityArchetype){
    ArrayList<Entity> allEntities = new ArrayList<>();
    HashSet<Entity> entitiesOfArchetype = entities.get(entityArchetype);
    if(entitiesOfArchetype !=null){
      for(Entity entity: entities.get(entityArchetype)){
        allEntities.add(entity);
      }
      return allEntities;
    }
    else return null;
  }
  public void componentAdded(Entity entity,Component component){
    for(EntityArchetype entityArchetype: entities.keySet()){
      if(entities.get(entityArchetype).contains(entity)){
        ArrayList<Class<? extends Component>> entityComponentTypes = entityArchetype.getComponentTypes();
        entityComponentTypes.add(component.getClass());
        EntityArchetype newArchetype = new EntityArchetype(entityComponentTypes);
        if(entityArchetypes.contains(newArchetype)){
          entities.get(newArchetype).add(entity);
        }
        else{
          entityArchetypes.add(newArchetype);
          entities.put(newArchetype,new HashSet<Entity>());
          entities.get(entityArchetype).add(entity);
        }
        return;
      }
    }

  }
  public void componentsAdded(Entity entity,ArrayList<Component> componentList){
    for(EntityArchetype entityArchetype: entities.keySet()){
      if(entities.get(entityArchetype).contains(entity)){
        ArrayList<Class<? extends Component>> entityComponentTypes = entityArchetype.getComponentTypes();
        entities.get(entityArchetype).remove(entity);
        for(Component component: componentList){
          entityComponentTypes.add(component.getClass());
        }
        EntityArchetype newArchetype = new EntityArchetype(entityComponentTypes);
        if(entityArchetypes.contains(newArchetype)){
          entities.get(newArchetype).add(entity);
        }
        else{
          entityArchetypes.add(newArchetype);
          entities.put(newArchetype,new HashSet<Entity>());
          entities.get(entityArchetype).add(entity);
        }
        return;
      }
    }
  }
  public void componentRemoved(Entity entity,Component component){
    for(EntityArchetype entityArchetype: entities.keySet()){
      if(entities.get(entityArchetype).contains(entity)){
        ArrayList<Class<? extends Component>> entityComponentTypes = entityArchetype.getComponentTypes();
        entityComponentTypes.remove(component.getClass());
        EntityArchetype newArchetype = new EntityArchetype(entityComponentTypes);
        if(entityArchetypes.contains(newArchetype)){
          entities.get(newArchetype).add(entity);
        }
        else{
          entityArchetypes.add(newArchetype);
          entities.put(newArchetype,new HashSet<Entity>());
          entities.get(entityArchetype).add(entity);
        }
        return;
      }
    }
  }
  public void componentsRemoved(Entity entity,ArrayList<Component> componentList){
    for(EntityArchetype entityArchetype: entities.keySet()){
      if(entities.get(entityArchetype).contains(entity)){
        ArrayList<Class<? extends Component>> entityComponentTypes = entityArchetype.getComponentTypes();
        for(Component component: componentList){
          entityComponentTypes.remove(component.getClass());
        }
        EntityArchetype newArchetype = new EntityArchetype(entityComponentTypes);
        if(entityArchetypes.contains(newArchetype)){
          entities.get(newArchetype).add(entity);
        }
        else{
          entityArchetypes.add(newArchetype);
          entities.put(newArchetype,new HashSet<Entity>());
          entities.get(entityArchetype).add(entity);
        }
        return;
      }
    }
  }
}

