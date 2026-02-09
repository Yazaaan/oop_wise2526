import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
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
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Inhalt
        JPanel panel_root = new JPanel();
        panel_root.setLayout(new GridLayout(2, 2, 20, 20));
        panel_root.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20)); // 20px Padding an allen Seiten (oben, links, unten, rechts)
        
        JButton btn_newGame = new JButton("Neues Spiel starten");
        JButton btn_quit = new JButton("Beenden");
        
        // Zahleneingabe mit JSpinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(5, 3, 20, 1);    // SpinnerModel: Startwert, Minimum, Maximum, Schrittweite
        JSpinner num_difficulty = new JSpinner(spinnerModel);
        
        panel_root.add(new JLabel("Anzahl Schalter"));
        panel_root.add(num_difficulty);
        panel_root.add(btn_quit);
        panel_root.add(btn_newGame);
        
        
        this.add(panel_root);
        setVisible(true);
    }
}