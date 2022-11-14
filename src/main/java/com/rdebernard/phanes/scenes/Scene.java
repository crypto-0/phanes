package com.rdebernard.phanes.scenes;
import com.rdebernard.phanes.entities.World;

public abstract class Scene{
  protected World world;
  public Scene(World world){
    this.world = world;
  }
  public abstract void onDestroy();
  public abstract void onActivate();
  public abstract void onDeactivate();
  public void update(float dt){
    world.systemManager.update(dt);
  }
}
