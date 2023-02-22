import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Panel extends JPanel implements Runnable {
    /* Racket */
    static final int racketWidth = 30;
    static final int racketHeight = 80;
    Racket player1;
    Racket player2;
    /* GUI */
    static final int width = 1000;
    static final int height = 555;
    static final Dimension size = new Dimension(width, height);
    boolean winner = false;
    Image image;
    Graphics graphics;
    /* Ball */
    static final int ballSize = 50;
    Ball ball;
    Random random = new Random();
    Win win = new Win(width, height);
    ScoreBoard scoreB;
    Thread gameT;
    int first = 0;

    Panel() {
        newRacket();
        createBall();
        scoreB = new ScoreBoard(width, height);
        /* Checks if keys have been pressed */
        this.setFocusable(true);
        this.addKeyListener(new Click());
        this.setPreferredSize(size);
        gameT = new Thread(this);
        gameT.start();
    }

    public class Click extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }

    public void createBall() {
        first = 0;
        random = new Random();
        ball = new Ball(475, random.nextInt(100, 300), ballSize, ballSize);
    }

    public void newRacket() {
        player1 = new Racket(0, (height / 2) - (racketHeight / 2), racketWidth, racketHeight, 1);
        player2 = new Racket(width - racketWidth, (height / 2) - (racketHeight / 2), racketWidth, racketHeight, 2);

    }

    public void paint(Graphics graphic) {
        if (winner == true) {
            if (scoreB.player1 == 5)
                Win.win(graphic, 1);
            else
                Win.win(graphic, 2);
        } else {
            image = createImage(getWidth(), getHeight());
            graphics = image.getGraphics();
            draw(graphics);
            graphic.drawImage(image, 0, 0, this);
        }

    }

    public void draw(Graphics graphic) {
        player1.colour(graphic);
        player2.colour(graphic);
        ball.colour(graphic);
        scoreB.colour(graphic);
        Toolkit.getDefaultToolkit().sync();
    }

    public void go() {
        ball.go(first);
        player1.go();
        player2.go();
        first++;
    }

    public boolean checkWinner() {
        if (scoreB.player1 == 5 || scoreB.player2 == 5) {
            repaint();
            return true;
        }
        return false;
    }

    public void hit() {

        if (ball.intersects(player1)) {
            ball.xS = Math.abs(ball.xS);
            ball.xS++;
            if (ball.yS > 0)
                ball.yS++;
            else
                ball.yS--;
            ball.setXSpeed(ball.xS);
            ball.setYSpeed(ball.yS);
        }

        // prevents racket from going off screen
        if (player2.y >= (height - racketHeight))
            player2.y = height - racketHeight;
        if (player1.y >= (height - racketHeight))
            player1.y = height - racketHeight;
        if (player2.y <= 0)
            player2.y = 0;
        if (player1.y <= 0)
            player1.y = 0;

        // scoring points
        if (ball.x >= 950) {
            scoreB.player1++;
            newRacket();
            createBall();
        }
        if (ball.x <= 0) {
            scoreB.player2++;
            newRacket();
            createBall();
        }

        if (ball.intersects(player2)) {
            ball.xS = Math.abs(ball.xS);
            ball.xS++;
            if (ball.yS > 0)
                ball.yS++;
            else
                ball.yS--;
            ball.setXSpeed(-ball.xS);
            ball.setYSpeed(ball.yS);
        }
        if (ball.y >= 505) {
            ball.setYSpeed(-ball.yS);
        }
        if (ball.y <= 0) {
            ball.setYSpeed(-ball.yS);
        }

    }

    public void run() { // copy and pasted from stack overflow
                        // https://stackoverflow.com/questions/26838286/delta-time-getting-60-updates-a-second-in-java
        // Get the system time
        long lastTime = System.nanoTime();
        // Specify how many seconds there are in a minute as a double
        // store as a double cause 60 sec in nanosec is big and store as final so it
        // can't be changed
        final double ticks = 60D;
        // Set definition of how many ticks per 1000000000 ns or 1 sec
        double ns = 1000000000 / ticks;
        double delta = 0;

        while (!winner) {
            // Update the time
            long now = System.nanoTime();
            // calculate change in time since last known time
            delta += (now - lastTime) / ns;
            // update last known time
            lastTime = now;
            // continue while delta is less than or equal to 1
            if (delta >= 1) {
                // Go through one tick
                go();
                hit();
                repaint();
                winner = checkWinner();
                // decrement delta

                delta--;
            }
        }
    }

}
