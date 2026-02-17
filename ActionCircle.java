import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.lang.Math.*;
import javax.swing.JLabel;

// Die Klasse die einen Kreis beschreibt
// Orientiert sich an der Funktion der Datenstruktur der doppelt verketteten Liste

public class ActionCircle extends Circle{
    private ActionCircle previous, next;
    private boolean activated;
    private Color color_active = new Color(111, 220, 17);
    private Color color_inactive = new Color(127, 17, 220);
    private BasicStroke stroke_dashed = new BasicStroke(
            strokeWidth,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            10.0f, new float[]{10, 7}, 0.0f
        );
    private BasicStroke stroke_solid = new BasicStroke(strokeWidth);
    private GamePanel panel;
    private boolean isRoot;
    private JLabel lbl_turns;
    private static boolean rootExsists;
    private static int turns;
    private static int numActivated;
    private boolean mouseHover;
    private boolean isCurrentlyOver;

    public ActionCircle(int x, int y, int r, GamePanel panel, JLabel lbl_turns){
        previous = this;
        next = this;
        this.x = x;
        this.y = y;
        this.r = r;
        this.panel = panel;
        this.lbl_turns = lbl_turns;
        
        if(!rootExsists){
            rootExsists = true;
            isRoot = true;
        }
    }

    public void stateSwitch(){
        lbl_turns.setText("Züge: " + ++turns);
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
            mouseHover = false;
            System.out.println("Gewonnen!");
            panel.gameWon(turns);
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(mouseHover? stroke_dashed : stroke_solid);
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

    public void checkPos(int mouseX, int mouseY, boolean click){
        // Eigene Distanz berechnen
        double distance = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));

        // Status für DIESEN Kreis setzen
        isCurrentlyOver = (distance < r + strokeWidth / 2);

        // Nur repainten, wenn sich der Hower-Status tatsächlich geändert hat (Performance)
        if (mouseHover != isCurrentlyOver) {
            mouseHover = isCurrentlyOver;
            panel.repaint();
            return;
        }

        // Klick-Logik ausführen
        if (isCurrentlyOver && click) {
            stateSwitch();
            panel.repaint();
            return;
        }
        
        // Den Aufruf an den nächsten Kreis weitergeben (Passiert nur, wenn der Kreis selbst nichts damit anfangen kann)
        if (!next.isRoot) {
            next.checkPos(mouseX, mouseY, click);
        }
    }

    public static void reset(){
        rootExsists = false;
        turns = 0;
        numActivated = 0;
    }
}