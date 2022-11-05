package com.rdebernard.theses.scenes;
import com.rdebernard.theses.World;

public abstract class Scene{
  protected World world;
  public Scene(World world){
    this.world = world;
  }
  public abstract void onDestroy();
  public abstract void onActivate();
  public abstract void onDeactivate();
}
