package bolt;

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

  public void destroyEntity(Entity entity){
    entities.remove(entity.id);
  }
}
