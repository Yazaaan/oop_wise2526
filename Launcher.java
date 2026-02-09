import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;

public class Launcher extends JFrame{
    public Launcher(){
        // Fenstereigenschaften
        setTitle("Kreisspiel Launcher");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Inhalt
        JPanel panel_root = new JPanel();
        panel_root.setLayout(new GridLayout(2,2));
        panel_root.add(new JLabel("Test1"));
        panel_root.add(new JLabel("Test2"));
        panel_root.add(new JLabel("Test3"));
        panel_root.add(new JLabel("Test4"));
        
        
        this.add(panel_root);
        setVisible(true);
    }
}