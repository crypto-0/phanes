package com.rdebernard.phanes.components;

import java.util.UUID;

public class Parent implements Component{
  public UUID parent;
  public Parent(){
    parent = null;
  }
  public Parent(UUID parent){
    this.parent = parent;
  }
}
