package bolt;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import bolt.sytems.*;
import bolt.scenes.*;
public class Game extends JPanel implements Runnable
{
  private World world;
  private Thread animator;
  private volatile boolean running = false;
  private int TARGET_FPS = 60;
  private int NO_DELAY_PER_YIELD = 10;
  private long period = 1000 / TARGET_FPS;

  public Game(int  PWIDTH,int PHEIGHT) {
    setBackground(Color.white);
    setFocusable(true);
    setPreferredSize(new Dimension(PWIDTH, PHEIGHT));
    world = new World();
    Display.buffer = new BufferedImage(PWIDTH,PHEIGHT,BufferedImage.TYPE_INT_RGB);
  }

  public void init(){
    Scene scene= new Scene1(world);
    world.addScene("mainScene",scene);
    world.loadScene("mainScene");
    this.addKeyListener(world.getSystem(InputSystem.class));
    start();
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
      world.update(dt * .001f * TARGET_FPS);
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
    try{
      g = this.getGraphics();
      BufferedImage bufferedImage = Display.buffer;
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
