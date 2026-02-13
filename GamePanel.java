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
    DrawableObject[] objs = new DrawableObject[100];

    public GamePanel(GameFrame frame)
    {
        this.frame = frame;

        this.add(new JLabel("Spielfeld"));

        Circle rootCircle = new Circle(100, 100, 50);
        objs[0] = rootCircle;
        
        //repaint();

        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(DrawableObject o : objs) if(o != null) o.paint(g);

    }
}