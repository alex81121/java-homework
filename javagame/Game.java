package javagame;
import javax.swing.JFrame;


public class Game extends JFrame{
    public Game(){
        setTitle("Fight on two");
        setSize(800, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
    }
}

