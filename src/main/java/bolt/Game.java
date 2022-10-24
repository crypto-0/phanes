package bolt;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import bolt.components.*;
import bolt.sytems.*;
import bolt.EntityState;
public class Game extends JPanel implements Runnable
{
  private World world;
  private final int PWIDTH;
  private final int PHEIGHT;
  private Thread animator;
  private volatile boolean running = false;
  private long period = 1000 / 60;
  private int NO_DELAY_PER_YIELD = 10;

  public Game(int  PWIDTH,int PHEIGHT) {
    this.PWIDTH = PWIDTH;
    this.PHEIGHT = PHEIGHT;
    setBackground(Color.white);
    setFocusable(true);
    setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
    world = new World();
  }

  public void init(){
    world.registerSystem(InputSystem.class);
    world.registerSystem(PlayerMovementSystem.class);
    world.registerSystem(RunStateSystem.class);
    world.registerSystem(IdleStateSystem.class);
    world.registerSystem(CollisionDetectionSystem.class);
    world.registerSystem(CollisionResponseSystem.class);
    world.registerSystem(PhysicSystem.class);
    world.registerSystem(AnimationSystem.class);
    world.registerSystem(RenderSystem.class);
    world.registerComponent(PlayerMovement.class);
    world.registerComponent(Sprite.class);
    world.registerComponent(Transform.class);
    world.registerComponent(Animation.class);
    world.registerComponent(RigidBody.class);
    world.registerComponent(Input.class);
    world.registerComponent(IdleState.class);
    world.registerComponent(RunState.class);
    world.registerComponent(Collision.class);
    world.registerComponent(Collider.class);;
    Entity entity = world.createEntity();
    Entity entity2 = world.createEntity();
    Transform transform = new Transform();
    Transform transform2 = new Transform();
    Collider collider = new Collider(200, 200);
    Collider collider2 = new Collider(200, 200);
    transform.scale.x = .3f;
    transform.scale.y = .3f;
    transform2.scale.x = .3f;
    transform2.scale.y = .3f;
    transform.position.x = 100;
    transform.position.y = 100;
    transform2.position.x = 400;
    transform2.position.y = 100;
    world.addComponent(entity.id, transform);
    world.addComponent(entity.id, new RigidBody());
    world.addComponent(entity.id, new Input());
    world.addComponent(entity.id, new PlayerMovement(200,200));
    world.addComponent(entity.id, collider);
    //second Entity
    world.addComponent(entity2.id, transform2);
    world.addComponent(entity2.id, new RigidBody());
    world.addComponent(entity2.id, collider2);
    InputSystem  inputSystem = world.getSystem(InputSystem.class);
    this.addKeyListener(inputSystem);
    long interval = 1000/24;
    java.lang.System.out.println("Interval: " + interval);
    int frames = 9;
    int xOffset = 575;
    Animation animationRun = new Animation("run.png",interval, frames, xOffset);
    Animation animationIdle = new Animation("idle.png",interval, 7, xOffset);
    world.addComponent(entity.id,animationRun);
    Sprite sprite = new Sprite(null);
    Sprite sprite2 = new Sprite(animationIdle.sprites[0]);
    world.addComponent(entity.id, sprite);
    world.addComponent(entity2.id, sprite2);
    EntityStateMachine entityStateMachine = world.createStateMachine(entity.id);
    EntityState  entityStateRun= entityStateMachine.createEntityState("run");
    EntityState  entityStateIdle= entityStateMachine.createEntityState("idle");
    entityStateRun.addComponent(animationRun);
    entityStateRun.addComponent(new RunState());
    entityStateIdle.addComponent(animationIdle);
    entityStateIdle.addComponent(new IdleState());
    world.changeEntityState(entity.id, "idle");
    start();
  }
  public void  addNotify(){
    super.addNotify();
    //start();
  }

  public void start() {
    if (animator == null || !running) {
      animator = new Thread(this);
      animator.start();
    }
  }

  @Override
  public void run() {
    long beforeTime, timeDiff=0, sleepTime, afterTime;
    long overSleepTime = 0l;
    long dt =0;
    int noDelays = 0;
    beforeTime = java.lang.System.currentTimeMillis();
    running = true;;
    while (running) {
      world.update(dt);
      paintScreen();
      afterTime = java.lang.System.currentTimeMillis();
      timeDiff = afterTime - beforeTime;
      sleepTime = period - timeDiff - overSleepTime;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
        }
        overSleepTime = (java.lang.System.currentTimeMillis() - afterTime) - sleepTime;
      }
      else {
        overSleepTime = 0;
        if (++noDelays >= NO_DELAY_PER_YIELD) {
          Thread.yield();
          noDelays = 0;
        }
      }
      dt = java.lang.System.currentTimeMillis() - beforeTime;
      beforeTime = java.lang.System.currentTimeMillis();
    }
  }
   public void paintScreen() {
    Graphics g;
    RenderSystem system = world.getSystem(RenderSystem.class);
    try{
      g = this.getGraphics();
      BufferedImage bufferedImage = system.getBufferImage();
      if(g == null){
      }
      if((g != null) && (bufferedImage !=null)){
        g.drawImage(bufferedImage, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
      }
    }
    catch (Exception e) {
      java.lang.System.out.println("Graphics context error: " + e);
    }
  }

}
