package bolt.sytems;
import bolt.components.*;
import java.lang.Math;
import java.util.Map;
import java.util.UUID;

import bolt.*;

public class ParallaxSystem extends System{
  public ParallaxSystem(World world){
    super(world);
  }

	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(UUID entityId: entities.keySet()){
      Parallax parallax = world.getComponent(entityId, Parallax.class);
      if(parallax !=null){
        Transform transform = world.getComponent(entityId, Transform.class);
        SpriteRenderer spriteRenderer = world.getComponent(entityId, SpriteRenderer.class);
        if(transform !=null && spriteRenderer !=null){
          float deltax = world.camera.transform.position.x - parallax.lastCameraPosition.x ;
          float deltay = world.camera.transform.position.y - parallax.lastCameraPosition.y;
          parallax.lastCameraPosition.x = world.camera.transform.position.x;
          parallax.lastCameraPosition.y = world.camera.transform.position.y;
          float deltax2 =world.camera.transform.position.x - transform.position.x; 
          if(Math.abs(deltax2) >=((spriteRenderer.sprite.width * transform.scale.x))){
            transform.position.x = world.camera.transform.position.x + deltax2 - (deltax) - (deltax2 % spriteRenderer.sprite.width * transform.scale.x);
          }
          else{
            transform.position.x +=deltax * parallax.effectMultiplier;
            transform.position.y +=deltay * parallax.effectMultiplier;
          }
        }
      }
    }
		
	}
}
