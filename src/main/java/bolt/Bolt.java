package bolt;
import javax.swing.JFrame;

public class Bolt{

  public static void main(String args[]){
    JFrame window = new JFrame();
    Game game = new Game(800, 800);
    game.init();
    window.add(game);
    window.pack();
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
