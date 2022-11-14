package com.rdebernard.phanes.entities;
import java.util.ArrayList;
import java.util.Iterator;


public abstract class  SystemBase{
  protected World world;
  protected Entities entities;
  public SystemBase(World world){
    this.world = world;
    this.entities = new Entities();
  }
  public abstract void update(float dt);

  private class Entities{
    static ArrayList<Class<? extends Component>> all;
    static ArrayList<Class<? extends Component>> any;
    static ArrayList<Class<? extends Component>> none;
    public  Entities withAll(ArrayList<Class<? extends Component>> componentTypeList){
      all = null;
      all = componentTypeList;
      return this;
    }
    public Entities withAny(ArrayList<Class<? extends Component>> componentTypeList){
      any = null;
      any = componentTypeList;
      return this;
    }
    public  Entities withNone(ArrayList<Class<? extends Component>> componentTypeList){
      none = null;
      none = componentTypeList;
      return this;
    }
    @SafeVarargs
    final public void forEach(Job job,Class<? extends Component>... componentTypes){
      ArrayList<Entity> queryEntities = new ArrayList<>();
      ArrayList<EntityArchetype> entityArchetypes = new ArrayList<>();
      if(all == null) all = new ArrayList<>();
      for(Class<? extends Component> componentType: componentTypes){
        all.add(componentType);
      }
      for(EntityArchetype entityArchetype: world.entityManager.getEntityArchetypes()){
        if(entityArchetype.containAll(all)){
          entityArchetypes.add(entityArchetype);
        };
      }
      Iterator<EntityArchetype> itr = entityArchetypes.iterator();
      while(itr.hasNext()){
        EntityArchetype entityArchetype = itr.next();
        if(entityArchetype.containAny(any)){
          itr.remove();
        }
        else if(!entityArchetype.containNone(none)){
          itr.remove();
        }
      }
      for(EntityArchetype entityArchetype: entityArchetypes){
        queryEntities.addAll(world.entityManager.getEntities(entityArchetype));
      }
      switch(componentTypes.length){
        case 0:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity);
            }
          }
          break;
        case 1:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,world.componentManager.getComponent(entity,componentTypes[0]));
            }
          }
          break;
        case 2:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1]));
            }
          }
          break;
        case 3:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2]));
            }
          }
          break;
        case 4:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2])
                  ,world.componentManager.getComponent(entity,componentTypes[3]));
            }
          }
          break;
        case 5:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2])
                  ,world.componentManager.getComponent(entity,componentTypes[3])
                  ,world.componentManager.getComponent(entity,componentTypes[4]));
            }
          }
          break;
        case 6:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2])
                  ,world.componentManager.getComponent(entity,componentTypes[3])
                  ,world.componentManager.getComponent(entity,componentTypes[4])
                  ,world.componentManager.getComponent(entity,componentTypes[5]));
            }
          }
          break;
        case 7:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2])
                  ,world.componentManager.getComponent(entity,componentTypes[3])
                  ,world.componentManager.getComponent(entity,componentTypes[4])
                  ,world.componentManager.getComponent(entity,componentTypes[5])
                  ,world.componentManager.getComponent(entity,componentTypes[6]));
            }
          }
          break;
        default:
          {
            for(Entity entity: queryEntities){
              job.doWork(entity,
                  world.componentManager.getComponent(entity,componentTypes[0])
                  ,world.componentManager.getComponent(entity,componentTypes[1])
                  ,world.componentManager.getComponent(entity,componentTypes[2])
                  ,world.componentManager.getComponent(entity,componentTypes[3])
                  ,world.componentManager.getComponent(entity,componentTypes[4])
                  ,world.componentManager.getComponent(entity,componentTypes[5])
                  ,world.componentManager.getComponent(entity,componentTypes[6])
                  ,world.componentManager.getComponent(entity,componentTypes[7]));
            }
          }
          break;
      }
    }
  }
  protected abstract class Job{
    public void  doWork(Entity entity){}
    public <A extends Component> void  doWork(Entity entity,A a){
    }
    public <A,B extends Component> void  doWork(Entity entity,A a,B b){
    }
    public <A,B,C extends Component> void  doWork(Entity entity,A a,B b,C c){}
    public <A,B,C,D extends Component> void  doWork(Entity entity,A a,B b,C c,D d){}
    public <A,B,C,D,E extends Component> void  doWork(Entity entity,A a,B b,C c,D d,E e){}
    public <A,B,C,D,E,F extends Component> void  doWork(Entity entity,A a,B b,C c,D d,E e,F f){}
    public <A,B,C,D,E,F,G extends Component> void  doWork(Entity entity,A a,B b,C c,D d,E e,F f,G g){}
    public <A,B,C,D,E,F,G,H extends Component> void  doWork(Entity entity,A a,B b,C c,D d,E e,F f,G g,H h){}
  }

}
