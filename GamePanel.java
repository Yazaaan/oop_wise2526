import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Label;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.BasicStroke;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.lang.Math;

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

        // Winkel zwischen zwei Kreisen in Radiant
        double angleDiffRad = 2 * Math.PI / num;   // 360° → 2π

        int ringRadius = 160;
        int actionRadius = 20;
        int centerX = 250;
        int centerY = 210;

        ring = new Ring(centerX, centerY, ringRadius);
        ActionCircle.reset();
        rootCircle = new ActionCircle(centerX, centerY-ringRadius, actionRadius, this, lbl_turns);

        for(int i = 1; i < num; i++){
            double rad = i * angleDiffRad - Math.PI / 2;    // Start oben im Kreis (-π/2)
            int x = centerX + (int) Math.round(ringRadius * Math.cos(rad));
            int y = centerY + (int) Math.round(ringRadius * Math.sin(rad));
            rootCircle.insert(new ActionCircle(x, y, actionRadius, this, lbl_turns));
        }

        MouseAdapter mouse = new MouseAdapter(rootCircle);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
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