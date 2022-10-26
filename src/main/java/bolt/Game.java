package bolt;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import bolt.components.*;
import bolt.sytems.*;
import bolt.sytems.System;
import bolt.EntityState;
import bolt.scenes.*;
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
    Scene scene= new Scene1(world);
    world.addScene("test",scene);
    world.loadScene("test");
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
      //BufferedImage bufferedImage =null;
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
