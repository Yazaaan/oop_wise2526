import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Label;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePanel extends JPanel{
    private GameFrame frame;
    
    public GamePanel(GameFrame frame)
    {
        this.frame = frame;

        this.frame.add(new JLabel("Spielfeld"));
    }
}