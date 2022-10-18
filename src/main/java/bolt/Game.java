package bolt;
import javax.swing.JPanel;
import java.awt.*;
public class Game extends JPanel implements Runnable
{
  private final int PWIDTH =256;
  private final int PHEIGHT=240;
  private Thread animator;
  private volatile boolean running = false;
  private long period = 1000 / 60;
  private int NO_DELAY_PER_YIELD = 10;

  public Game() {
    setBackground(Color.black);
    setFocusable(true);
    setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

  }

  public void  addNotify(){
    super.addNotify();
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
    long beforeTime, timeDiff, sleepTime, afterTime;
    long overSleepTime = 0l;
    int noDelays = 0;
    beforeTime = System.currentTimeMillis();
    running = true;;
    while (running) {
      afterTime = System.currentTimeMillis();
      timeDiff = afterTime - beforeTime;
      sleepTime = period - timeDiff - overSleepTime;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
        }
        overSleepTime = (System.currentTimeMillis() - afterTime) - sleepTime;
      }
      else {
        overSleepTime = 0;
        if (++noDelays >= NO_DELAY_PER_YIELD) {
          Thread.yield();
          noDelays = 0;
        }
      }
      beforeTime = System.currentTimeMillis();
    }
  }

}
