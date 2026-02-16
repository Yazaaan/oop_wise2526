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
    DrawableObject[] objs = new DrawableObject[100];
    private Ring ring;
    private ActionCircle rootCircle;
    public final int num;   // Für alle verfügbar aber nicht manipulierbar

    public GamePanel(int num, JLabel lbl_turns)
    {
        // this.frame = frame;
        this.num = num;
        
        int angleDiff = 360/num;

        int ringRadius = 150;
        int actionRadius = 20;
        ring = new Ring(250, 250, ringRadius);
        rootCircle = new ActionCircle(250, 250-ringRadius, actionRadius, true, this, lbl_turns);
        
        for(int i = 1; i < num; i++){
            rootCircle.insert(new ActionCircle(250 + (int)(150*Math.cos(Math.toRadians(i*angleDiff)-Math.PI/2)), 250 + (int)(150*Math.sin(Math.toRadians(i*angleDiff)-Math.PI/2)), actionRadius, false, this, lbl_turns));
        }
        
        MouseAdapter mouse = new MouseAdapter(rootCircle);
        addMouseListener(mouse);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        ring.paint(g);

        ActionCircle current = rootCircle;
        ActionCircle next;
        for(int i = 0; i < num; i++){
            current.paint(g);
            next = current;
            current = current.next;
        }

    }
}