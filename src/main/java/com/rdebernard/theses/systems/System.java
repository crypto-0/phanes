package com.rdebernard.theses.systems;
import com.rdebernard.theses.World;

public abstract class  System{
  protected World world;
  public System(World world){
    this.world = world;
  }
  public abstract void update(float dt);
}
