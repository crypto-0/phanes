package bolt.sytems;
import java.util.Map;
import java.util.UUID;
import bolt.World;
import bolt.components.CameraFocus;
import bolt.components.Transform;
import bolt.*;

public class CameraFollowSystem extends System{

  public CameraFollowSystem(World world){
    super(world);
  }
	@Override
	public void update(float dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(UUID entityId: entities.keySet()){
      CameraFocus cameraFocus = world.getComponent(entityId, CameraFocus.class);
      if(cameraFocus !=null){
        Transform transform = world.getComponent(entityId, Transform.class);
        if(transform !=null){
          world.camera.transform.position.x = transform.position.x - cameraFocus.offset;
        }
      }
    }
	}
}
