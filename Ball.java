import java.awt.*;
import java.util.*;

public class Ball {

    // instance of the Main class
    Main pong;

    // ball diameter + instatnce of class
    static final int BALL_D = 30;

    // location and velocity of x and y-axis
    int x = (Main.WINDOW_W / 2) - (BALL_D / 2);
    int y = (Main.WINDOW_H / 2) - (BALL_D / 2);
    int xdx, ydx;
    int moveSpeed = 3;

    // instance of random class (the ball moves randomly)
    Random random = new Random();

    public Ball(Main pong) {
        this.pong = pong;
        xDir(ranDir() * moveSpeed);
        yDir(ranDir() * moveSpeed);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, BALL_D, BALL_D);
    }
    
    public int ranDir() {
        int ranValue = random.nextInt(2);
        if (ranValue == 0)
            ranValue--;
        return ranValue;
    }

    public void xDir(int xd) {
        xdx = xd;
    }

    public void yDir(int yd) {
        ydx = yd;
    }

    public void move() {
        x = x + xdx;
        y = y + ydx;
    }

    // bounces ball of window edges
    public void collisions() {
        if (y <= 0)
            yDir(-ydx);
        if (y >= (Main.WINDOW_H - BALL_D))
            yDir(-ydx);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, BALL_D, BALL_D);
    }
}
