package com.rdebernard.phanes.components;

public class Collider implements Component{
  public int width;
  public int height;
  public Collider(int width,int height){
    this.height = height;
    this.width = width;
  }
}
