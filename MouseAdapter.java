public class MouseAdapter extends java.awt.event.MouseAdapter{
    private ActionCircle rootCircle;
    
    
    public MouseAdapter(ActionCircle rootCircle){
        this.rootCircle = rootCircle;
    }
    
    public void mousePressed(java.awt.event.MouseEvent event){
        if( javax.swing.SwingUtilities.isLeftMouseButton(event)){
            rootCircle.checkPos(event.getX(), event.getY());
        }
    }
}