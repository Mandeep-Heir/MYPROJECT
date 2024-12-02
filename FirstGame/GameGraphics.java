package FirstGame;

import javax.swing.*;
import java.awt.*;

public class GameGraphics extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {//
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(100, 100, 50, 50);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game with Graphics");
        GameGraphics game = new GameGraphics();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
