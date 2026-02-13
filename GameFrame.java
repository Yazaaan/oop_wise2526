import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;

public class GameFrame extends JFrame{
    public GameFrame(int num){
        // Fenstereigenschaften
        setTitle("Kreisspiel - trial version");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menüzeile erstellen
        JMenuBar menuezeile = new JMenuBar();
        // menuezeile.setFont(new Font("serif", Font.PLAIN, 24));
        setJMenuBar(menuezeile);

        JMenu menueDatei= new JMenu("Einstellungen");
        menuezeile.add(menueDatei);        
        
        
        // Hilfe Eintrag
        JMenuItem menueHelp = new JMenuItem("Bitte helfen Sie mir...");
        menueDatei.add(menueHelp);
        menueHelp.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               JOptionPane.showMessageDialog(null, "Drücken Sie die Schalter (Kreise) so, dass alle gelb leuchten!\nDurch Drücken auf einen Kreis ändern dieser selber und die beiden Nachbarn ihren Zustand.\nSind sie aus gewesen, sind sie danach an; waren sie an, so sind sie dann aus.", "Hilfe", JOptionPane.QUESTION_MESSAGE);
           }
        });
        
        // Neues Spiel Eintrag
        JMenuItem menueNewGame = new JMenuItem("Neues Spiel");
        menueDatei.add(menueNewGame);
        menueNewGame.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               Launcher GameLauncher = new Launcher();
               dispose();
           }
        });
        
        // Beenden Eintrag
        JMenuItem menueBeenden = new JMenuItem("Beenden");
        menueDatei.add(menueBeenden);
        menueBeenden.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               System.exit(0);
           }
        });
        
        
        
        setVisible(true);
    }
}