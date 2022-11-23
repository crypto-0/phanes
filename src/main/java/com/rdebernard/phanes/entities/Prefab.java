package com.rdebernard.phanes.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Prefab {
  private HashMap<Class<? extends Component>,Component> components;

  public Prefab(){
    components = new HashMap<>();
  }
  public Prefab(Component... components){
    for(Component component: components){
      if(component == null)continue;
      this.components.put(component.getClass(),component);
    }
  }
  public Prefab(ArrayList<Component> components){
    for(Component component: components){
      if(component == null)continue;
      this.components.put(component.getClass(),component);
    }
  }

  public void addComponent(Component component){
    this.components.put(component.getClass(),component);
  }

  public void removeComponent(Class<? extends Component> componentType){
    this.components.remove(componentType);
  }

  public ArrayList<Component> getComponents(){
    return new ArrayList<>(this.components.values());
  }
}
