package com.rdebernard.theses.managers;
import com.rdebernard.theses.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityManager{
  private Map<UUID,Entity> entities = new HashMap<UUID,Entity>();

  public Entity createEntity(){
    Entity entity = new Entity();
    entity.id = UUID.randomUUID();
    entities.put(entity.id, entity);
    return entity;
  }

  public void removeEntity(UUID id){
    entities.remove(id);
  }

  public Map<UUID,Entity> getAllEntity(){
    return entities;
  }

  public void clearEntities(){
    entities.clear();
  }
}
