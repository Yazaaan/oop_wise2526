import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.BasicStroke;
import static java.lang.Math.*;

public class Ring extends Circle {
    private int x, y, r = 80;

    public Ring(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x-r, y-r, 2*r, 2*r);
    }
}