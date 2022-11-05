package com.rdebernard.theses.components;
import com.rdebernard.theses.Vec2d;

public class RigidBody implements Component{
  public Vec2d velocity = new Vec2d();
  public Vec2d force = new Vec2d();
  public float mass =1;
  public float gravity = 9.8f;

}
