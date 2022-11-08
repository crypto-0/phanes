package com.rdebernard.phanes.components;
import com.rdebernard.phanes.Vec3d;

public class Transform implements Component{
  public Vec3d position ;
  public Vec3d rotation ;
  public Vec3d scale ;
  public Vec3d localPosition ;
  public Vec3d localRotation ;
  public Vec3d localScale ;
  public boolean isDirty;
  public Transform(){
    position =new Vec3d();
    rotation = new Vec3d();
    scale = new Vec3d();
    scale.x = 1;
    scale.y = 1;
    localPosition=new Vec3d();
    localRotation = new Vec3d();
    localScale = new Vec3d();
    localScale.x = 1;
    localScale.y = 1;
    isDirty = true;

  }
}
