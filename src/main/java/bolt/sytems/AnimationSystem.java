package bolt.sytems;
import java.util.Map;
import java.util.UUID;
import bolt.components.Animation;
import bolt.components.Sprite;
import bolt.Entity;
import bolt.World;

public class AnimationSystem extends System{

  public AnimationSystem(World world){
    super(world);
  }
	@Override
	public void update(long dt) {
    Map<UUID,Entity> entities = world.getAllEntity();
    for(Entity entity: entities.values()){
      Animation animation = world.getComponent(entity.id,Animation.class);
      Sprite sprite = world.getComponent(entity.id,Sprite.class);
      if(animation != null && sprite != null){
        if(animation.sprites == null) continue;
          if(animation.currentFrame == animation.frames){
            animation.finished = true;
            if(animation.loop){
              animation.currentFrame = animation.currentFrame % animation.frames;
            }
            else{
              animation.currentFrame--;
            }
          }
          sprite.sprite = animation.sprites[animation.currentFrame];
          //sprite.sprite = animation.sprites[2];
          if(animation.timeElapse >= animation.interval){
            animation.currentFrame++;
            animation.timeElapse =0l;
          }
          animation.timeElapse += dt;
        }
    }
	}
}
