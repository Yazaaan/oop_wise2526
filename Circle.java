import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

// Die Klasse die einen Kreis beschreibt
// Orientiert sich an der Funktion der Datenstruktur der doppelt verketteten Liste

public abstract class Circle extends DrawableObject{
    protected int x, y, r, strokeWidth = 8;

    abstract public void paint(Graphics g);
}