import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.BasicStroke;

// Die Klasse die einen Kreis beschreibt
// Orientiert sich an der Funktion der Datenstruktur der doppelt verketteten Liste

public class Circle extends DrawableObject{
    private Circle previous, next;
    private int x, y, r;
    private boolean activated;
    Color color_active = new Color(255, 203, 0);
    Color color_inactive = new Color(127, 101, 0);
    private BasicStroke stroke_dashed = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f, new float[]{10.0f}, 0.0f
        );
    private BasicStroke stroke_solid = new BasicStroke(20.0f);

    public Circle(int x, int y, int r){
        previous = this;
        next = this;
        this.x = x;
        this.y = y;
        this.r = r;
        activated = false;
    }

    public void stateSwitch(){
        activated = !activated; // Eigenen Zusantd wechseln
        previous.activated = !previous.activated;   // Zustand des vorherigen Knoten ändern
        next.activated = !next.activated;   // Zustand des nachfolgenden Knoten ändern
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke_solid);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x-r, y-r, 2*r, 2*r);
        g2d.setColor(activated? color_active : color_inactive);
        g2d.fillOval(x-r, y-r, 2*r, 2*r);
        
    }

    public void insert(Circle newCircle){
        newCircle.next = next;
        next.previous = newCircle;
        next = newCircle;
        newCircle.previous = this;
    }
}