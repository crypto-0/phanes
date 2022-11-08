package com.rdebernard.phanes.components;
import com.rdebernard.phanes.Vec3d;

public class RigidBody implements Component{
  public Vec3d velocity = new Vec3d();
  public Vec3d force = new Vec3d();
  public float mass =1;
  public float gravity = 9.8f;

}
