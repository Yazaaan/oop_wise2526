import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.BasicStroke;
import static java.lang.Math.*;
import javax.swing.JLabel;

// Die Klasse die einen Kreis beschreibt
// Orientiert sich an der Funktion der Datenstruktur der doppelt verketteten Liste

public class ActionCircle extends DrawableObject{
    public ActionCircle previous, next;
    private int x, y, r;
    private int strokeWidth = 10;
    private boolean activated;
    private Color color_active = new Color(111, 220, 17);
    private Color color_inactive = new Color(127, 17, 220);
    private BasicStroke stroke_dashed = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f, new float[]{10.0f}, 0.0f
        );
    private BasicStroke stroke_solid = new BasicStroke(strokeWidth);
    private GamePanel panel;
    private boolean isRoot;
    private JLabel lbl_turns;
    private static int turns;
    private static int numActivated;

    public ActionCircle(int x, int y, int r, boolean isRoot, GamePanel panel, JLabel lbl_turns){
        previous = this;
        next = this;
        this.x = x;
        this.y = y;
        this.r = r;
        activated = false;
        this.isRoot = isRoot;
        this.panel = panel;
        this.lbl_turns = lbl_turns;
    }

    public void stateSwitch(){
        activated = !activated; // Eigenen Zusantd wechseln
        numActivated += activated? 1 : -1;

        if (previous != this){
            previous.activated = !previous.activated;   // Zustand des vorherigen Knoten ändern
            numActivated += previous.activated? 1 : -1;
        }
        if (next != this){
            next.activated = !next.activated;   // Zustand des nachfolgenden Knoten ändern
            numActivated += next.activated? 1 : -1;
        }

        if(panel.num == numActivated){
            panel.repaint();
            System.out.println("Gewonnen!");
            panel.gameWon(turns);
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke_solid);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x-r, y-r, 2*r, 2*r);
        g2d.setColor(activated? color_active : color_inactive);
        g2d.fillOval(x-r, y-r, 2*r, 2*r);
        
        if(!next.isRoot){
            next.paint(g);
        }
    }

    public void insert(ActionCircle newCircle){
        newCircle.next = next;
        next.previous = newCircle;
        next = newCircle;
        newCircle.previous = this;
    }

    public void checkPos(int mouseX, int mouseY){
        double distance = sqrt(pow(mouseX - x, 2) + pow(mouseY - y, 2));    // Abstand von Maus zu Kreis
        // System.out.println(String.format("Abstand: %s", distance));

        if(distance <= r + strokeWidth/2){
            lbl_turns.setText("Züge: " + ++turns);
            stateSwitch();
            panel.repaint();
        }
        else if(!next.isRoot){
            next.checkPos(mouseX, mouseY);
        }
    }

    public void reset(){
        turns = 0;
        numActivated = 0;
    }
}