package com.rdebernard.phanes.entities;
import java.util.UUID;

public class Entity {
  final public UUID id;
  Entity(){
    id = UUID.randomUUID();
  }
  @Override
  public int hashCode(){
    return id.hashCode();
  }
  @Override
  public boolean equals(Object obj){
    if(this == obj) return true;
    if(obj == null || obj.getClass() != this.getClass())return false;
    Entity entity = (Entity)obj;
    return this.hashCode() == entity.hashCode();
  }
}
