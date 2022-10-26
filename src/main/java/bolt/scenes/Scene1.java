package bolt.scenes;
import bolt.*;
import bolt.sytems.*;
import bolt.components.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Scene1 extends Scene{

  AssetManager assetManager;
  public Scene1(World world){
    super(world);
    assetManager = new AssetManager();
  }
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onActivate() {
    loadResources();
    world.addSystem(new AnimationSystem(world));
    world.addSystem(new RenderSystem(world));
    world.addSystem(new PhysicSystem(world));
    world.addSystem(new IdleStateSystem(world));
    world.addSystem(new RunStateSystem(world));
    world.registerComponent(SpriteRenderer.class);
    world.registerComponent(Animation.class);
    world.registerComponent(Transform.class);
    world.registerComponent(RigidBody.class);
    world.registerComponent(IdleState.class);
    world.registerComponent(RunState.class);
    Entity player = world.createEntity();
    world.addComponent(player.id, new SpriteRenderer());
    world.addComponent(player.id, new Animation(assetManager.getSpriteSheet("run"),41));
    world.getComponent(player.id,Animation.class).loop = true;
    Transform transform = new Transform();
    transform.position.x = 200;
    world.addComponent(player.id, transform);
	}

	@Override
	public void onDeactivate() {
    assetManager.clearAssets();
	}

  private void loadResources(){
    String resourceBasePath = "player/";
    String resourceName = "run";
    BufferedImage runSpriteSheetImg = getImage(resourceBasePath + resourceName + ".png");
    if(runSpriteSheetImg !=null){
      SpriteSheet spriteSheet = new SpriteSheet(runSpriteSheetImg,9,575);
      assetManager.addSpriteSheet(resourceName,spriteSheet);
    }
    else{
    }

  }
  private BufferedImage getImage(String resourceName){
    BufferedImage img;
    try{
      String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
      img = ImageIO.read(new File(path)) ;
    }
    catch(IOException e){
      java.lang.System.out.println("Coult not load sprite: " + resourceName);
      img = null;
    }
    return img;
  }


}
