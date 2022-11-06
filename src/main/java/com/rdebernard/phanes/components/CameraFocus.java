package com.rdebernard.phanes.components;

import java.util.UUID;

public class CameraFocus implements Component{
  public int offset;
  public CameraFocus(){};
  public CameraFocus(int offset){
    this.offset = offset;
  };
}
