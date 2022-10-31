package bolt.sytems;
import java.util.Map;
import java.util.UUID;
import bolt.*;
import bolt.components.*;

public class AttackStateSystem extends System{
  public AttackStateSystem(World world){
    super(world);
  }

	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      AttackState attackState = world.getComponent(entity.id,AttackState.class);
      if(attackState != null){
        Input input = world.getComponent(entity.id,Input.class);
        Transform transform = world.getComponent(entity.id,Transform.class);
        if(input != null){
          Grounded grounded = world.getComponent(entity.id, Grounded.class);
          if(input.vertical > 0){
            if(grounded !=null){
              RigidBody rigidBody = world.getComponent(entity.id, RigidBody.class);
              if(grounded.grounded) rigidBody.velocity.y = -50;
            }
          }
          else if(input.vertical < 0){
            world.changeEntityState(entity.id, "slam");
          }
          else if (grounded != null && grounded.grounded){
            world.changeEntityState(entity.id, "idle");
          }
        }
      }
    }
    }
  }
