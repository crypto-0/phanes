package bolt.scenes;
import bolt.*;
import bolt.sytems.*;
import bolt.components.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    //register systems
    world.addSystem(new InputSystem(world));
    world.addSystem(new IdleStateSystem(world));
    world.addSystem(new RunStateSystem(world));
    world.addSystem(new AttackStateSystem(world));
    world.addSystem(new CollisionDetectionSystem(world));
    world.addSystem(new CollisionResponseSystem(world));
    world.addSystem(new GroundedSystem(world));
    world.addSystem(new PhysicSystem(world));
    world.addSystem(new CameraFollowSystem(world));
    world.addSystem(new ParallaxSystem(world));
    world.addSystem(new AnimationSystem(world));
    world.addSystem(new RenderSystem(world));
    //register components
    world.registerComponent(SpriteRenderer.class);
    world.registerComponent(Animation.class);
    world.registerComponent(Transform.class);
    world.registerComponent(RigidBody.class);
    world.registerComponent(IdleState.class);
    world.registerComponent(RunState.class);
    world.registerComponent(AttackState.class);
    world.registerComponent(Input.class);
    world.registerComponent(PlayerMovement.class);
    world.registerComponent(Collider.class);
    world.registerComponent(Collision.class);
    world.registerComponent(Grounded.class);
    world.registerComponent(Ground.class);
    world.registerComponent(CameraFocus.class);
    world.registerComponent(Parallax.class);
    //player
    Entity player = world.createEntity();
    SpriteRenderer spriteRenderer = new SpriteRenderer(assetManager.getSpriteSheet("idle").sprites.get(0), 6);
    Collider collider = new Collider((int)(spriteRenderer.sprite.width *.35f),(int)(spriteRenderer.sprite.height * .35f));
    world.addComponent(player.id,spriteRenderer);
    world.addComponent(player.id, collider);
    world.addComponent(player.id, new Input());
    world.addComponent(player.id, new Grounded());
    world.addComponent(player.id, new CameraFocus(Display.buffer.getWidth() / 2));
    RigidBody rigidBody = new RigidBody();
    rigidBody.gravity = 3.8f;
    world.addComponent(player.id,rigidBody);
    Transform transform = new Transform();
    transform.position = new Vec2d(0,0);
    transform.scale = new Vec2d(.35f, .35f);
    world.addComponent(player.id, transform);
    //create player states
    EntityState runEntityState = new EntityState();
    runEntityState.addComponent(new Animation(assetManager.getSpriteSheet("run"),2));
    runEntityState.addComponent(new RunState());
    EntityState idleEntityState = new EntityState();
    idleEntityState.addComponent(new Animation(assetManager.getSpriteSheet("idle"),2));
    idleEntityState.addComponent(new IdleState());
    EntityState attackEntityState = new EntityState();
    attackEntityState.addComponent(new Animation(assetManager.getSpriteSheet("roll"),2));
    attackEntityState.addComponent(new AttackState());
    EntityStateMachine entityStateMachine = world.createStateMachine(player.id);
    entityStateMachine.addEntityState("run", runEntityState);
    entityStateMachine.addEntityState("idle", idleEntityState);
    entityStateMachine.addEntityState("attack", attackEntityState);
    world.changeEntityState(player.id,"idle");
    //create ground entity
    for(int a=-2; a< 3; a++){
    Entity ground = world.createEntity();
    spriteRenderer = new SpriteRenderer(assetManager.getSprite("layer-5"),5);
    Transform transform2 = new Transform();
    //transform2.scale = new Vec2d(.35f, 1f);
    transform2.scale = new Vec2d(1f, 1f);
    transform2.position.x = a * (int)(spriteRenderer.sprite.width * transform2.scale.x);
    transform2.position.y = Display.buffer.getHeight() - (spriteRenderer.sprite.height * transform2.scale.y);
    Collider collider2 = new Collider((int)(spriteRenderer.sprite.width *transform2.scale.x),(int)(spriteRenderer.sprite.height * transform2.scale.y));
    world.addComponent(ground.id, transform2);
    world.addComponent(ground.id,spriteRenderer);
    world.addComponent(ground.id,collider2);
    world.addComponent(ground.id,new Ground());
    world.addComponent(ground.id, new Parallax(0.5f));
    }
    //create rest of background
    for(int b=1; b <5; b++){
      for(int a=-2; a< 3; a++){
      Entity ground = world.createEntity();
      spriteRenderer = new SpriteRenderer(assetManager.getSprite("layer-" + b),b);
      Transform transform2 = new Transform();
      //transform2.scale = new Vec2d(.35f, 1f);
      transform2.scale = new Vec2d(1f, 1f);
      transform2.position.x = a * (int)(spriteRenderer.sprite.width * transform2.scale.x);
      transform2.position.y = Display.buffer.getHeight() - (spriteRenderer.sprite.height * transform2.scale.y);
      world.addComponent(ground.id, transform2);
      world.addComponent(ground.id,spriteRenderer);
      world.addComponent(ground.id,new Ground());
      world.addComponent(ground.id, new Parallax(0.5f));
      }
    }
	}

	@Override
	public void onDeactivate() {
    assetManager.clearAssets();
	}

  private void loadResources(){
    Map<String,Integer> spriteSheetResources = new HashMap<>();
    spriteSheetResources.put("run",9);
    spriteSheetResources.put("idle",7);
    spriteSheetResources.put("roll",7);
    for(Map.Entry<String, Integer> resource: spriteSheetResources.entrySet()){
      String resourceFullName = "player/" + resource.getKey() + ".png";
      BufferedImage spriteSheetImg = getImage(resourceFullName);
      if(spriteSheetImg !=null){
        SpriteSheet spriteSheet = new SpriteSheet(spriteSheetImg,resource.getValue(),575);
        assetManager.addSpriteSheet(resource.getKey(),spriteSheet);
      }
    }
    ArrayList<String> spriteResources = new ArrayList<>();
    spriteResources.add(0,"layer-5");
    spriteResources.add(1,"layer-4");
    spriteResources.add(2,"layer-3");
    spriteResources.add(3,"layer-2");
    spriteResources.add(4,"layer-1");
    for(String resource: spriteResources){
      String resourceFullName = "background/city/" + resource+ ".png";
      BufferedImage groundBackground = getImage(resourceFullName);
    if(groundBackground !=null){
      Sprite sprite = new Sprite(groundBackground);
      assetManager.addSprite(resource, sprite);
    }
    }
  }
  private BufferedImage getImage(String resourceName){
    BufferedImage img;
    try{
      String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
      img = ImageIO.read(new File(path)) ;
    }
    catch(IOException e){
      java.lang.System.out.println("Coult not load image: " + resourceName);
      img = null;
    }
    return img;
  }


}
