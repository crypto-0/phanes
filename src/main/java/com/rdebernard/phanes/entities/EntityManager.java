package com.rdebernard.phanes.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EntityManager{
  private final Map<EntityArchetype,HashSet<Entity>> entities;
  private final Map<Entity,EntityArchetype> entityArchetypes;
  private final HashMap<EntityArchetype,HashMap<Class<? extends Component>,EntityArchetypeEdge>> entityArchetypeEdges ;
  private final World world;

  public EntityManager(World world){
    entities = new HashMap<EntityArchetype,HashSet<Entity>>();
    entityArchetypes = new HashMap<>();
    EntityArchetype entityArchetype = new EntityArchetype();
    entities.put(entityArchetype,new HashSet<Entity>());
    entityArchetypeEdges = new HashMap<>();
    entityArchetypeEdges.put(entityArchetype,new HashMap<>());
    this.world = world;
  }
  public Entity createEntity(){
    Entity entity = new Entity();
    EntityArchetype entityArchetype = createArchetype();
    entities.get(entityArchetype).add(entity);
    entityArchetypes.put(entity,entityArchetype);
    entityArchetypeEdges.put(entityArchetype,new HashMap<>());
    return entity;
  }
  public Entity createEntity(EntityArchetype entityArchetype){
    Entity entity = new Entity();
    if(!entities.containsKey(entityArchetype)){
      entities.put(entityArchetype,new HashSet<>());
      entityArchetypeEdges.put(entityArchetype,new HashMap<>());
    }
    entities.get(entityArchetype).add(entity);
    entityArchetypes.put(entity,entityArchetype);
    entityArchetype.getComponentTypes().stream().forEach(ct->{
      try{
        world.componentManager.addComponent(entity,ct.getConstructor().newInstance());
      }
      catch(Exception e){
        System.out.println("Failed to create component of " + ct.getTypeName());
      }

    });
    return entity;

  }
  @SafeVarargs
  final public EntityArchetype createArchetype(Class<? extends Component>... componentTypes){
    EntityArchetype entityArchetype =  new EntityArchetype(componentTypes);
    if(!entities.containsKey(entityArchetype)){
      entities.put(entityArchetype,new HashSet<Entity>());
      entityArchetypeEdges.put(entityArchetype,new HashMap<>());
    }
    return entityArchetype;
  }
  public EntityArchetype createArchetype(ArrayList<Class<? extends Component>> componentTypes){
    EntityArchetype entityArchetype =  new EntityArchetype(componentTypes);
    if(!entities.containsKey(entityArchetype)){
      entities.put(entityArchetype,new HashSet<Entity>());
      entityArchetypeEdges.put(entityArchetype,new HashMap<>());
    }
    return entityArchetype;
  }
  public void removeEntity(Entity entity){
    EntityArchetype entityArchetype = entityArchetypes.get(entity);
    if(entityArchetype == null)return;
    entities.get(entityArchetype).remove(entity);
  }
  public ArrayList<EntityArchetype> getEntityArchetypes(){
    ArrayList<EntityArchetype> archetypes = new ArrayList<>(entities.keySet());
    return archetypes;
  }
  public ArrayList<Entity> getEntities(){
    ArrayList<Entity> allEntities = new ArrayList<>();
    entities.values().stream().forEach(at->{
      at.stream().forEach(e->{
        allEntities.add(e);
      });
    });
    return allEntities;
  }
  public ArrayList<Entity> getEntities(EntityArchetype entityArchetype){
    ArrayList<Entity> allEntities = new ArrayList<>();
    if(!entities.containsKey(entityArchetype))return allEntities;
    entities.get(entityArchetype).stream().forEach(e->{
      allEntities.add(e);
    });
    return allEntities;
  }
  public void componentAdded(Entity entity,Class<? extends Component> componentType){
    EntityArchetype entityArchetype = entityArchetypes.get(entity);
    if(entityArchetype !=null){
      if(!entityArchetypeEdges.get(entityArchetype).containsKey(componentType)){
        EntityArchetypeEdge entityArchetypeEdge = new EntityArchetypeEdge();
        entityArchetypeEdges.get(entityArchetype).put(componentType, entityArchetypeEdge);
      }
      EntityArchetypeEdge entityArchetypeEdge = entityArchetypeEdges.get(entityArchetype).get(componentType);
      if(entityArchetypeEdge.add !=null){
        entities.get(entityArchetype).remove(entity);
        this.entityArchetypes.replace(entity,entityArchetypeEdge.add);
        entities.get(entityArchetypeEdge.add).add(entity);
      }
      else{
        ArrayList<Class<? extends Component>> archetypeComponents = entityArchetype.getComponentTypes();
        archetypeComponents.add(componentType);
        EntityArchetype newEntityArchetype  = createArchetype(archetypeComponents);
        entityArchetypeEdge.add = newEntityArchetype;
        entities.get(entityArchetype).remove(entity);
        entities.get(newEntityArchetype).add(entity);
        this.entityArchetypes.replace(entity,newEntityArchetype);
      }
    }

  }
  public void componentRemoved(Entity entity,Class<? extends Component> componentType){
    EntityArchetype entityArchetype = entityArchetypes.get(entity);
    if(entityArchetype !=null){
      if(!entityArchetypeEdges.get(entityArchetype).containsKey(componentType)){
        EntityArchetypeEdge entityArchetypeEdge = new EntityArchetypeEdge();
        entityArchetypeEdges.get(entityArchetype).put(componentType, entityArchetypeEdge);
      }
      EntityArchetypeEdge entityArchetypeEdge = entityArchetypeEdges.get(entityArchetype).get(componentType);
      if(entityArchetypeEdge.remove !=null){
        entities.get(entityArchetype).remove(entity);
        entities.get(entityArchetypeEdge.remove).add(entity);
        this.entityArchetypes.replace(entity,entityArchetypeEdge.remove);
      }
      else{
        ArrayList<Class<? extends Component>> archetypeComponents = entityArchetype.getComponentTypesExclude(componentType);
        EntityArchetype newEntityArchetype  = createArchetype(archetypeComponents);
        entityArchetypeEdge.remove = newEntityArchetype;
        entities.get(entityArchetype).remove(entity);
        entities.get(newEntityArchetype).add(entity);
        this.entityArchetypes.replace(entity,newEntityArchetype);
      }
    }
  }
  public void clearEntities(){
    entityArchetypes.clear();
    entities.clear();
    entityArchetypeEdges.clear();
  }
}

