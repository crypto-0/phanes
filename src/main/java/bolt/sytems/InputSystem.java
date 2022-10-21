package bolt.sytems;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.UUID;
import bolt.Entity;

import bolt.World;
import bolt.components.Input;

public class InputSystem extends System implements KeyListener{
  public byte W ,A, S, D,SPACE;
  public InputSystem(World world){
    super(world);
    byte W,A,S,D,SPACE =0;
  }
	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      Input input = world.getComponent(entity.id,Input.class);
      if(input != null){
        input.A = A;
        input.D = D;
        input.SPACE = SPACE;
        input.S = S;
        input.W = W;
      }
    }
  }
  @Override
  public void keyPressed(KeyEvent key) {
    switch(key.getKeyCode()){
      case 32:
        this.SPACE = 1;
        break;
      case 87:
        this.W = 1;
        break;
      case 65:
        this.A = 1;
        break;
      case 83:
        this.S = 1;
        break;
      case 68:
        this.D = 1;
        break;
    }
    
  }
  @Override
  public void keyReleased(KeyEvent key) {
    switch(key.getKeyCode()){
      case 32:
        this.SPACE = 0;
        break;
      case 87:
        this.W = 0;
        break;
      case 65:
        this.A = 0;
        break;
      case 83:
        this.S = 0;
        break;
      case 68:
        this.D = 0;
        break;
    }
  }
  @Override
  public void keyTyped(KeyEvent key) {
  }
  }
    

