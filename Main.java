import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    // window width and hight
    static final int WINDOW_W = 1280;
    static final int WINDOW_H = 720;


    // create object of Panel class
    Panel panel;

    // paddles color for each player
    static Color color1;
    static Color color2;

    // players' names
    static String player1;
    static String player2;

    public Main() {
        super("Pong Game");

        // add panel
        panel = new Panel(this);
        getContentPane().add(panel);

        // default stuff for window frame
        setResizable(false); // can't resize window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // the frame will pack around the panel
        setLocationRelativeTo(null); // center the window on screen
    }

    public static void main(String[] args) {
        welcomeFrame();
        player1 = JOptionPane.showInputDialog(null, "Enter player 1 name: ", "Player 1 name", JOptionPane.PLAIN_MESSAGE);
        player2 = JOptionPane.showInputDialog(null, "Enter player 2 name: ", "Player 2 name", JOptionPane.PLAIN_MESSAGE);
        color1 = pickColor(1);
        color2 = pickColor(2);
        Main pong = new Main();
        pong.setVisible(true);
    }

    // welcome window + game instructions
    public static void welcomeFrame() {
        JOptionPane.showMessageDialog(null, "Welcome to Pong! Use UP/DOWN and W/S keys to play the game.", 
            "Welcome", JOptionPane.PLAIN_MESSAGE);
    }

    // frames to pick colors
    public static Color pickColor(int playerNum) {
        Object[] colors = {"Blue", "Red", "Yellow", "Pink"};
        String pickedColor = (String)JOptionPane.showInputDialog(null, "Player " + playerNum + ":\n", "Pick A Color!",
            JOptionPane.PLAIN_MESSAGE, null, colors, "Blue");
        
        if (pickedColor == "Blue") 
            return Color.BLUE;
        else if (pickedColor == "Red")
            return Color.RED;
        else if (pickedColor == "Yellow")
            return Color.YELLOW;
        else if (pickedColor == "Pink")
            return Color.PINK;
        else
            return Color.WHITE;
    }
}
