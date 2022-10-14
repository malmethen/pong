import java.awt.*;
import java.awt.event.*;

public class Paddle {

    // instance of the Main class
    Main pong;

    // paddles width and hight
    static final int PADDLE_W = 40, PADDLE_H = 100;

    // player number
    int playerNum;

    // location and velocity on the y-axix (paddles only move on y-axis)
    int x, y, ydx;
    int moveSpeed = 15;
    
    public Paddle(Main pong, int x, int y, int playerNum) {
        this.pong = pong;
        this.x = x;
        this.y = y;
        this.playerNum = playerNum;
    }

    public void paint(Graphics g) {
        if (playerNum == 1) 
            g.setColor(Main.color1);
        else
            g.setColor(Main.color2);
        g.fillRect(x, y, PADDLE_W, PADDLE_H);
    }

    // below are methods to update the position of paddles depending on input from keyboard
    public void pressed(int key) {
        if (playerNum == 1) {
            if (key == KeyEvent.VK_UP) {
                yDir(-moveSpeed);
                move();
            }
            if (key == KeyEvent.VK_DOWN) {
                yDir(moveSpeed);
                move();
            }
        }
        else {
            if (key == KeyEvent.VK_W) {
                yDir(-moveSpeed);
                move();
            }
            if (key == KeyEvent.VK_S) {
                yDir(moveSpeed);
                move();
            }
        }
    }

    public void released(int key) {
        if (playerNum == 1) {
            yDir(0);
            move();
        }
        else {
            yDir(0);
            move();
        }
    }
    
    public void yDir(int yd) {
        // set velocity to - or + with constant speed
        ydx = yd;
    }
    
    public void move() {
        // add (or subtract) the position to the y location to update
        y = y + ydx;
    }

    // stops paddle at window edges
    public void collisions() {
        if (y <= 0) 
            y = 0;
        if (y >= (Main.WINDOW_H - PADDLE_H))
            y = Main.WINDOW_H - PADDLE_H;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, PADDLE_W, PADDLE_H);
    }
}
