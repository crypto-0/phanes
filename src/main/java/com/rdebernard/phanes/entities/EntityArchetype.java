package com.rdebernard.phanes.entities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class  EntityArchetype{
  private HashSet<Class<? extends Component>> componentTypes;
  @SafeVarargs
  public EntityArchetype(Class<? extends Component>... componentTypes){
    this.componentTypes = new HashSet<>();
    for (Class<? extends Component> componentType: componentTypes){
      if(componentType ==null)continue;
      this.componentTypes.add(componentType);
    }
  }
  public EntityArchetype(ArrayList<Class<? extends Component>> componentTypes){
    this.componentTypes = new HashSet<>();
    for (Class<? extends Component> componentType: componentTypes){
      if(componentType ==null)continue;
      this.componentTypes.add(componentType);
    }
  
  }
  public boolean containAll(ArrayList<Class<? extends Component>> componentTypes){
    if(componentTypes.isEmpty()) return true;
    for(Class<? extends Component> component: componentTypes){
      if(!this.componentTypes.contains(component)){
        return false;
      }
    }
    return true;
  }
  public boolean containAny(ArrayList<Class<? extends Component>> componentTypes){
    if(componentTypes.isEmpty()) return true;
    for(Class<? extends Component> component: componentTypes){
      if(this.componentTypes.contains(component)){
        return true;
      }
    }
    return false;
  }
  public boolean containNone(ArrayList<Class<? extends Component>> componentTypes){
    if(componentTypes.isEmpty()) return true;
    for(Class<? extends Component> component: componentTypes){
      if(this.componentTypes.contains(component)){
        return false;
      }
    }
    return true;
  }
  public ArrayList<Class<? extends Component>> getComponentTypes(){
    ArrayList<Class<? extends Component>> allComponentTypes = new ArrayList<>();
    this.componentTypes.forEach(ct->{
      allComponentTypes.add(ct);
    });
    return allComponentTypes;
  }
  public ArrayList<Class<? extends Component>> getComponentTypesExclude(Class<? extends Component> componentType){
    ArrayList<Class<? extends Component>> allComponentTypes = this.componentTypes.stream().filter(ct->{
      return !ct.equals(componentType);
    }).collect(Collectors.toCollection(ArrayList::new));
    
    return allComponentTypes;
  }
  @Override
  public int hashCode(){
    return this.componentTypes.hashCode();
  }
  @Override
  public boolean equals(Object obj){
    if(this == obj) return true;
    if(obj == null || obj.getClass() != this.getClass())return false;
    EntityArchetype entityArchetype = (EntityArchetype)obj;
    return this.hashCode() == entityArchetype.hashCode();
  }
}
