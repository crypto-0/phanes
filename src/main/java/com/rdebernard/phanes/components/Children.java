package com.rdebernard.phanes.components;

import java.util.HashSet;
import java.util.UUID;

public class Children implements Component{
  public HashSet<UUID> children;
  public Children(){
    children = new HashSet<>();
  }
}
