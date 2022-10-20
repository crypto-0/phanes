package bolt.sytems;
import bolt.Entity;
import bolt.World;
import bolt.components.Sprite;
import bolt.components.Transform;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.UUID;
import java.awt.Color;
import java.awt.Graphics2D;

public class RenderSystem extends System{
  private BufferedImage bufferedImage;
  public RenderSystem(World world){
    super(world);
    bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
  }
  public void setBufferImage(BufferedImage bufferedImage){
    this.bufferedImage = bufferedImage;
  }
  public BufferedImage getBufferImage(){
    return bufferedImage;
  }
	@Override
	public void update(long dt) {
    int spriteW= 575;
    int spriteH= 523;
    Map<UUID,Entity> entities = world.getAllEntity();
    Graphics2D g2d = bufferedImage.createGraphics(); 
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, 800, 800);
    for(Entity entity: entities.values()){
      Sprite sprite = world.getComponent(entity.id,Sprite.class);
      Transform transform = world.getComponent(entity.id,Transform.class);
      int dstx = (int)transform.position.x;
      int dsty = (int)transform.position.y;
      //int srcx = spriteW * sprite.frame;
      //srcx = 1 * spriteW;
      int srcy = spriteH * 3;
      //srcy =7 * spriteH;
      float scalex = transform.scale.x;
      float scaley = transform.scale.y;
      //if(sprite != null && transform != null) g2d.drawImage(sprite.sprite,dstx,dsty,dstx +(int)(spriteW * scalex),dsty + (int)(spriteH * scaley),srcx,srcy, srcx + spriteW,srcy +spriteH,null);
      if(sprite.sprite !=null) g2d.drawImage(sprite.sprite,dstx,dsty,null);
    }
	}
}
