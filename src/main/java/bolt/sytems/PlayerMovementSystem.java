package bolt.sytems;
import java.util.UUID;
import java.util.Map;
import bolt.Entity;
import bolt.World;
import bolt.components.PlayerMovement;
import bolt.components.Input;
import bolt.components.RigidBody;

public class PlayerMovementSystem extends System{

  public PlayerMovementSystem(World world){
    super(world);
  }
	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      Input input = world.getComponent(entity.id,Input.class);
      PlayerMovement playerMovement = world.getComponent(entity.id,PlayerMovement.class);
      RigidBody rigidBody = world.getComponent(entity.id,RigidBody.class);
      if(input != null && playerMovement != null){
        if(input.A == 1) rigidBody.velocity.x = -playerMovement.velocity * ((float)dt/1000);
        else if(input.D == 1) rigidBody.velocity.x = playerMovement.velocity * ((float)dt/1000);
        else if(input.SPACE == 1) rigidBody.velocity.y = -playerMovement.velocity * ((float)dt/1000);
        else{ 
          rigidBody.velocity.x =0;
          rigidBody.velocity.y =0;
        }
      }
    }
		
	}
}
