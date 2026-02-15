public class MouseAdapter extends java.awt.event.MouseAdapter{
    private Circle rootCircle;
    
    
    public MouseAdapter(Circle rootCircle){
        this.rootCircle = rootCircle;
    }
    
    public void mousePressed(java.awt.event.MouseEvent event){
        if( javax.swing.SwingUtilities.isLeftMouseButton(event)){
            
            rootCircle.checkPos(event.getX(), event.getY());
        }
    }
}