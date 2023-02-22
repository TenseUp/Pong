import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Ball extends Rectangle {
    int xS, yS;
    int speed = 4;

    Random random;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randX = random.nextInt(2);
        if (randX == 0)
            randX--;
        setXSpeed(randX * speed);
        int randY = random.nextInt(2);
        if (randY == 0)
            randY--;
        setYSpeed(randY * speed);

    }

    public void setXSpeed(int s) {
        xS = s;
    }

    public void setYSpeed(int s) {
        yS = s;
    }

    public void go(int first) {
        if (first <= 50) {
            x = 475;
        } else {
            x += xS;
            y += yS;
        }

    }

    public void colour(Graphics graphic) {
        graphic.setColor(Color.white);
        graphic.fillOval(x, y, height, width);
    }
}
