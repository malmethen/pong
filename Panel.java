/*
this class implements Runnable, which allows to use threads through the method run()
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {
    
    // instance of the Main class
    Main pong;

    // instances of paddle and ball classes
    Paddle paddle1, paddle2;
    Ball ball;

    int player1Score = 0;
    int player2Score = 0;

    Thread thread;

    public Panel(Main pong) {

        this.pong = pong;

        // greeting at the top
        JLabel greeting = new JLabel("Welcome to Pong!");
        greeting.setBounds((Main.WINDOW_W / 2) - 70, 5, 150, 25);
        greeting.setForeground(Color.WHITE);
        greeting.setFont(new Font("Monospaced", Font.PLAIN, 15));
        add(greeting);

        // size of panel
        setPreferredSize(new Dimension(Main.WINDOW_W, Main.WINDOW_H));
        setFocusable(true); // this thing is so important omfg!!!!???

        // create paddles
        paddle1 = new Paddle(pong, (Main.WINDOW_W - 40), (Main.WINDOW_H / 2), 1);
        paddle2 = new Paddle(pong, 0, (Main.WINDOW_H / 2), 2);
        // create ball
        ball = new Ball(pong);

        addKeyListener(new ActionListener());

        thread = new Thread(this);
        thread.start();
    }
    
    // since we implemented Runnable, we need a run method for threads 
    // this is essentially a game loop
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                update();
                paddle1.collisions();
                paddle2.collisions();
                ball.collisions();
                collisions();
                repaint();
                delta--;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        paddle1.paint(g);
        paddle2.paint(g);
        ball.paint(g);
        setBackground(Color.DARK_GRAY);

        // score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas",Font.PLAIN, 60));
        g.drawString(String.valueOf(player1Score), (Main.WINDOW_W / 2) + 40, 90);
        g.drawString(String.valueOf(player2Score), (Main.WINDOW_W / 2) - 85, 90);
    }

    // for smoother movement
    public void update() { 
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    
    public void collisions() {
        // bounces ball off paddles
        if (ball.getBounds().intersects(paddle1.getBounds()))
            ball.xDir(-ball.xdx);
        if (ball.getBounds().intersects(paddle2.getBounds()))
            ball.xDir(-ball.xdx);

        // updates score and creates a new game
        if (ball.x >= (Main.WINDOW_W - Ball.BALL_D)) {
            player2Score++;
            // create paddles
            paddle1 = new Paddle(pong, (Main.WINDOW_W - 40), (Main.WINDOW_H / 2), 1);
            paddle2 = new Paddle(pong, 0, (Main.WINDOW_H / 2), 2);
            // create ball
            ball = new Ball(pong);
            System.out.println("Player2 scored " + player2Score);
        }
        if (ball.x <= 0) {
            player1Score++;
            // create paddles
            paddle1 = new Paddle(pong, (Main.WINDOW_W - 40), (Main.WINDOW_H / 2), 1);
            paddle2 = new Paddle(pong, 0, (Main.WINDOW_H / 2), 2);
            // create ball
            ball = new Ball(pong);
            System.out.println("Player1 scored " + player1Score);
        }
    }

    // doesn't work
    /*public void updateScore() {
        JLabel p1Score = new JLabel("");
        p1Score.setBounds((Main.WINDOW_W / 2) - 70, 10, 150, 25);
        p1Score.setText(String.valueOf(player1Score));
        add(p1Score);

        JLabel p2Score = new JLabel("");
        p2Score.setBounds((Main.WINDOW_W / 2) - 70, 20, 150, 25);
        p2Score.setText(String.valueOf(player2Score));
        add(p2Score);
    }*/

    // inner class for listener
    public class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.pressed(e.getKeyCode());
            paddle2.pressed(e.getKeyCode());
        }
        public void keyReleased(KeyEvent e) {
            paddle1.released(e.getKeyCode());
            paddle2.released(e.getKeyCode());
        }
    }
}