package com.rdebernard.phanes.entities;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class PrefabManager{
  private World world;
  private HashMap<String,Prefab> prefabs;
  private Logger logger; 

  public PrefabManager(World world){
    this.world = world;
    prefabs = new HashMap<>();
    logger = Logger.getLogger(PrefabManager.class.getName());
  }
  public Prefab createPrefab(String name,Component... components){
    if(this.prefabs.containsKey(name)){
      logger.warning("Prefab with name " + name + " already exitsts");
      return null;
    }
    else{
      Prefab prefab = new Prefab(components);
      this.prefabs.put(name,prefab);
      return prefab;
    }
  }
  public void removePrefab(String name){
    if(!this.prefabs.containsKey(name)){
      logger.warning("Attempting to remove Prefab with name " + name + " that does not exitsts");
    }
    else{
      this.prefabs.remove(name);
    }
  }
  public void clearPrefabs(){
    prefabs.clear();
  }

  public Entity instanciatePrefab(String name){
    if(prefabs.containsKey(name)){
      ArrayList<Component> components = prefabs.get(name).getComponents();
      Entity entity = world.entityManager.createEntity();
      components.forEach(c->{
        try{
          Constructor<? extends Component> constructor =c.getClass().getConstructor(c.getClass());
          Component component = constructor.newInstance(c);
          world.componentManager.addComponent(entity, component);
        }
        catch(Exception e){
          logger.warning("Failed to instanciate component " +c.getClass().getName());
        }
      });
      return entity;
    }
    else{
      logger.warning("Trying to instanciate prefab with name " + name + " that does not exitsts");
      return null;
    }
  }
}
