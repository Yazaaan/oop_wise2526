public class MouseAdapter extends java.awt.event.MouseAdapter{
    public void mousePressed(java.awt.event.MouseEvent event){
        if( javax.swing.SwingUtilities.isLeftMouseButton(event)){
            System.out.println(String.format("Klick! X:%s | Y: %s", event.getX(), event.getY()));
        }
    }
}