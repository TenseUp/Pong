import java.awt.*;

public class Win extends Rectangle {
    static int width, height;

    Win(int width, int height) {
        Win.width = width;
        Win.height = height;
    }

    public static void win(Graphics graphic, int id) {
        if (id == 1)
            graphic.setColor(Color.cyan);
        else
            graphic.setColor(Color.pink);
        graphic.fillRect(0, 0, width, height);
        graphic.setColor(Color.darkGray);
        graphic.setFont(new Font("Monospaced", Font.PLAIN, 50));
        graphic.drawString("Winner! Player " + id, (width / 2) - 200, height / 2);
    }
}