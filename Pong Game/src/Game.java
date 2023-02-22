import java.awt.Color;

import javax.swing.*;

public class Game extends JFrame {

    Panel panel;

    Game() {
        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setBackground(Color.darkGray);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
