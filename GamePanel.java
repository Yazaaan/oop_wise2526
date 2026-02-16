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
    private GameFrame frame;

    public GamePanel(int num, GameFrame frame, JLabel lbl_turns)
    {
        this.num = num;
        this.frame = frame;
        
        // Aufbau Spielfeld
        int angleDiff = 360/num;

        int ringRadius = 150;
        int actionRadius = 20;
        
        int centerX = 250;
        int centerY = 220;
        
        ring = new Ring(centerX, centerY, ringRadius);
        ActionCircle.reset();
        rootCircle = new ActionCircle(centerX, centerY-ringRadius, actionRadius, this, lbl_turns);
        
        for(int i = 1; i < num; i++){
            rootCircle.insert(new ActionCircle(centerX + (int)(ringRadius*Math.cos(Math.toRadians(i*angleDiff)-Math.PI/2)), centerY + (int)(ringRadius*Math.sin(Math.toRadians(i*angleDiff)-Math.PI/2)), actionRadius, this, lbl_turns));
        }
        
        MouseAdapter mouse = new MouseAdapter(rootCircle);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        
        System.out.println(size());

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        ring.paint(g);
        rootCircle.paint(g);
    }
    
    public void gameWon(int turns){
        repaint();
        frame.gameWonDialog(num, turns);
    }
}