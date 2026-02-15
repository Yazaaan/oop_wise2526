import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

// Die Klasse die einen Kreis beschreibt
// Orientiert sich an der Funktion der Datenstruktur der doppelt verketteten Liste

public abstract class Circle extends DrawableObject{
    private int x, y, r;
    private int strokeWidth;
    private BasicStroke stroke_solid = new BasicStroke(strokeWidth);
    private GamePanel panel;

    abstract public void paint(Graphics g);
}