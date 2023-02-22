import java.awt.*;

public class ScoreBoard extends Rectangle {
    static int width, height;
    int player1, player2;

    ScoreBoard(int width, int height) {
        ScoreBoard.width = width;
        ScoreBoard.height = height;
    }

    public void colour(Graphics graphic) {
        graphic.setColor(Color.white);
        graphic.setFont(new Font("Monospaced", Font.PLAIN, 25));
        graphic.drawLine(width / 2, 0, width / 2, height);
        graphic.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (width / 2) + 60, 50);
        graphic.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (width / 2) - 85, 50);
    }
}
