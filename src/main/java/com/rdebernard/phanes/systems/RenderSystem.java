package com.rdebernard.phanes.systems;
import com.rdebernard.phanes.Entity;
import com.rdebernard.phanes.World;
import com.rdebernard.phanes.components.Collider;
import com.rdebernard.phanes.components.SpriteRenderer;
import com.rdebernard.phanes.components.Transform;
import com.rdebernard.phanes.Display;


import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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
	public void update(float dt) {
    Comparator<Entity> compare = (e1,e2)-> {
      SpriteRenderer spriteRenderer = world.getComponent(e1.id,SpriteRenderer.class);
      SpriteRenderer spriteRenderer2 = world.getComponent(e2.id,SpriteRenderer.class);
      if(spriteRenderer.layerOrder > spriteRenderer2.layerOrder) return 1;
      return -1;
    };
    PriorityQueue<Entity> entitiesToDraw = new PriorityQueue<Entity>(compare);

    Map<UUID,Entity> entities = world.getAllEntity();
    if(Display.buffer == null)return;
    Graphics2D g2d = Display.buffer.createGraphics(); 
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, Display.buffer.getWidth(),Display.buffer.getHeight());
    for(Entity entity: entities.values()){
      SpriteRenderer spriteRenderer = world.getComponent(entity.id,SpriteRenderer.class);
      if(spriteRenderer == null)continue;
      Transform transform = world.getComponent(entity.id,Transform.class);
      if(transform ==null)continue;
      entitiesToDraw.add(entity);
    }
    while (!entitiesToDraw.isEmpty()) {
      Entity entity = entitiesToDraw.poll();
      SpriteRenderer spriteRenderer = world.getComponent(entity.id,SpriteRenderer.class);
      Transform transform = world.getComponent(entity.id,Transform.class);
      int x = (int)(transform.position.x - world.camera.transform.position.x);
      int y = (int)(transform.position.y - world.camera.transform.position.y);
      float scalex = transform.scale.x;
      float scaley = transform.scale.y;
      int spriteW= (int)(spriteRenderer.sprite.width * scalex);
      int spriteH= (int)(spriteRenderer.sprite.height * scaley);
      if(spriteRenderer.sprite !=null){
        //Collider collider = world.getComponent(entity.id,Collider.class);
        //if(collider !=null){
        //g2d.setColor(Color.BLUE);
        //g2d.drawRect(x ,y,collider.width,collider.height);
        //}
        if(scalex < 0){
          AffineTransform at = AffineTransform.getTranslateInstance(x + (spriteW * -1),y);
          at.scale(scalex, scaley);
          at.translate(spriteW, 0);
          at.rotate(Math.toRadians(transform.rotation.z),spriteW/2,spriteH/2);
          //g2d.drawImage(spriteRenderer.sprite.img,x + (spriteW * -1),y,spriteW,spriteH,null);
          g2d.drawImage(spriteRenderer.sprite.img,at,null);
        }
        else{
          AffineTransform at = AffineTransform.getTranslateInstance(x,y);
          at.scale(scalex, scaley);
          at.rotate(Math.toRadians(transform.rotation.z),spriteW/2,spriteH/2);
         // g2d.rotate(Math.toRadians(transform.rotation.z),spriteW/2,spriteH/2);
          //g2d.drawImage(spriteRenderer.sprite.img,x,y,spriteW,spriteH,null);
          g2d.drawImage(spriteRenderer.sprite.img,at,null);
        }
      }
    }
    g2d.dispose();
	}
}
