package com.rdebernard.phanes.systems;
import com.rdebernard.phanes.Entity;
import com.rdebernard.phanes.World;
import com.rdebernard.phanes.components.Transform;
import com.rdebernard.phanes.components.UIimage;
import com.rdebernard.phanes.Display;


import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.awt.Color;
import java.awt.Graphics2D;

public class UIrenderSystem extends System{
  public UIrenderSystem(World world){
    super(world);
  }
	@Override
	public void update(float dt) {
    Comparator<Entity> compare = (e1,e2)-> {
      UIimage uiImage = world.getComponent(e1.id,UIimage.class);
      UIimage uiImage2 = world.getComponent(e1.id,UIimage.class);
      if(uiImage.layerOrder > uiImage2.layerOrder) return 1;
      return -1;
    };
    PriorityQueue<Entity> entitiesToDraw = new PriorityQueue<Entity>(compare);

    Map<UUID,Entity> entities = world.getAllEntity();
    if(Display.buffer == null)return;
    Graphics2D g2d = Display.buffer.createGraphics(); 
    g2d.setColor(Color.white);
    //g2d.fillRect(0, 0, Display.buffer.getWidth(),Display.buffer.getHeight());
    for(Entity entity: entities.values()){
      UIimage uiImage = world.getComponent(entity.id,UIimage.class);
      if(uiImage == null)continue;
      Transform transform = world.getComponent(entity.id,Transform.class);
      if(transform ==null)continue;
      entitiesToDraw.add(entity);
    }
    while (!entitiesToDraw.isEmpty()) {
      Entity entity = entitiesToDraw.poll();
      UIimage uiImage = world.getComponent(entity.id,UIimage.class);
      Transform transform = world.getComponent(entity.id,Transform.class);
      int x = (int)transform.position.x;
      int y = (int)transform.position.y;
      float scalex = transform.scale.x;
      float scaley = transform.scale.y;
      int uiImageW= (int)(uiImage.width * scalex);
      int uiImageH= (int)(uiImage.height * scaley);
      if(uiImage.img !=null){
        if(scalex < 0){
          g2d.drawImage(uiImage.img,x + (uiImageW * -1),y,uiImageW,uiImageH,null);
        }
        else{
          g2d.drawImage(uiImage.img,x,y,uiImageW,uiImageH,null);
        }
      }
    }
	}
}
