import java.awt.*;
import java.awt.event.*;

public class Racket extends Rectangle {
    int id, yS;
    int speed = 10;

    Racket(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setSpeed(speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setSpeed(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSpeed(speed);
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setSpeed(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setSpeed(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setSpeed(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSpeed(0);
                }
                break;
        }
    }

    public void setSpeed(int Speed) {
        yS = Speed;
    }

    public void go() {
        y += yS;
    }

    public void colour(Graphics graphic) {
        if (id == 1)
            graphic.setColor(Color.cyan);
        else
            graphic.setColor(Color.pink);
        graphic.fillRect(x, y, width, height);
        graphic.setColor(Color.white);
        graphic.draw3DRect(x, y, width, height, false);
    }
}
