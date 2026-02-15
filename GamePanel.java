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

    public GamePanel(int num, GameFrame frame)
    {
        this.frame = frame;

        int angleDiff = 360/num;

        //Circle rootCircle = new Circle(100, 100, 50);
        //objs[0] = rootCircle;
        
        for(int i = 0; i < num; i++){
            Circle newCircle = new Circle(250 + (int)(150*Math.cos(Math.toRadians(i*angleDiff)-Math.PI/2)), 250 + (int)(150*Math.sin(Math.toRadians(i*angleDiff)-Math.PI/2)), 20);
            //Circle newCircle = new Circle(100+ i*100, 100, 50);
            objs[i] = newCircle;

        }
        
        MouseAdapter mouse = new MouseAdapter();
        addMouseListener(mouse);

        //repaint();


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(DrawableObject o : objs) if(o != null) o.paint(g);

    }
}