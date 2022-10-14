import javax.swing.*;
import java.awt.*;

public class PaddleColor extends JFrame {
    
    public PaddleColor()
    {
        super("Pick A Color");
        Container layout = getContentPane();
        layout.setLayout(new BorderLayout());
        // message at top
        layout.add(new JLabel("Please pick a color for each player."), BorderLayout.NORTH);

        // player 1
        layout.add(new JLabel("Player 1: "), BorderLayout.WEST);
        // player 2
        layout.add(new JLabel("Player 2: "), BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
